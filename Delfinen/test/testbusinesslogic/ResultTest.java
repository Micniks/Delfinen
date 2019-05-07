package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
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
    public void testAddTrainingResult() {
        String[] input = {"1", "1", "3", "00:00:45:67", "03-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNotNull(member.getTrainingResultRygCrawl());
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertTrue(member.getTrainingResultRygCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getTrainingResultRygCrawl().getDiscipline());

    }

    @Test
    public void testAddOneEventResult() {
        String[] input = {"1", "2", "2", "00:01:49:71", "Ny Svømmer Konkurrence", "3"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-03-05");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNull(member.getTrainingResultRygCrawl());
        assertEquals(1, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:01:49:71"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
    }

}
