/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Controller {

    private UI ui;
    private Facade db;

    public Controller(UI ui, Facade db) {
        this.ui = ui;
        this.db = db;
    }

        public void start(){
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
        boolean compedetiveSwimmer = ui.getNewMemberActivityForm();
        Member newMember = new Member(name, age, compedetiveSwimmer);
        db.storageMember(newMember);
    }
}
