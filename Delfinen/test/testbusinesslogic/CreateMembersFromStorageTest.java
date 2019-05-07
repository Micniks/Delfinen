/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.Controller;
import businesslogic.Member;
import datasource.FakeFacade;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CreateMembersFromStorageTest {

    @Test
    public void testCreateOneNewMemberFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        HashMap<String, String> map = new HashMap();
        map.put("Member_ID", Integer.toString(1));
        map.put("Name", "Jens");
        map.put("Age", Integer.toString(23));
        map.put("Competitive_Swimmer", Boolean.toString(false));
        map.put("Active_Member", Boolean.toString(true));
        map.put("Debt", Double.toString(0));
        map.put("Sign_Up_Date", "2019-05-04");
        map.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map);

        //act
        ctrl.createMembersFromStorage();

        //assert
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertEquals(1, ctrl.getMembers().getMembersList().get(0).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(0).getName().contains("Jens"));
        assertEquals(23, ctrl.getMembers().getMembersList().get(0).getAge());
        assertFalse(ctrl.getMembers().getMembersList().get(0).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembersList().get(0).isActiveMember());
        assertEquals(0, ctrl.getMembers().getMembersList().get(0).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(0).getSignUpDate().contains("2019-05-04"));
    }

    @Test
    public void testCreateOneNewCompetitiveSwimmerFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        HashMap<String, String> map = new HashMap();
        map.put("Member_ID", Integer.toString(2));
        map.put("Name", "Oscar");
        map.put("Age", Integer.toString(24));
        map.put("Competitive_Swimmer", Boolean.toString(true));
        map.put("Active_Member", Boolean.toString(true));
        map.put("Debt", Double.toString(0));
        map.put("Sign_Up_Date", "2019-05-04");
        map.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map);

        //act
        ctrl.createMembersFromStorage();

        //assert
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertEquals(2, ctrl.getMembers().getMembersList().get(0).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(0).getName().contains("Oscar"));
        assertEquals(24, ctrl.getMembers().getMembersList().get(0).getAge());
        assertTrue(ctrl.getMembers().getMembersList().get(0).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembersList().get(0).isActiveMember());
        assertEquals(0, ctrl.getMembers().getMembersList().get(0).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(0).getSignUpDate().contains("2019-05-04"));
    }

    @Test
    public void testCreateFiveMixedMembersFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        HashMap<String, String> map1 = new HashMap();
        map1.put("Member_ID", Integer.toString(1));
        map1.put("Name", "Jens");
        map1.put("Age", Integer.toString(22));
        map1.put("Competitive_Swimmer", Boolean.toString(true));
        map1.put("Active_Member", Boolean.toString(true));
        map1.put("Debt", Double.toString(500));
        map1.put("Sign_Up_Date", "2019-05-04");
        map1.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map1);

        HashMap<String, String> map2 = new HashMap();
        map2.put("Member_ID", Integer.toString(2));
        map2.put("Name", "Oscar");
        map2.put("Age", Integer.toString(25));
        map2.put("Competitive_Swimmer", Boolean.toString(true));
        map2.put("Active_Member", Boolean.toString(false));
        map2.put("Debt", Double.toString(1000));
        map2.put("Sign_Up_Date", "2019-05-04");
        map2.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map2);

        HashMap<String, String> map3 = new HashMap();
        map3.put("Member_ID", Integer.toString(3));
        map3.put("Name", "Michael");
        map3.put("Age", Integer.toString(26));
        map3.put("Competitive_Swimmer", Boolean.toString(false));
        map3.put("Active_Member", Boolean.toString(true));
        map3.put("Debt", Double.toString(2000));
        map3.put("Sign_Up_Date", "2019-05-04");
        map3.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map3);

        HashMap<String, String> map4 = new HashMap();
        map4.put("Member_ID", Integer.toString(5));
        map4.put("Name", "Cassandra");
        map4.put("Age", Integer.toString(20));
        map4.put("Competitive_Swimmer", Boolean.toString(false));
        map4.put("Active_Member", Boolean.toString(false));
        map4.put("Debt", Double.toString(100000));
        map4.put("Sign_Up_Date", "2019-05-04");
        map4.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map4);

        HashMap<String, String> map5 = new HashMap();
        map5.put("Member_ID", Integer.toString(4));
        map5.put("Name", "Alexander");
        map5.put("Age", Integer.toString(23));
        map5.put("Competitive_Swimmer", Boolean.toString(true));
        map5.put("Active_Member", Boolean.toString(true));
        map5.put("Debt", Double.toString(0));
        map5.put("Sign_Up_Date", "2019-05-04");
        map5.put("Pay_Date", "2019-05-04");
        db.testMembersHashMapArray.add(map5);

        //act
        ctrl.createMembersFromStorage();

        //assert
        assertEquals(5, ctrl.getMembers().getMembersList().size());
        assertEquals(1, ctrl.getMembers().getMembersList().get(0).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(0).getName().contains("Jens"));
        assertEquals(22, ctrl.getMembers().getMembersList().get(0).getAge());
        assertTrue(ctrl.getMembers().getMembersList().get(0).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembersList().get(0).isActiveMember());
        assertEquals(500, ctrl.getMembers().getMembersList().get(0).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(0).getSignUpDate().contains("2019-05-04"));
        
        assertEquals(2, ctrl.getMembers().getMembersList().get(1).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(1).getName().contains("Oscar"));
        assertEquals(25, ctrl.getMembers().getMembersList().get(1).getAge());
        assertTrue(ctrl.getMembers().getMembersList().get(1).isCompetetiveSwimmer());
        assertFalse(ctrl.getMembers().getMembersList().get(1).isActiveMember());
        assertEquals(1000, ctrl.getMembers().getMembersList().get(1).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(1).getSignUpDate().contains("2019-05-04"));
        
        assertEquals(3, ctrl.getMembers().getMembersList().get(2).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(2).getName().contains("Michael"));
        assertEquals(26, ctrl.getMembers().getMembersList().get(2).getAge());
        assertFalse(ctrl.getMembers().getMembersList().get(2).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembersList().get(2).isActiveMember());
        assertEquals(2000, ctrl.getMembers().getMembersList().get(2).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(2).getSignUpDate().contains("2019-05-04"));
        
        assertEquals(5, ctrl.getMembers().getMembersList().get(3).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(3).getName().contains("Cassandra"));
        assertEquals(20, ctrl.getMembers().getMembersList().get(3).getAge());
        assertFalse(ctrl.getMembers().getMembersList().get(3).isCompetetiveSwimmer());
        assertFalse(ctrl.getMembers().getMembersList().get(3).isActiveMember());
        assertEquals(100000, ctrl.getMembers().getMembersList().get(3).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(3).getSignUpDate().contains("2019-05-04"));
        
        assertEquals(4, ctrl.getMembers().getMembersList().get(4).getMember_ID());
        assertTrue(ctrl.getMembers().getMembersList().get(4).getName().contains("Alexander"));
        assertEquals(23, ctrl.getMembers().getMembersList().get(4).getAge());
        assertTrue(ctrl.getMembers().getMembersList().get(4).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembersList().get(4).isActiveMember());
        assertEquals(0, ctrl.getMembers().getMembersList().get(4).getDebt(), 0);
        assertTrue(ctrl.getMembers().getMembersList().get(4).getSignUpDate().contains("2019-05-04"));
    }

}
