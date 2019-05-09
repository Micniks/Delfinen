/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.Trainer;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class MainMenuTest {

    @Test
    public void testMainMenuOptionCreateMember() {
        String[] input = {"1", "Oscar L.", "26", "1", "9"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        //act
        ctrl.start();
        int index = 0;
        for (String str : ui.output) {
            System.out.println(index++ + ":   " + str);
        }

        //assert
        assertTrue(ui.output.get(0).contains("Svømmeklubben Delfinen"));
        assertTrue(ui.output.get(1).contains("1. Opret nyt medlem"));
        assertTrue(ui.output.get(9).contains("9. Afslut Program"));
        assertTrue(ui.output.get(10).contains("Nyt medlems navn"));
        assertTrue(ui.output.get(15).contains("Svømmeklubben Delfinen"));
        assertTrue(ui.output.get(24).contains("9. Afslut Program"));
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertTrue(ctrl.getMembers().getMembersList().get(0).getName().contains("Oscar L."));
        assertFalse(ctrl.getMembers().getMembersList().get(0).isCompetitiveSwimmer());
    }

    @Test
    public void testMainMenuOptionShowAllMembers() {
        String[] input = {"2", "1", "6", "9"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019", 1);
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.start();

        //assert
        assertTrue(ui.output.get(0).contains("Svømmeklubben Delfinen"));
        assertTrue(ui.output.get(1).contains("1. Opret nyt medlem"));
        assertTrue(ui.output.get(11).contains("Svømmeklubben Delfinen - Medlemmer"));
        assertTrue(ui.output.get(13).contains("1. Vis alle"));
        assertTrue(ui.output.get(17).contains("Michael"));
    }

}
