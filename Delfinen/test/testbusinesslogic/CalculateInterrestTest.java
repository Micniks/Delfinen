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
    public void testCalculateJuniorMemberFor() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member0years = new Member(1, "Oscar", 15, true, false, 1000, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 15, true, false, 1000, "2019-05-01", "2018-05-01");
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 15, true, false, 1000, "2019-05-01", "2017-05-01");
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateInterests(ctrl.getMembers().getMembersList());

        //assert
        assertTrue(false);
    }

    @Test
    public void testCalculateAdultMembertest() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member0years = new Member(1, "Oscar", 22, true, false, 1000, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 22, true, false, 1000, "2019-05-01", "2018-05-01");
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 22, true, false, 1000, "2019-05-01", "2017-05-01");
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateInterests(ctrl.getMembers().getMembersList());

        //assert
        assertTrue(false);
    }

    @Test
    public void testCalculateSeniorMembertest() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member member0years = new Member(1, "Oscar", 61, true, false, 1000, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(member0years);
        Member member1year = new Member(1, "Jens", 61, true, false, 1000, "2019-05-01", "2018-05-01");
        ctrl.getMembers().addMembers(member1year);
        Member member2years = new Member(1, "Michael", 61, true, false, 1000, "2019-05-01", "2017-05-01");
        ctrl.getMembers().addMembers(member2years);

        //act
        ctrl.calculateInterests(ctrl.getMembers().getMembersList());

        //assert
        assertTrue(false);
    }

    @Test
    public void testCalculateInactiveMemberstest() {
        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Member memberJunior0years = new Member(1, "Oscar", 15, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberJunior0years);
        Member memberJunior1years = new Member(1, "Cassandra", 15, false, false, 0, "2019-05-01", "2018-05-01");
        ctrl.getMembers().addMembers(memberJunior1years);
        Member memberJunior2years = new Member(1, "Andreas", 15, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberJunior2years);
        Member memberAdult0years = new Member(1, "Jens", 22, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberAdult0years);
        Member memberAdult1years = new Member(1, "Alex", 22, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberAdult1years);
        Member memberAdult2years = new Member(1, "Benjamin", 22, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberAdult2years);
        Member memberSenior0years = new Member(1, "Michael", 61, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberSenior0years);
        Member memberSenior1years = new Member(1, "Marcus", 61, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberSenior1years);
        Member memberSenior2years = new Member(1, "Mads", 61, false, false, 0, "2019-05-01", "2019-05-01");
        ctrl.getMembers().addMembers(memberSenior2years);

        //act
        ctrl.calculateInterests(ctrl.getMembers().getMembersList());

        //assert
        assertTrue(false);
    }

}
