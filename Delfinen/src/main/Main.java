package main;

import businesslogic.Controller;
import datasource.DBFacade;
import datasource.Facade;
import presentation.SystemUI;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Main {

    public static void main(String[] args) {

        UI ui = new SystemUI();
        Facade db = new DBFacade();
        Controller ctrl = new Controller(ui, db);
        ctrl.start();

    }

}
