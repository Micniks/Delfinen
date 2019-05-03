/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import presentation.UI;
import java.time.LocalDate;

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
        do {
            ui.displayMainMenu();
            String userInput = ui.mainMenuSelection();
            switch (userInput) {
                case "1":
                    createMember();
                    break;
                case "2":
                    quit = true;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } while (!quit);

    }

    public void createMember() {
        String name = ui.getNewMemberName();
        int age = ui.getNewMemberAge();
        boolean competetiveSwimmer = ui.getNewMemberActivityForm();
        String signUpDate = ui.getNewMemberSignUpDate();
        Member newMember;
        if (competetiveSwimmer) {
            newMember = new CompetitiveSwimmer(currentHighestMemberID++, name, age, competetiveSwimmer, signUpDate);
        } else {
            newMember = new Member(currentHighestMemberID++, name, age, competetiveSwimmer, signUpDate);
        }
        members.addMembers(newMember);
        db.storageMember(newMember);
    }

    public void addResult() {
        int medlemsID = ui.getMemberID();
        int resultatValg = ui.resultType();
        int disciplinValg = ui.swimmingDiscipline();
        SwimmingDiscipline dv;
        switch (disciplinValg) {
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

        String træningsResultat = ui.trainingResult();
        String resultatsDato = ui.resultDate();
        CompetitiveSwimmer tempMember;
        for (Member member : members.getMembers()) {
            if (medlemsID == member.getMember_ID() && member.isCompetetiveSwimmer()) {
                tempMember = (CompetitiveSwimmer) member;
                break;
            }
        }
        TrainingResults temp = new TrainingResults(dv, træningsResultat, resultatsDato);
        
    }

}
