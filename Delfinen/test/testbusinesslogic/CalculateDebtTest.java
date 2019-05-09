/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.Member;
import datasource.FakeFacade;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class CalculateDebtTest {

    @Test
    public void testCalculateJuniorMember() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate current = LocalDate.now();
        Member member0years = new Member(1, "Oscar", 15, true, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 15, true, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 15, true, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateDebts(ctrl.getMembers().getMembersList());

        //assert
        assertEquals(1000, member0years.getDebt(), 0);
        assertEquals(2000, member1year.getDebt(), 0);
        assertEquals(3000, member2years.getDebt(), 0);
    }

    @Test
    public void testCalculateAdultMember() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate current = LocalDate.now();
        Member member0years = new Member(1, "Oscar", 22, true, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 22, true, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 22, true, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateDebts(ctrl.getMembers().getMembersList());

        //assert
        assertEquals(1000, member0years.getDebt(), 0);
        assertEquals(2600, member1year.getDebt(), 0);
        assertEquals(4200, member2years.getDebt(), 0);
    }

    @Test
    public void testCalculateSeniorMember() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate current = LocalDate.now();
        Member member0years = new Member(1, "Oscar", 61, true, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 61, true, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 61, true, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateDebts(ctrl.getMembers().getMembersList());

        //assert
        assertEquals(1000, member0years.getDebt(), 0);
        assertEquals(2200, member1year.getDebt(), 0);
        assertEquals(3400, member2years.getDebt(), 0);
    }

    @Test
    public void testCalculateInactiveMembers() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate current = LocalDate.now();
        Member memberJunior0years = new Member(1, "Oscar", 15, false, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(memberJunior0years);
        Member memberJunior1year = new Member(1, "Cassandra", 15, false, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(memberJunior1year);
        Member memberJunior2years = new Member(1, "Andreas", 15, false, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(memberJunior2years);
        Member memberAdult0years = new Member(1, "Jens", 22, false, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(memberAdult0years);
        Member memberAdult1year = new Member(1, "Alex", 22, false, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(memberAdult1year);
        Member memberAdult2years = new Member(1, "Benjamin", 22, false, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(memberAdult2years);
        Member memberSenior0years = new Member(1, "Michael", 61, false, false, 1000, "2019-05-01", current.toString());
        ctrl.getMembers().addMembers(memberSenior0years);
        Member memberSenior1year = new Member(1, "Marcus", 61, false, false, 1000, "2019-05-01", current.minusYears(1).toString());
        ctrl.getMembers().addMembers(memberSenior1year);
        Member memberSenior2years = new Member(1, "Mads", 61, false, false, 1000, "2019-05-01", current.minusYears(2).toString());
        ctrl.getMembers().addMembers(memberSenior2years);

        //act
        ctrl.calculateDebts(ctrl.getMembers().getMembersList());

        //assert
        assertEquals(1000, memberJunior0years.getDebt(), 0);
        assertEquals(1500, memberJunior1year.getDebt(), 0);
        assertEquals(2000, memberJunior2years.getDebt(), 0);
        assertEquals(1000, memberAdult0years.getDebt(), 0);
        assertEquals(1500, memberAdult1year.getDebt(), 0);
        assertEquals(2000, memberAdult2years.getDebt(), 0);
        assertEquals(1000, memberSenior0years.getDebt(), 0);
        assertEquals(1500, memberSenior1year.getDebt(), 0);
        assertEquals(2000, memberSenior2years.getDebt(), 0);
    }

}
