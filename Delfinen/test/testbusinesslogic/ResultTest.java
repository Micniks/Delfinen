package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.Member;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author oscar
 */
public class ResultTest {
    
    
    @Test
    public void testAddTrainingResult(){
        String[] input = {"1","1","3","00:00:45:67", "03-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1,"Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);
        
        
        //act
        ctrl.addResult();
        //assert
        assertEquals(1, ctrl.getMembers().getMembers().size());
        assertNotNull(member.getTrainingResultRygCrawl());
        assertTrue(member.getTrainingResultRygCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("03-05-2019"));
        
    }
    
}
