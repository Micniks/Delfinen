/*
*@author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.Member;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class PayDebtTest {

    @Test
    public void testPayZeroDebt() {
        String[] input = {"1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member = new Member(1, "Oscar", 15, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.payDebt();

        //assert
        assertTrue(ui.output.get(1).contains("Det valgte medlem har ingen gæld"));

    }

    @Test
    public void testPayExsistingDebt() {
        String[] input = {"1", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member = new Member(1, "Oscar", 15, false, false, 1000, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.payDebt();

        //assert
        assertEquals(0,member.getDebt(),0);

    }

    @Test
    public void testCancelPayDebt() {
        String[] input = {"1", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member = new Member(1, "Oscar", 15, false, false, 1000, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.payDebt();

        //assert
        assertEquals(1000,member.getDebt(),0);

    }

}
