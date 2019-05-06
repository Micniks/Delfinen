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
public class AddResultTest {

    @Test
    public void testAddOneTrainingResult() {
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

    @Test
    public void testAddFourTrainingResult() {
        String[] input = {"1", "1", "1", "00:00:45:67", "01-05-2019", "1", "1", "2", "00:01:45:67", "02-05-2019", "1", "1", "3", "00:02:45:67", "03-05-2019", "1", "1", "4", "00:03:45:67", "03-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();
        ctrl.addResult();
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertNotNull(member.getTrainingResultBrystsvømning());
        assertNotNull(member.getTrainingResultButterfly());
        assertNotNull(member.getTrainingResultCrawl());
        assertNotNull(member.getTrainingResultRygCrawl());
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertTrue(member.getTrainingResultButterfly().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultButterfly().getDate().contains("01-05-2019"));
        assertEquals(SwimmingDiscipline.BUTTERFLY, member.getTrainingResultButterfly().getDiscipline());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:01:45:67"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("02-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertTrue(member.getTrainingResultRygCrawl().getTimeResult().contains("00:02:45:67"));
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getTrainingResultRygCrawl().getDiscipline());
        assertTrue(member.getTrainingResultBrystsvømning().getTimeResult().contains("00:03:45:67"));
        assertTrue(member.getTrainingResultBrystsvømning().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.BRYSTSVØMMING, member.getTrainingResultBrystsvømning().getDiscipline());
    }

    @Test
    public void testAddThreeEventResult() {
        String[] input = {"1", "2", "1", "00:01:49:71", "Ny Svømmer Konkurrence", "3", "1", "2", "2", "00:01:19:71", "NSK", "2", "1", "2", "3", "00:00:49:71", "NSK-2", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-03-05");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNull(member.getTrainingResultRygCrawl());
        assertEquals(3, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:01:49:71"));
        assertEquals(SwimmingDiscipline.BUTTERFLY, member.getEventResults().get(0).getDiscipline());
        assertTrue(member.getEventResults().get(1).getEventName().contains("NSK"));
        assertTrue(member.getEventResults().get(1).getTimeResult().contains("00:01:19:71"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(1).getDiscipline());
        assertTrue(member.getEventResults().get(2).getEventName().contains("NSK-2"));
        assertTrue(member.getEventResults().get(2).getTimeResult().contains("00:00:49:71"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getEventResults().get(2).getDiscipline());
    }

    @Test
    public void testAddTwoTrainingResultOverride() {
        String[] input = {"1", "1", "2", "00:01:45:67", "02-05-2019", "1", "1", "2", "00:00:45:67", "03-05-2019", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNotNull(member.getTrainingResultCrawl());
        assertNull(member.getTrainingResultRygCrawl());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
    }

    @Test
    public void testAddTwoTrainingResultToTwoMembers() {
        String[] input = {"1", "1", "1", "00:00:45:67", "01-05-2019", "2", "1", "2", "00:01:45:67", "02-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member1 = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        CompetitiveSwimmer member2 = new CompetitiveSwimmer(2, "Oscar", 25, true, "02-05-2019");
        ctrl.getMembers().addMembers(member1);
        ctrl.getMembers().addMembers(member2);

        //act
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertEquals(2, ctrl.getMembers().getMembersList().size());

        assertNotNull(member1.getTrainingResultButterfly());
        assertNull(member1.getTrainingResultCrawl());
        assertTrue(member1.getName().contains("Michael"));
        assertTrue(member1.getTrainingResultButterfly().getTimeResult().contains("00:00:45:67"));
        assertTrue(member1.getTrainingResultButterfly().getDate().contains("01-05-2019"));
        assertEquals(SwimmingDiscipline.BUTTERFLY, member1.getTrainingResultButterfly().getDiscipline());

        assertNull(member2.getTrainingResultButterfly());
        assertNotNull(member2.getTrainingResultCrawl());
        assertTrue(member2.getName().contains("Oscar"));
        assertTrue(member2.getTrainingResultCrawl().getTimeResult().contains("00:01:45:67"));
        assertTrue(member2.getTrainingResultCrawl().getDate().contains("02-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member2.getTrainingResultCrawl().getDiscipline());
    }

    @Test
    public void testAddTwoEventResultToTwoMembers() {
        String[] input = {"1", "2", "1", "00:01:49:71", "Ny Svømmer Konkurrence", "3", "2", "2", "2", "00:01:19:71", "NSK", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member1 = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        CompetitiveSwimmer member2 = new CompetitiveSwimmer(2, "Oscar", 25, true, "02-05-2019");
        ctrl.getMembers().addMembers(member1);
        ctrl.getMembers().addMembers(member2);

        //act
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertNull(member1.getTrainingResultBrystsvømning());
        assertNull(member1.getTrainingResultButterfly());
        assertNull(member1.getTrainingResultCrawl());
        assertNull(member1.getTrainingResultRygCrawl());
        assertTrue(member1.getName().contains("Michael"));
        assertNull(member2.getTrainingResultBrystsvømning());
        assertNull(member2.getTrainingResultButterfly());
        assertNull(member2.getTrainingResultCrawl());
        assertNull(member2.getTrainingResultRygCrawl());
        assertTrue(member2.getName().contains("Oscar"));
        assertEquals(1, member1.getEventResults().size());
        assertEquals(1, member2.getEventResults().size());
        assertTrue(member1.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertTrue(member1.getEventResults().get(0).getTimeResult().contains("00:01:49:71"));
        assertEquals(SwimmingDiscipline.BUTTERFLY, member1.getEventResults().get(0).getDiscipline());
        assertTrue(member2.getEventResults().get(0).getEventName().contains("NSK"));
        assertTrue(member2.getEventResults().get(0).getTimeResult().contains("00:01:19:71"));
        assertEquals(SwimmingDiscipline.CRAWL, member2.getEventResults().get(0).getDiscipline());
    }

}
