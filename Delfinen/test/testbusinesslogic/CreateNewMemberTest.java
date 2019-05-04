package testbusinesslogic;

import businesslogic.Controller;
import datasource.Facade;
import datasource.FakeFacade;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CreateNewMemberTest {

    @Test
    public void testCreateOneMemberToStorage() {
        String[] input = {"Oscar L.", "26", "1",};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String localDate = today.format(formatter);

        //act
        ctrl.createNewMember();
        System.out.println(db.getMembers().get(0).get("Dept"));

        //assert
        assertTrue(ui.getOutput().get(0).contains("navn"));
        assertTrue(ui.getOutput().get(1).contains("alder"));
        assertTrue(ui.getOutput().get(3).contains("Motionist"));
        assertTrue(ui.getOutput().get(4).contains("Konkurrencesvømmer"));
        assertEquals(1, db.getMembers().size());
        assertTrue(db.getMembers().get(0).get("Name").contains("Oscar L."));
        assertTrue(db.getMembers().get(0).get("Age").contains("26"));
        assertTrue(!Boolean.parseBoolean(db.getMembers().get(0).get("Competitive_Swimmer")));
        assertTrue(Boolean.parseBoolean(db.getMembers().get(0).get("Active_Member")));
        assertTrue(db.getMembers().get(0).get("Debt").contains("0"));
        assertEquals(localDate, db.getMembers().get(0).get("Sign_Up_Date"));
    }

    @Test
    public void testCreateOneMemberToMembersList() {
        String[] input = {"Oscar L.", "26", "1",};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String localDate = today.format(formatter);

        //act
        ctrl.createNewMember();
        System.out.println(db.getMembers().get(0).get("Dept"));

        //assert
        assertTrue(ui.getOutput().get(0).contains("navn"));
        assertTrue(ui.getOutput().get(1).contains("alder"));
        assertTrue(ui.getOutput().get(3).contains("Motionist"));
        assertTrue(ui.getOutput().get(4).contains("Konkurrencesvømmer"));
        assertEquals(1, ctrl.getMembers().getMembers().size());
        assertTrue(ctrl.getMembers().getMembers().get(0).getName().contains("Oscar L."));
        assertEquals(26, ctrl.getMembers().getMembers().get(0).getAge());
        assertFalse(ctrl.getMembers().getMembers().get(0).isCompetetiveSwimmer());
        assertTrue(ctrl.getMembers().getMembers().get(0).isActiveMember());
        assertEquals(0, ctrl.getMembers().getMembers().get(0).getDebt(), 0);
        assertEquals(localDate, ctrl.getMembers().getMembers().get(0).getSignUpDate());

    }

    @Test
    public void testCreateTwoMemberToStorage() {
        String[] input = {"Oscar L.", "26", "1", "Jens B.", "22", "2",};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String localDate = today.format(formatter);

        //act
        ctrl.createNewMember();
        ctrl.createNewMember();
        System.out.println(db.getMembers().get(0));
        System.out.println(db.getMembers().get(1));

        //assert
        assertTrue(ui.getOutput().get(5).contains("navn"));
        assertTrue(ui.getOutput().get(6).contains("alder"));
        assertTrue(ui.getOutput().get(8).contains("Motionist"));
        assertTrue(ui.getOutput().get(9).contains("Konkurrencesvømmer"));
        assertEquals(2, db.getMembers().size());

        assertTrue(db.getMembers().get(0).get("Name").contains("Oscar L."));
        assertTrue(db.getMembers().get(0).get("Age").contains("26"));
        assertTrue(!Boolean.parseBoolean(db.getMembers().get(0).get("Competitive_Swimmer")));
        assertTrue(Boolean.parseBoolean(db.getMembers().get(0).get("Active_Member")));
        assertTrue(db.getMembers().get(0).get("Debt").contains("0"));
        assertEquals(localDate, db.getMembers().get(0).get("Sign_Up_Date"));

        assertTrue(db.getMembers().get(1).get("Name").contains("Jens"));
        assertTrue(db.getMembers().get(1).get("Age").contains("22"));
        assertTrue(Boolean.parseBoolean(db.getMembers().get(1).get("Competitive_Swimmer")));
        assertTrue(Boolean.parseBoolean(db.getMembers().get(1).get("Active_Member")));
        assertTrue(db.getMembers().get(1).get("Debt").contains("0"));
        assertEquals(localDate, db.getMembers().get(0).get("Sign_Up_Date"));
    }

}
