package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import businesslogic.Trainer;
import businesslogic.TrainingResult;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class AddResultTest {

    @Test
    public void testAddOneTrainingResult() {
        String[] input = {"1", "1", "3", "00:00:45:67", "2019-05-03"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03", 1);
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
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("03"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getTrainingResultRygCrawl().getDiscipline());
    }

    @Test
    public void testAddOneEventResult() {
        String[] input = {"1", "2", "2", "00:01:49:71", "Ny Svømmer Konkurrence", "3"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03", 1);
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
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
    }

    @Test
    public void testAddFourTrainingResult() {
        String[] input = {"1", "1", "1", "00:00:45:67", "01-05-2019", "1", "1", "2", "00:01:45:67", "02-05-2019", "1", "1", "3", "00:02:45:67", "03-05-2019", "1", "1", "4", "00:03:45:67", "03-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03", 1);
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
        assertEquals(SwimmingDiscipline.BRYSTSVØMNING, member.getTrainingResultBrystsvømning().getDiscipline());
    }

    @Test
    public void testAddFourEventResult() {
        String[] input = {"1", "2", "1", "00:01:49:01", "Ny Svømmer Konkurrence", "3", "1", "2", "2", "00:01:19:02", "NSK", "2", "1", "2", "3", "00:00:49:03", "NSK-2", "1", "1", "2", "4", "00:01:49:04", "SnS", "4"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03", 1);
        ctrl.getMembers().addMembers(member);

        //act
        ctrl.addResult();
        ctrl.addResult();
        ctrl.addResult();
        ctrl.addResult();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNull(member.getTrainingResultRygCrawl());
        assertEquals(4, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:01:49:01"));
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.BUTTERFLY, member.getEventResults().get(0).getDiscipline());
        assertTrue(member.getEventResults().get(1).getEventName().contains("NSK"));
        assertTrue(member.getEventResults().get(1).getTimeResult().contains("00:01:19:02"));
        assertEquals(2, member.getEventResults().get(1).getPlacement());
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(1).getDiscipline());
        assertTrue(member.getEventResults().get(2).getEventName().contains("NSK-2"));
        assertTrue(member.getEventResults().get(2).getTimeResult().contains("00:00:49:03"));
        assertEquals(1, member.getEventResults().get(2).getPlacement());
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getEventResults().get(2).getDiscipline());
        assertTrue(member.getEventResults().get(3).getEventName().contains("SnS"));
        assertTrue(member.getEventResults().get(3).getTimeResult().contains("00:01:49:04"));
        assertEquals(4, member.getEventResults().get(3).getPlacement());
        assertEquals(SwimmingDiscipline.BRYSTSVØMNING, member.getEventResults().get(3).getDiscipline());
    }

    @Test
    public void testAddFourTrainingResultOverride() {
        String[] t = {"00:01:45:01", "00:01:45:02", "00:01:45:03", "00:01:45:04", "00:01:45:05", "00:01:45:06", "00:01:45:07", "00:01:45:08",};
        String[] d = {"01-05-2019", "02-05-2019", "03-05-2019", "04-05-2019", "05-05-2019", "06-05-2019", "07-05-2019", "08-05-2019"};
        String[] input = {"1", "1", "1", t[0], d[0], "1", "1", "1", "2", t[1], d[1], "1", "1", "1", "3", t[2], d[2], "1", "1", "1", "4", t[3], d[3], "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-01", 1);
        ctrl.getMembers().addMembers(member);
        TrainingResult result1 = new TrainingResult(SwimmingDiscipline.BUTTERFLY, t[4], d[4]);
        member.setTrainingResultButterfly(result1);
        db.storeTrainingResult(result1, member.getMember_ID());
        TrainingResult result2 = new TrainingResult(SwimmingDiscipline.CRAWL, t[5], d[5]);
        member.setTrainingResultCrawl(result2);
        db.storeTrainingResult(result2, member.getMember_ID());
        TrainingResult result3 = new TrainingResult(SwimmingDiscipline.RYGCRAWL, t[6], d[6]);
        member.setTrainingResultRygCrawl(result3);
        db.storeTrainingResult(result3, member.getMember_ID());
        TrainingResult result4 = new TrainingResult(SwimmingDiscipline.BRYSTSVØMNING, t[7], d[7]);
        member.setTrainingResultBrystsvømning(result4);
        db.storeTrainingResult(result4, member.getMember_ID());

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

        assertTrue(member.getTrainingResultButterfly().getTimeResult().contains("00:01:45:01"));
        assertTrue(member.getTrainingResultButterfly().getDate().contains("01-05-2019"));
        assertEquals(SwimmingDiscipline.BUTTERFLY, member.getTrainingResultButterfly().getDiscipline());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:01:45:02"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("02-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertTrue(member.getTrainingResultRygCrawl().getTimeResult().contains("00:01:45:03"));
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getTrainingResultRygCrawl().getDiscipline());
        assertTrue(member.getTrainingResultBrystsvømning().getTimeResult().contains("00:01:45:04"));
        assertTrue(member.getTrainingResultBrystsvømning().getDate().contains("04-05-2019"));
        assertEquals(SwimmingDiscipline.BRYSTSVØMNING, member.getTrainingResultBrystsvømning().getDiscipline());
    }

    @Test
    public void testAddTwoTrainingResultToTwoMembers() {
        String[] input = {"1", "1", "1", "00:00:45:67", "01-05-2019", "2", "1", "2", "00:01:45:67", "02-05-2019"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member1 = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03", 1);
        CompetitiveSwimmer member2 = new CompetitiveSwimmer(2, "Oscar", 25, true, "2019-05-02", 1);
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
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member1 = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03",1);
        CompetitiveSwimmer member2 = new CompetitiveSwimmer(2, "Oscar", 25, true, "2019-05-02",1);
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
        assertEquals(3, member1.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.BUTTERFLY, member1.getEventResults().get(0).getDiscipline());
        assertTrue(member2.getEventResults().get(0).getEventName().contains("NSK"));
        assertTrue(member2.getEventResults().get(0).getTimeResult().contains("00:01:19:71"));
        assertEquals(2, member2.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.CRAWL, member2.getEventResults().get(0).getDiscipline());
    }

}
