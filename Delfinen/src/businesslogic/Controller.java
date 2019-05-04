/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import presentation.UI;
import java.time.LocalDate;
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
        boolean quit = false;
        createMembersFromStorage();
        do {
            ui.displayMainMenu();
            String userInput = ui.mainMenuSelection();
            switch (userInput) {
                case "1":
                    createNewMember();
                    break;
                case "2":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);
    }

    /*
    *   This is the method that is used from the main menu by the User to create a new member.
    *   It creates an object from the Member or CompetetiveSwimmer classes, add them to the 
    *   membersList, and add them to storage. A new member will always have a debt = 0 and
    *   boolean activeMember = true, as the default. 
     */
    //  TODO: Add functions for the trainer and team for CompetitiveSwimmers
    public void createNewMember() {
        // We get data from user that we need for the new member
        String name = ui.getNewMemberName();
        int age = ui.getNewMemberAge();
        boolean competetiveSwimmer = ui.getNewMemberActivityForm();
        String signUpDate = ui.getNewMemberSignUpDate();

        // We make the new member, as either Member or CompetitiveSwimmer
        Member newMember;
        if (competetiveSwimmer) {
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
        // we get data from user that we need to add a result to a specific member
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        int disciplineChoice = ui.swimmingDiscipline();
        String timeResult = ui.timeResult();

        // calling different methods based on what kind of result should be
        // added to the competitiveSwimmer 
        CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);
        if (resultChoice == 1) {
            addTrainingResult(memberID, disciplineChoice, timeResult, tempMember);
        } else if (resultChoice == 2) {
            addEventResult(memberID, disciplineChoice, timeResult, tempMember);
        }
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
        for (Member member : members.getMembers()) {
            if (memberID == member.getMember_ID() && member.isCompetetiveSwimmer()) {
                tempMember = (CompetitiveSwimmer) member;
                break;
            }
        }
        return tempMember;
    }

    /*
    *   This is the method used by Controller.addResult() to create a training Result.
    *   The TrainingResult is added to the CompetitiveSwimmers TrainingResultsArray.
    *   As of this point, each member can only have their best training result in
    *   each swimming discipline stored, and not the best for each day
     */
    //  TODO: Check if the new TrainingResult is better then the old.
    public void addTrainingResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer tempMember) {

        String resultDate = ui.resultDate();

        SwimmingDiscipline dv;
        TrainingResult temp;
        switch (disciplineChoice) {
            case 1:
                dv = SwimmingDiscipline.BUTTERFLY;
                temp = new TrainingResult(dv, timeResult, resultDate);
                tempMember.setTrainingResultButterfly(temp);
                break;
            case 2:
                dv = SwimmingDiscipline.CRAWL;
                temp = new TrainingResult(dv, timeResult, resultDate);
                tempMember.setTrainingResultCrawl(temp);
                break;
            case 3:
                dv = SwimmingDiscipline.RYGCRAWL;
                temp = new TrainingResult(dv, timeResult, resultDate);
                tempMember.setTrainingResultRygCrawl(temp);
                break;
            case 4:
                dv = SwimmingDiscipline.BRYSTSVØMMING;
                temp = new TrainingResult(dv, timeResult, resultDate);
                tempMember.setTrainingResultBrystsvømning(temp);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /*
    *   This is the method used by Controller.addResult() to create an event Result.
    *   The EventResult is added to the CompetitiveSwimmers EventResultsArrayList.
    *   As of this point, there is no sorting in the eventResults, other then the
    *   order in which they are added to the ArrayList
    *      
     */
    //  TODO: Maybe sort the eventResults based on date
    private void addEventResult(int memberID, int disciplineChoice, String timeResult, CompetitiveSwimmer tempMember) {

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
                dv = SwimmingDiscipline.BRYSTSVØMMING;
                break;
            default:
                throw new IllegalArgumentException();
        }

        temp = new EventResult(eventName, eventPlacement, timeResult, dv);
        tempMember.addEventResult(temp);
    }

    /*
    *   This is the method that read from the database when called, and create 
    *   Members from the database information. This should only be called once,
    *   when the program start up from the Controller.Start() method, as there is
    *   so far no checking if dublicates are already in the program. This method
    *   create both Members and CompetitiveSwimmer from the Database data, and
    *   add them to the MembersList that should be empty at the beginning of the
    *   program. This method adds nothing to the database, despite creating members
    *      
     */
    //  TODO: Make sure this method is only called once, propoly check if MembersList is empty
    public void createMembersFromStorage() {
        for (HashMap<String, String> memberInfo : db.getMembers()) {

            Member storageMember;

            int member_ID = Integer.parseInt(memberInfo.get("Member_ID"));
            String name = memberInfo.get("Name");
            int age = Integer.parseInt(memberInfo.get("Age"));
            boolean activeMember = Boolean.parseBoolean(memberInfo.get("Active_Member"));
            boolean competetiveSwimmer = Boolean.parseBoolean(memberInfo.get("Competitive_Swimmer"));
            double debt = Double.parseDouble(memberInfo.get("Debt"));
            String signUpDate = memberInfo.get("Sign_Up_Date");

            if (competetiveSwimmer) {
                storageMember = new CompetitiveSwimmer(member_ID, name, age, activeMember, true, debt, signUpDate);
            } else {
                storageMember = new Member(member_ID, name, age, activeMember, false, debt, signUpDate);
            }

            members.addMembers(storageMember);
        }
    }

}
