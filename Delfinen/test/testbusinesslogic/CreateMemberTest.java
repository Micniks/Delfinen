
package testbusinesslogic;

import businesslogic.Controller;
import datasource.Facade;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;
import presentation.UI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CreateMemberTest {
    
@Test
public void testCreateOneMember(){
    String[] input = {"Oscar L.", "26", "1", };
    FakeUI ui = new FakeUI(input);
    FakeFacade db = new FakeFacade();
    Controller ctrl = new Controller(ui, db);
    
    //act
    ctrl.createMember();
    
    
    //assert
    assertTrue(ui.getOutput().get(0).contains("navn"));
    assertTrue(ui.getOutput().get(1).contains("alder"));
    assertTrue(ui.getOutput().get(3).contains("Motionist"));
    assertTrue(ui.getOutput().get(4).contains("Konkurrencesvømmer"));
    assertEquals(1,db.getMembers().size());
    assertTrue(db.getMembers().get(0).contains("Oscar L."));
    assertTrue(db.getMembers().get(0).contains("26"));
    assertTrue(db.getMembers().get(0).contains("motionist"));
}

@Test
public void testCreateTwoMember(){
    String[] input = {"Oscar L.", "26", "1", "Jens B.", "22", "2", };
    FakeUI ui = new FakeUI(input);
    FakeFacade db = new FakeFacade();
    Controller ctrl = new Controller(ui, db);
    
    //act
    ctrl.createMember();
    ctrl.createMember();
    System.out.println(db.getMembers().get(0));
    System.out.println(db.getMembers().get(1));
    
    
    //assert
    assertTrue(ui.getOutput().get(5).contains("navn"));
    assertTrue(ui.getOutput().get(6).contains("alder"));
    assertTrue(ui.getOutput().get(8).contains("Motionist"));
    assertTrue(ui.getOutput().get(9).contains("Konkurrencesvømmer"));
    assertEquals(2,db.getMembers().size());
    assertTrue(db.getMembers().get(0).contains("Oscar L."));
    assertTrue(db.getMembers().get(0).contains("26"));
    assertTrue(db.getMembers().get(0).contains("motionist"));    
    assertTrue(db.getMembers().get(1).contains("Jens B."));
    assertTrue(db.getMembers().get(1).contains("22"));
    assertTrue(db.getMembers().get(1).contains("konkurrencesvømmer"));
}
    
}
