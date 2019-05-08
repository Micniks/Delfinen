/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class CalculateInterrestTest {

    @Test
    public void CalculateInactiveMembertest() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member = new Member(1, "Jens", 22, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member);
        
        //act
        ctrl.calculateInterests(ctrl.getMembers().getMembersList());
        
        //assert
        assertTrue(true);
    }

}
