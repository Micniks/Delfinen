package main;

import businesslogic.Controller;
import datasource.DBFacade;
import datasource.Facade;
import presentation.SystemUI;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 * 
 */
public class Main {

    public static void main(String[] args) {

        UI ui = new SystemUI();
        Facade db = new DBFacade(ui.getPasswordForDatabase());
        Controller ctrl = new Controller(ui, db);
        ctrl.start();

        
    }

}
