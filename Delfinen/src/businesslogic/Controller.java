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

/**
 *
 * @author Michael N. Korsgaard
 */
public class Controller {

    private UI ui;
    private Facade db;
    private Members members;
    private int currentHighestMemberID;

    public Members getMembers() {
        return members;
    }

    public Controller(UI ui, Facade db) {
        this.ui = ui;
        this.db = db;
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
        createMembersFromStorage();
        createResultsFromStorage();
        for (Member member : members.getMembersList()) {
            System.out.println(member.getMember_ID() + ". " + member);
        }
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
                    editResultMenu();
                    break;
                case "4":
                    showResultMenu();
                    break;
                case "5":
                   ui.showDebt(members.getMembersList());
                   break;
                case "6":
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
    //  TODO: Add functions for the trainer and team for CompetitiveSwimmers
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

    /*
    *   This is our method to create a Training- or Event-Result, and store them
    *   in a member that the results belong to.
     */
    //  TODO: add access to the start() method in controller
    //  TODO: refactor temp names
    public void addResult() {
        int memberID = ui.getMemberID();
        CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);

        if (tempMember != null) {
            int resultChoice = ui.resultType();
            int disciplineChoice = ui.swimmingDiscipline();
            String timeResult = ui.timeResult();

            if (resultChoice == 1) {
                addTrainingResult(memberID, disciplineChoice, timeResult, tempMember);
            } else if (resultChoice == 2) {
                addEventResult(memberID, disciplineChoice, timeResult, tempMember);
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
    //  TODO: refactor temp names.
    //  TODO: Change the switch to use getTrainingResults() and use indexes, so we can generlize more from the switch
    public void addTrainingResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer tempMember) {

        String resultDate = ui.resultDate();

        SwimmingDiscipline dv;
        TrainingResult newTrainingResult;
        TrainingResult oldTrainingResult;
        boolean confirmProceed = true;
        switch (disciplineChoice) {
            case 1:
                dv = SwimmingDiscipline.BUTTERFLY;
                newTrainingResult = new TrainingResult(dv, timeResult, resultDate);
                if (tempMember.getTrainingResultButterfly() != null) {
                    oldTrainingResult = tempMember.getTrainingResultButterfly();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, dv);
                }
                if (confirmProceed) {
                    tempMember.setTrainingResultButterfly(newTrainingResult);
                }
                break;
            case 2:
                dv = SwimmingDiscipline.CRAWL;
                newTrainingResult = new TrainingResult(dv, timeResult, resultDate);
                if (tempMember.getTrainingResultCrawl() != null) {
                    oldTrainingResult = tempMember.getTrainingResultCrawl();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, dv);
                }
                if (confirmProceed) {
                    tempMember.setTrainingResultCrawl(newTrainingResult);
                }
                break;
            case 3:
                dv = SwimmingDiscipline.RYGCRAWL;
                newTrainingResult = new TrainingResult(dv, timeResult, resultDate);
                if (tempMember.getTrainingResultRygCrawl() != null) {
                    oldTrainingResult = tempMember.getTrainingResultRygCrawl();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, dv);
                }
                if (confirmProceed) {
                    tempMember.setTrainingResultRygCrawl(newTrainingResult);
                }
                break;
            case 4:
                dv = SwimmingDiscipline.BRYSTSVØMNING;
                newTrainingResult = new TrainingResult(dv, timeResult, resultDate);
                if (tempMember.getTrainingResultBrystsvømning() != null) {
                    oldTrainingResult = tempMember.getTrainingResultBrystsvømning();
                    confirmProceed = confirmTrainingResultProceed(oldTrainingResult, newTrainingResult, memberID, dv);
                }
                if (confirmProceed) {
                    tempMember.setTrainingResultBrystsvømning(newTrainingResult);
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (confirmProceed) {
            db.storeTrainingResult(newTrainingResult, tempMember.getMember_ID());
        }
    }

    public boolean confirmTrainingResultProceed(TrainingResult oldTrainingResult, TrainingResult newTrainingResult, int memberID, SwimmingDiscipline dv) {
        boolean confirmOverride = ui.confirmTrainingResultOverride(oldTrainingResult, newTrainingResult);
        if (confirmOverride) {
            db.deleteTrainingResult(memberID, dv);
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
    //  TODO: Maybe sort the eventResults based on date
    //  TODO: refactor temp names
    public void addEventResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer tempMember) {

        String eventName = ui.getEventName();
        int eventPlacement = ui.getEventPlacement();

        SwimmingDiscipline dv;
        EventResult temp;
        switch (disciplineChoice) {
            case 1:
                dv = SwimmingDiscipline.BUTTERFLY;
                break;
            case 2:
                dv = SwimmingDiscipline.CRAWL;
                break;
            case 3:
                dv = SwimmingDiscipline.RYGCRAWL;
                break;
            case 4:
                dv = SwimmingDiscipline.BRYSTSVØMNING;
                break;
            default:
                throw new IllegalArgumentException();
        }

        temp = new EventResult(eventName, eventPlacement, timeResult, dv);
        tempMember.addEventResult(temp);
        db.storeEventResult(temp, tempMember.getMember_ID());
    }

    /*
    *   This method is used to delete a Training- or Event-Result from a specific
    *   Member, and goes to either deleteTrainingResult() or deleteEventResult()
     */
    //  TODO: add way to access from Start()
    //  TODO: delete from database as well.
    public void deleteResult() {
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);
        if (tempMember != null) {
            if (resultChoice == 1) {
                deleteTrainingResult(tempMember);
            } else if (resultChoice == 2) {
                deleteEventResult(tempMember);
            }
        }
    }

    /*
    *   This method is used to delete a TrainingResult from a specific CompetitiveSwimmer,
    *   by setting the TrainResultArray[] at the corrosponing index to equal null.
    *   The method is called from the Controller.deleteResult
     */
    //  TODO: refactor temp names.
    //  TODO: think about showing the time in the training dicipline before choosing to delete.
    //  TODO: delete from database as well.
    public void deleteTrainingResult(CompetitiveSwimmer tempMember) {

        int disciplineChoice = ui.swimmingDiscipline();

        switch (disciplineChoice) {
            case 1:
                db.deleteTrainingResult(tempMember.getMember_ID(), SwimmingDiscipline.BUTTERFLY);
                tempMember.setTrainingResultButterfly(null);
                break;
            case 2:
                db.deleteTrainingResult(tempMember.getMember_ID(), SwimmingDiscipline.CRAWL);
                tempMember.setTrainingResultCrawl(null);
                break;
            case 3:
                db.deleteTrainingResult(tempMember.getMember_ID(), SwimmingDiscipline.RYGCRAWL);
                tempMember.setTrainingResultRygCrawl(null);
                break;
            case 4:
                db.deleteTrainingResult(tempMember.getMember_ID(), SwimmingDiscipline.BRYSTSVØMNING);
                tempMember.setTrainingResultBrystsvømning(null);
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
    //  TODO: refactor temp names.
    //  TODO: delete from database as well.
    public void deleteEventResult(CompetitiveSwimmer tempMember) {
        int eventNeedingDeleting = ui.getEventNeedingDeleting(tempMember.getEventResults());
        String eventName = tempMember.getEventResults().get(eventNeedingDeleting).getEventName();
        SwimmingDiscipline eventSD = tempMember.getEventResults().get(eventNeedingDeleting).getDiscipline();
        db.deleteEventResult(tempMember.getMember_ID(), eventName, eventSD);
        tempMember.getEventResults().remove(eventNeedingDeleting);
    }

    public void editResult() {
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);
        if (tempMember != null) {
            if (resultChoice == 1) {
                editTrainingResult(memberID, tempMember);

            } else if (resultChoice == 2) {
                editEventResult(memberID, tempMember);
            }
        }
    }

    public void editTrainingResult(int memberID, CompetitiveSwimmer tempMember) {
        int disciplineChoice = ui.swimmingDiscipline();
        String timeresult = ui.timeResult();
        addTrainingResult(memberID, disciplineChoice, timeresult, tempMember);
    }

    public void editEventResult(int memberID, CompetitiveSwimmer tempMember) {
        deleteEventResult(tempMember);
        addEventResult(memberID, ui.swimmingDiscipline(), ui.timeResult(), tempMember);
    }

    /*
    *   This is a short method used to find a specific CompetitiveSwimmer from their
    *   Member_ID, but this method will become obsulite given a better storing/sorting
    *   method of the members, that will make this search method redudant.
    *   So far this method is only used in controller.addResult() 
     */
    //  TODO: improve sorting and searching method
    public CompetitiveSwimmer getCompetitiveSwimmerFromMemberID(int memberID) {
        CompetitiveSwimmer tempMember = null;
        for (Member member : members.getMembersList()) {
            if (memberID == member.getMember_ID() && member.isCompetitiveSwimmer()) {
                tempMember = (CompetitiveSwimmer) member;
                break;
            }
        }
        if (tempMember == null) {
            ui.notCompetitveSwimmerMessage(memberID);
        }
        return tempMember;
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
            String payDate = memberInfo.get("Pay_Date");

            if (competitiveSwimmer) {
                storageMember = new CompetitiveSwimmer(member_ID, name, age, activeMember, true, debt, signUpDate, payDate);
            } else {
                storageMember = new Member(member_ID, name, age, activeMember, false, debt, signUpDate, payDate);
            }

            members.addMembers(storageMember);
        }
    }

    /*
    *   if there is faulty data in storage, ui.notCompetitveSwimmerMessage() will be called as the program is starting up
     */
    //  TODO: addEventResults
    //  TODO: change temp names
    public void createResultsFromStorage() {
        for (HashMap<String, String> memberInfo : db.getTrainingResults()) {

            TrainingResult storageResult;

            SwimmingDiscipline dv = SwimmingDiscipline.valueOf(memberInfo.get("Swimming_Discipline"));
            String timeResult = memberInfo.get("Time_Result");
            String resultDate = memberInfo.get("Date");

            storageResult = new TrainingResult(dv, timeResult, resultDate);
            int memberID = Integer.parseInt(memberInfo.get("Member_ID"));

            CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);

            if (tempMember != null) {
                switch (dv) {
                    case BUTTERFLY:
                        tempMember.setTrainingResultButterfly(storageResult);
                        break;
                    case CRAWL:
                        tempMember.setTrainingResultCrawl(storageResult);
                        break;
                    case RYGCRAWL:
                        tempMember.setTrainingResultRygCrawl(storageResult);
                        break;
                    case BRYSTSVØMNING:
                        tempMember.setTrainingResultBrystsvømning(storageResult);
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
            SwimmingDiscipline dv = SwimmingDiscipline.valueOf(memberInfo.get("Swimming_Discipline"));

            storageResult = new EventResult(eventName, eventPlacement, timeResult, dv);
            int memberID = Integer.parseInt(memberInfo.get("Member_ID"));
            CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);
            if (tempMember != null) {
                tempMember.addEventResult(storageResult);
            }

        }
    }

    public void calculateInterests(ArrayList<Member> memberArrayList) {
        for (Member member : memberArrayList) {
            int memberAge = member.getAge();
            double oldDebt = member.getDebt();
            LocalDate current = LocalDate.now();
            int debtAge = Period.between(current, LocalDate.parse(member.getPayDate())).getYears();
            double debt;
            if (!member.isActiveMember()) {
                debt = debtAge * 500;
            } else {

                if (memberAge < 18) {
                    debt = debtAge * 1000;
                } else if (memberAge < 60) {
                    debt = debtAge * 1600;
                } else {
                    debt = debtAge * (1600 * 0.75);
                }
            }
            member.setDebt(debt);
            db.updateMember(member);

        }

    }

    public void payDebt(Member member) {
        member.setDebt(0);
        member.setPayDate(LocalDate.now().toString());
        db.updateMember(member);
    }
}
