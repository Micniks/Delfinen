/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.EventResult;
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
public class DeleteResultTest {

    @Test
    public void testDeleteOneTrainingResult() {
        String[] input = {"1", "1", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019", 1);
        TrainingResult trainingResult = new TrainingResult(SwimmingDiscipline.CRAWL, "00:00:45:67", "03-05-2019");
        member.setTrainingResultCrawl(trainingResult);
        db.storeTrainingResult(trainingResult, member.getMember_ID());
        ctrl.getMembers().addMembers(member);

        //pre-asserts
        assertNotNull(member.getTrainingResultCrawl());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        //act
        ctrl.deleteResult();

        //assert
        assertTrue(ui.output.get(0).contains("Indtast medlems ID-nummer:"));
        assertTrue(ui.output.get(1).contains("Vælg resultattype:"));
        assertTrue(ui.output.get(2).contains("1: Træningsresultat"));
        assertTrue(ui.output.get(4).contains("Vælg svømmediscplin:"));
        assertTrue(ui.output.get(6).contains("2. Crawl"));
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultRygCrawl());
        assertNull(member.getTrainingResultCrawl());

    }

    @Test
    public void testDeleteOneEventResult() {
        String[] input = {"1", "2", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019", 1);
        EventResult eventResult = new EventResult("Ny Svømmer Konkurrence", 3, "00:00:45:67", SwimmingDiscipline.CRAWL);
        member.addEventResult(eventResult);
        db.storeEventResult(eventResult, member.getMember_ID());
        ctrl.getMembers().addMembers(member);

        //pre-asserts
        assertEquals(1, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        //act
        ctrl.deleteResult();

        //assert
        assertTrue(ui.output.get(0).contains("Indtast medlems ID-nummer:"));
        assertTrue(ui.output.get(1).contains("Vælg resultattype:"));
        assertTrue(ui.output.get(3).contains("2: Konkurrenceresultat"));
        assertTrue(ui.output.get(4).contains("Indtast nr. for konkurrence der skal slettes:"));
        assertTrue(ui.output.get(5).contains("1. " + eventResult.toString()));
        assertEquals(0, member.getEventResults().size());

    }

    @Test
    public void testDeleteOneTrainingResultOfFour() {
        String[] input = {"1", "1", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019", 1);
        TrainingResult trainingResult1 = new TrainingResult(SwimmingDiscipline.CRAWL, "00:00:45:67", "03-05-2019");
        member.setTrainingResultCrawl(trainingResult1);
        db.storeTrainingResult(trainingResult1, member.getMember_ID());
        TrainingResult trainingResult2 = new TrainingResult(SwimmingDiscipline.RYGCRAWL, "00:01:45:67", "03-05-2019");
        member.setTrainingResultRygCrawl(trainingResult2);
        db.storeTrainingResult(trainingResult2, member.getMember_ID());
        TrainingResult trainingResult3 = new TrainingResult(SwimmingDiscipline.BRYSTSVØMNING, "00:02:45:67", "03-05-2019");
        member.setTrainingResultBrystsvømning(trainingResult3);
        db.storeTrainingResult(trainingResult3, member.getMember_ID());
        TrainingResult trainingResult4 = new TrainingResult(SwimmingDiscipline.BUTTERFLY, "00:03:45:67", "03-05-2019");
        member.setTrainingResultButterfly(trainingResult4);
        db.storeTrainingResult(trainingResult4, member.getMember_ID());
        ctrl.getMembers().addMembers(member);

        //pre-asserts
        assertNotNull(member.getTrainingResultCrawl());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        //act
        ctrl.deleteResult();

        //assert
        assertTrue(ui.output.get(0).contains("Indtast medlems ID-nummer:"));
        assertTrue(ui.output.get(1).contains("Vælg resultattype:"));
        assertTrue(ui.output.get(2).contains("1: Træningsresultat"));
        assertTrue(ui.output.get(4).contains("Vælg svømmediscplin:"));
        assertTrue(ui.output.get(6).contains("2. Crawl"));
        assertNotNull(member.getTrainingResultBrystsvømning());
        assertNotNull(member.getTrainingResultButterfly());
        assertNotNull(member.getTrainingResultRygCrawl());
        assertNull(member.getTrainingResultCrawl());

    }

    @Test
    public void testDeleteOneEventResultOfThree() {
        String[] input = {"1", "2", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019", 1);
        EventResult eventResult1 = new EventResult("Ny Svømmer Konkurrence", 3, "00:01:45:67", SwimmingDiscipline.CRAWL);
        member.addEventResult(eventResult1);
        db.storeEventResult(eventResult1, member.getMember_ID());
        EventResult eventResult2 = new EventResult("NSK", 2, "00:00:45:67", SwimmingDiscipline.RYGCRAWL);
        member.addEventResult(eventResult2);
        db.storeEventResult(eventResult2, member.getMember_ID());
        EventResult eventResult3 = new EventResult("NSK-2", 1, "00:00:35:67", SwimmingDiscipline.BRYSTSVØMNING);
        member.addEventResult(eventResult3);
        db.storeEventResult(eventResult3, member.getMember_ID());
        ctrl.getMembers().addMembers(member);

        //pre-asserts
        assertEquals(3, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:01:45:67"));
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertTrue(member.getEventResults().get(1).getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getEventResults().get(1).getEventName().contains("NSK"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getEventResults().get(1).getDiscipline());
        assertEquals(2, member.getEventResults().get(1).getPlacement());
        assertTrue(member.getEventResults().get(2).getTimeResult().contains("00:00:35:67"));
        assertTrue(member.getEventResults().get(2).getEventName().contains("NSK-2"));
        assertEquals(SwimmingDiscipline.BRYSTSVØMNING, member.getEventResults().get(2).getDiscipline());
        assertEquals(1, member.getEventResults().get(2).getPlacement());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        //act
        ctrl.deleteResult();

        //assert
        assertTrue(ui.output.get(0).contains("Indtast medlems ID-nummer:"));
        assertTrue(ui.output.get(1).contains("Vælg resultattype:"));
        assertTrue(ui.output.get(3).contains("2: Konkurrenceresultat"));
        assertTrue(ui.output.get(4).contains("Indtast nr. for konkurrence der skal slettes:"));
        assertTrue(ui.output.get(6).contains("2. " + eventResult2.toString()));
        assertEquals(2, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:01:45:67"));
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertTrue(member.getEventResults().get(1).getTimeResult().contains("00:00:35:67"));
        assertTrue(member.getEventResults().get(1).getEventName().contains("NSK-2"));
        assertEquals(SwimmingDiscipline.BRYSTSVØMNING, member.getEventResults().get(1).getDiscipline());
        assertEquals(1, member.getEventResults().get(1).getPlacement());

    }

    @Test
    public void testDeleteFourTrainingResultOfFour() {
        String[] input = {"1", "1", "1", "1", "1", "2", "1", "1", "4", "1", "1", "3"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        Trainer trainer = new Trainer(1, "Bob");
        ctrl.getTrainers().addTrainers(trainer);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019",1);
        TrainingResult trainingResult1 = new TrainingResult(SwimmingDiscipline.CRAWL, "00:00:45:67", "03-05-2019");
        member.setTrainingResultCrawl(trainingResult1);
        db.storeTrainingResult(trainingResult1, member.getMember_ID());
        TrainingResult trainingResult2 = new TrainingResult(SwimmingDiscipline.RYGCRAWL, "00:01:45:67", "03-05-2019");
        member.setTrainingResultRygCrawl(trainingResult2);
        db.storeTrainingResult(trainingResult2, member.getMember_ID());
        TrainingResult trainingResult3 = new TrainingResult(SwimmingDiscipline.BRYSTSVØMNING, "00:02:45:67", "03-05-2019");
        member.setTrainingResultBrystsvømning(trainingResult3);
        db.storeTrainingResult(trainingResult3, member.getMember_ID());
        TrainingResult trainingResult4 = new TrainingResult(SwimmingDiscipline.BUTTERFLY, "00:03:45:67", "03-05-2019");
        member.setTrainingResultButterfly(trainingResult4);
        db.storeTrainingResult(trainingResult4, member.getMember_ID());
        ctrl.getMembers().addMembers(member);

        //pre-asserts
        assertNotNull(member.getTrainingResultCrawl());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("00:00:45:67"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("03-05-2019"));
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertEquals(1, ctrl.getMembers().getMembersList().size());

        //act
        ctrl.deleteResult();
        ctrl.deleteResult();
        ctrl.deleteResult();
        ctrl.deleteResult();

        //assert
        assertTrue(ui.output.get(0).contains("Indtast medlems ID-nummer:"));
        assertTrue(ui.output.get(1).contains("Vælg resultattype:"));
        assertTrue(ui.output.get(2).contains("1: Træningsresultat"));
        assertTrue(ui.output.get(4).contains("Vælg svømmediscplin:"));
        assertTrue(ui.output.get(6).contains("2. Crawl"));
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultRygCrawl());
        assertNull(member.getTrainingResultCrawl());

    }

}
