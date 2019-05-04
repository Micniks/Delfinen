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

    public void createNewMember() {
        String name = ui.getNewMemberName();
        int age = ui.getNewMemberAge();
        boolean competetiveSwimmer = ui.getNewMemberActivityForm();
        String signUpDate = ui.getNewMemberSignUpDate();
        Member newMember;
        if (competetiveSwimmer) {
            newMember = new CompetitiveSwimmer(currentHighestMemberID++, name, age, true, signUpDate);
        } else {
            newMember = new Member(currentHighestMemberID++, name, age, false, signUpDate);
        }
        members.addMembers(newMember);
        db.storageMember(newMember);
    }

    public void addResult() {
        int memberID = ui.getMemberID();
        int resultChoice = ui.resultType();
        int disciplineChoice = ui.swimmingDiscipline();
        String timeResult = ui.timeResult();
        CompetitiveSwimmer tempMember = getCompetitiveSwimmerFromMemberID(memberID);

        if (resultChoice == 1) {
            addTrainingResult(memberID, disciplineChoice, timeResult, tempMember);
        } else if (resultChoice == 2) {
            addEventResult(memberID, disciplineChoice, timeResult, tempMember);
        }
    }

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
