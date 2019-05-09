/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import presentation.UI;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class Controller {

    private UI ui;
    private Facade db;
    private Trainers trainers;
    private Members members;
    private int currentHighestMemberID;
    private int currentHighestTrainerID;

    public Members getMembers() {
        return members;
    }

    public Controller(UI ui, Facade db) {
        this.ui = ui;
        this.db = db;
        this.trainers = new Trainers(db);
        this.members = new Members(db);
        
    }

    /*
    *   This is the method that create the "Main Menu", is the entrance to the program from the main class.
    *   This is the first real step into the program, as well as the last
     */
    //  TODO: Create test for the this function
    //  TODO: Add the new functions to the Menu
    //  TODO: Error-handeling
    public void start() {
        currentHighestMemberID = db.readHighestMemberID();
        currentHighestTrainerID = db.readHighestTrainerID();
        createMembersFromStorage();
        createResultsFromStorage();
        boolean quit = false;
        do {
            ui.displayMainMenu();
            String userInput = ui.getMenuSelection();
            switch (userInput) {
                case "1":
                    createNewMember();
                    break;
                case "2":
                    membersMenu();
                    break;
                case "3":
                    removeMember();
                    break;
                case "4":
                    editResultMenu();
                    break;
                case "5":
                    showResultMenu();
                    break;
                case "6":
                    ui.showDebt(members.getMembersList());
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);
    }

    public void showResultMenu() {
        boolean quit = false;
        do {
            ui.showResultsMenu();
            String userInput = ui.getMenuSelection();
            switch (userInput) {
                case "1":
                    ui.showResults(members.getMembersList());
                    break;

                case "2":
                    ui.showTopTimes(db.getTopFiveTrainingResults());
                    break;
                case "3":
                    ui.showTopTimes(db.getTopFiveEventResults());
                case "4":
                    showResultsFromMemberID();
                case "5":
                    quit = true;
                    break;
            }
        } while (!quit);
    }

    public void editResultMenu() {
        boolean quit = false;
        do {
            ui.displayEditResultMenu();
            String userInput = ui.getMenuSelection();
            switch (userInput) {
                case "1":
                    addResult();
                    break;
                case "2":
                    editResult();
                    break;
                case "3":
                    deleteResult();
                    break;
                case "4":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);
    }

    public void membersMenu() {
        boolean quit = false;
        do {
            ui.showMembersMenu();
            String userInput = ui.getMenuSelection();
            switch (userInput) {
                case "1":
                    ui.showAllMembers(members.getMembersList());
                    break;
                case "2":
                    ui.showNonCompetitiveSwimmers(members.getMembersList());
                    break;
                case "3":
                    ui.showCompetitiveSwimmers(members.getMembersList());
                    break;
                case "4":
                    ui.showYoungTeamMembers(members.getMembersList());
                    break;
                case "5":
                    ui.showSeniorTeamMembers(members.getMembersList());
                    break;
                case "6":
                    quit = true;
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);
    }

    /*
    *   This is the method that is used from the main menu by the User to create a new member.
    *   It creates an object from the Member or CompetitiveSwimmer classes, add them to the 
    *   membersList, and add them to storage. A new member will always have a debt = 0 and
    *   boolean activeMember = true, as the default. 
     */
    //  TODO: Add functions for the trainer for CompetitiveSwimmers
    public void createNewMember() {
        // We get data from user that we need for the new member
        String name = ui.getNewMemberName();
        int age = ui.getNewMemberAge();
        boolean competitiveSwimmer = ui.getNewMemberActivityForm();
        String signUpDate = ui.getNewMemberSignUpDate();

        // We make the new member, as either Member or CompetitiveSwimmer
        Member newMember;
        if (competitiveSwimmer) {
            newMember = new CompetitiveSwimmer(currentHighestMemberID++, name, age, true, signUpDate);
        } else {
            newMember = new Member(currentHighestMemberID++, name, age, false, signUpDate);
        }

        // We store the member in both the programs MembersList and the Database
        members.addMembers(newMember);
        db.storageMember(newMember);
    }
    
    public void createNewTrainer() {
        String name = ui.getNewTrainerName();
        
        Trainer trainer = new Trainer(currentHighestTrainerID, name);
        
    }

    /*
    *   This is our method to create a Training- or Event-Result, and store them
    *   in a member that the results belong to.
     */
    public void addResult() {
        int memberID = ui.getMemberID();
        CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(memberID);

        if (competitiveSwimmer != null) {
            int resultChoice = ui.resultType();
            int disciplineChoice = ui.swimmingDiscipline();
            String timeResult = ui.timeResult();

            if (resultChoice == 1) {
                addTrainingResult(memberID, disciplineChoice, timeResult, competitiveSwimmer);
            } else if (resultChoice == 2) {
                addEventResult(memberID, disciplineChoice, timeResult, competitiveSwimmer);
            }
        }
    }

    /*
    *   This is the method used by Controller.addResult() to create a training Result.
    *   The TrainingResult is added to the CompetitiveSwimmers TrainingResultsArray.
    *   As of this point, each member can only have their best training result in
    *   each swimming discipline stored, and not the best for each day
     */
    //  TODO: Check if the new TrainingResult is better then the old.
    //  TODO: Change the switch to use getTrainingResults() and use indexes, so we can generlize more from the switch
    public void addTrainingResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer competitiveSwimmer) {

        String resultDate = ui.resultDate();

        SwimmingDiscipline discipline;
        TrainingResult newTrainingResult;
        TrainingResult oldTrainingResult;
        boolean confirmProceed = true;
        switch (disciplineChoice) {
            case 1:
                discipline = SwimmingDiscipline.BUTTERFLY;
                newTrainingResult = new TrainingResult(discipline, timeResult, resultDate);
                if (competitiveSwimmer.getTrainingResultButterfly() != null) {
                    oldTrainingResult = competitiveSwimmer.getTrainingResultButterfly();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, discipline);
                }
                if (confirmProceed) {
                    competitiveSwimmer.setTrainingResultButterfly(newTrainingResult);
                }
                break;
            case 2:
                discipline = SwimmingDiscipline.CRAWL;
                newTrainingResult = new TrainingResult(discipline, timeResult, resultDate);
                if (competitiveSwimmer.getTrainingResultCrawl() != null) {
                    oldTrainingResult = competitiveSwimmer.getTrainingResultCrawl();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, discipline);
                }
                if (confirmProceed) {
                    competitiveSwimmer.setTrainingResultCrawl(newTrainingResult);
                }
                break;
            case 3:
                discipline = SwimmingDiscipline.RYGCRAWL;
                newTrainingResult = new TrainingResult(discipline, timeResult, resultDate);
                if (competitiveSwimmer.getTrainingResultRygCrawl() != null) {
                    oldTrainingResult = competitiveSwimmer.getTrainingResultRygCrawl();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, discipline);
                }
                if (confirmProceed) {
                    competitiveSwimmer.setTrainingResultRygCrawl(newTrainingResult);
                }
                break;
            case 4:
                discipline = SwimmingDiscipline.BRYSTSVØMNING;
                newTrainingResult = new TrainingResult(discipline, timeResult, resultDate);
                if (competitiveSwimmer.getTrainingResultBrystsvømning() != null) {
                    oldTrainingResult = competitiveSwimmer.getTrainingResultBrystsvømning();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, discipline);
                }
                if (confirmProceed) {
                    competitiveSwimmer.setTrainingResultBrystsvømning(newTrainingResult);
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (confirmProceed) {
            db.storeTrainingResult(newTrainingResult, competitiveSwimmer.getMember_ID());
        }
    }

    public boolean confirmTrainingResultProceed(TrainingResult oldTrainingResult, TrainingResult newTrainingResult, int memberID, SwimmingDiscipline discipline) {
        boolean confirmOverride = ui.confirmTrainingResultOverride(oldTrainingResult, newTrainingResult);
        if (confirmOverride) {
            db.deleteTrainingResult(memberID, discipline);
        }
        return confirmOverride;
    }

    /*
    *   This is the method used by Controller.addResult() to create an event Result.
    *   The EventResult is added to the CompetitiveSwimmers EventResultsArrayList.
    *   As of this point, there is no sorting in the eventResults, other then the
    *   order in which they are added to the ArrayList
    *      
     */
    public void addEventResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer competitiveSwimmer) {

        String eventName = ui.getEventName();
        int eventPlacement = ui.getEventPlacement();

        SwimmingDiscipline discipline;
        EventResult eventResult;
        switch (disciplineChoice) {
            case 1:
                discipline = SwimmingDiscipline.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDiscipline.CRAWL;
                break;
            case 3:
                discipline = SwimmingDiscipline.RYGCRAWL;
                break;
            case 4:
                discipline = SwimmingDiscipline.BRYSTSVØMNING;
                break;
            default:
                throw new IllegalArgumentException();
        }

        eventResult = new EventResult(eventName, eventPlacement, timeResult, discipline);
        competitiveSwimmer.addEventResult(eventResult);
        db.storeEventResult(eventResult, competitiveSwimmer.getMember_ID());
    }

    /*
    *   This method is used to delete a Training- or Event-Result from a specific
    *   Member, and goes to either deleteTrainingResult() or deleteEventResult()
     */
    public void deleteResult() {
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(memberID);
        if (competitiveSwimmer != null) {
            if (resultChoice == 1) {
                deleteTrainingResult(competitiveSwimmer);
            } else if (resultChoice == 2) {
                deleteEventResult(competitiveSwimmer);
            }
        }
    }

    /*
    *   This method is used to delete a TrainingResult from a specific CompetitiveSwimmer,
    *   by setting the TrainResultArray[] at the corrosponing index to equal null.
    *   The method is called from the Controller.deleteResult
     */
    //  TODO: think about showing the time in the training dicipline before choosing to delete. A confirm delete ui call
    public void deleteTrainingResult(CompetitiveSwimmer competitiveSwimmer) {

        int disciplineChoice = ui.swimmingDiscipline();

        switch (disciplineChoice) {
            case 1:
                db.deleteTrainingResult(competitiveSwimmer.getMember_ID(), SwimmingDiscipline.BUTTERFLY);
                competitiveSwimmer.setTrainingResultButterfly(null);
                break;
            case 2:
                db.deleteTrainingResult(competitiveSwimmer.getMember_ID(), SwimmingDiscipline.CRAWL);
                competitiveSwimmer.setTrainingResultCrawl(null);
                break;
            case 3:
                db.deleteTrainingResult(competitiveSwimmer.getMember_ID(), SwimmingDiscipline.RYGCRAWL);
                competitiveSwimmer.setTrainingResultRygCrawl(null);
                break;
            case 4:
                db.deleteTrainingResult(competitiveSwimmer.getMember_ID(), SwimmingDiscipline.BRYSTSVØMNING);
                competitiveSwimmer.setTrainingResultBrystsvømning(null);
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    /*
    *   This method is used to delete EventResults from a specific competitiveSwimmer,
    *   by removing it from the ArrayList of EventResults from that member.
    *   The method is called from the Controller.deleteResult
     */
    public void deleteEventResult(CompetitiveSwimmer competitiveSwimmer) {
        int eventNeedingDeleting = ui.getEventNeedingDeleting(competitiveSwimmer.getEventResults());
        String eventName = competitiveSwimmer.getEventResults().get(eventNeedingDeleting).getEventName();
        SwimmingDiscipline eventSD = competitiveSwimmer.getEventResults().get(eventNeedingDeleting).getDiscipline();
        db.deleteEventResult(competitiveSwimmer.getMember_ID(), eventName, eventSD);
        competitiveSwimmer.getEventResults().remove(eventNeedingDeleting);
    }

    public void editResult() {
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(memberID);
        if (competitiveSwimmer != null) {
            if (resultChoice == 1) {
                editTrainingResult(memberID, competitiveSwimmer);

            } else if (resultChoice == 2) {
                editEventResult(memberID, competitiveSwimmer);
            }
        }
    }

    public void editTrainingResult(int memberID, CompetitiveSwimmer competitiveSwimmer) {
        int disciplineChoice = ui.swimmingDiscipline();
        String timeresult = ui.timeResult();
        addTrainingResult(memberID, disciplineChoice, timeresult, competitiveSwimmer);
    }

    public void editEventResult(int memberID, CompetitiveSwimmer competitiveSwimmer) {
        deleteEventResult(competitiveSwimmer);
        addEventResult(memberID, ui.swimmingDiscipline(), ui.timeResult(), competitiveSwimmer);
    }

    /*
    *   This is a short method used to find a specific CompetitiveSwimmer from their
    *   Member_ID, but this method will become obsulite given a better storing/sorting
    *   method of the members, that will make this search method redudant.
    *   So far this method is only used in controller.addResult() 
     */
    //  TODO: improve sorting and searching method
    public CompetitiveSwimmer getCompetitiveSwimmerFromMemberID(int memberID) {
        CompetitiveSwimmer competitiveSwimmer = null;
        for (Member member : members.getMembersList()) {
            if (memberID == member.getMember_ID() && member.isCompetitiveSwimmer()) {
                competitiveSwimmer = (CompetitiveSwimmer) member;
                break;
            }
        }
        if (competitiveSwimmer == null) {
            ui.notCompetitveSwimmerMessage(memberID);
        }
        return competitiveSwimmer;
    }

    public Member getMemberFromMemberID(int memberID) {
        Member foundMember = null;
        for (Member member : members.getMembersList()) {
            if (memberID == member.getMember_ID()) {
                foundMember = member;
                break;
            }
        }
        return foundMember;
    }

    /*
    *   This is the method that read from the database when called, and create 
    *   Members from the database information. This should only be called once,
    *   when the program start up from the Controller.Start() method, as there is
    *   so far no checking if dublicates are already in the program. This method
    *   create both Members and CompetitiveSwimmer from the Database data, and
    *   add them to the MembersList that should be empty at the beginning of the
    *   program. This method adds nothing to the database, despite creating members  
     */
    //  TODO: Make sure this method is only called once, propoly check if MembersList is empty
    public void createMembersFromStorage() {
        for (HashMap<String, String> memberInfo : db.getMembers()) {

            Member storageMember;

            int member_ID = Integer.parseInt(memberInfo.get("Member_ID"));
            String name = memberInfo.get("Name");
            int age = Integer.parseInt(memberInfo.get("Age"));
            boolean activeMember = Boolean.parseBoolean(memberInfo.get("Active_Member"));
            boolean competitiveSwimmer = Boolean.parseBoolean(memberInfo.get("Competitive_Swimmer"));
            double debt = Double.parseDouble(memberInfo.get("Debt"));
            String signUpDate = memberInfo.get("Sign_Up_Date");
            String lastAddedDebtDate = memberInfo.get("Pay_Date");

            if (competitiveSwimmer) {
                storageMember = new CompetitiveSwimmer(member_ID, name, age, activeMember, true, debt, signUpDate, lastAddedDebtDate);
            } else {
                storageMember = new Member(member_ID, name, age, activeMember, false, debt, signUpDate, lastAddedDebtDate);
            }

            members.addMembers(storageMember);
        }
    }

    /*
    *   if there is faulty data in storage, ui.notCompetitveSwimmerMessage() will be called as the program is starting up
     */
    public void createResultsFromStorage() {
        for (HashMap<String, String> memberInfo : db.getTrainingResults()) {

            TrainingResult storageResult;

            SwimmingDiscipline discipline = SwimmingDiscipline.valueOf(memberInfo.get("Swimming_Discipline"));
            String timeResult = memberInfo.get("Time_Result");
            String resultDate = memberInfo.get("Date");

            storageResult = new TrainingResult(discipline, timeResult, resultDate);
            int memberID = Integer.parseInt(memberInfo.get("Member_ID"));

            CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(memberID);

            if (competitiveSwimmer != null) {
                switch (discipline) {
                    case BUTTERFLY:
                        competitiveSwimmer.setTrainingResultButterfly(storageResult);
                        break;
                    case CRAWL:
                        competitiveSwimmer.setTrainingResultCrawl(storageResult);
                        break;
                    case RYGCRAWL:
                        competitiveSwimmer.setTrainingResultRygCrawl(storageResult);
                        break;
                    case BRYSTSVØMNING:
                        competitiveSwimmer.setTrainingResultBrystsvømning(storageResult);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }

        for (HashMap<String, String> memberInfo : db.getEventResults()) {

            EventResult storageResult;

            String eventName = memberInfo.get("Event_Name");
            int eventPlacement = Integer.parseInt(memberInfo.get("Placement"));
            String timeResult = memberInfo.get("Time_Result");
            SwimmingDiscipline discipline = SwimmingDiscipline.valueOf(memberInfo.get("Swimming_Discipline"));

            storageResult = new EventResult(eventName, eventPlacement, timeResult, discipline);
            int memberID = Integer.parseInt(memberInfo.get("Member_ID"));
            CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(memberID);
            if (competitiveSwimmer != null) {
                competitiveSwimmer.addEventResult(storageResult);
            }

        }
    }

    public void calculateDebts(ArrayList<Member> memberArrayList) {
        for (Member member : memberArrayList) {
            int memberAge = member.getAge();
            double oldDebt = member.getDebt();
            LocalDate current = LocalDate.now();
            int debtAge = Period.between(current, LocalDate.parse(member.getLastAddedDebtDate())).getYears();
            debtAge = Math.abs(debtAge);
            double debt = member.getDebt();
            if (!member.isActiveMember()) {
                debt += debtAge * 500;
            } else {

                if (memberAge < 18) {
                    debt += debtAge * 1000;
                } else if (memberAge < 60) {
                    debt += debtAge * 1600;
                } else {
                    debt += debtAge * (1600 * 0.75);
                }
            }
            member.setLastAddedDebtDate(LocalDate.now().plusYears(debtAge).toString());
            member.setDebt(debt);
            db.updateMember(member);

        }

    }

    public void payDebt() {
        int memberID = ui.getMemberID();
        Member member = getMemberFromMemberID(memberID);
        Boolean confirmPayDebt = ui.confirmPayDebt(member);
        if (confirmPayDebt) {
            member.setDebt(0);
            db.updateMember(member);
        }
    }

    private void removeMember() {
        int i = ui.getMemberID();
        db.removeMember(i);
        for (Member member : members.getMembersList()) {
            if (member.getMember_ID() == i) {
                members.getMembersList().remove(member);
                break;
            }
        }
    }

    private void showResultsFromMemberID() {
        ArrayList<Member> member = new ArrayList();
        int thisMemberID = ui.getMemberID();
        CompetitiveSwimmer competitiveSwimmer = getCompetitiveSwimmerFromMemberID(thisMemberID);
        if (competitiveSwimmer != null) {
            member.add(competitiveSwimmer);
            ui.showResults(member);
        }

    }
}
