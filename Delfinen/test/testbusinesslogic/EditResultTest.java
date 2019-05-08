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
import businesslogic.TrainingResult;
import datasource.FakeFacade;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class EditResultTest {

    @Test
    public void testEditOneTrainingResult() {
        String[] input = {"1", "1", "2", "00:00:45:02", "02-05-2019", "1"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);
        TrainingResult trainingResult = new TrainingResult(SwimmingDiscipline.CRAWL, "00:00:45:01", "01-05-2019");
        member.setTrainingResultCrawl(trainingResult);
        db.storeTrainingResult(trainingResult, member.getMember_ID());

        //act
        ctrl.editResult();

        //assert
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultRygCrawl());
        assertNull(member.getTrainingResultBrystsvømning());
        assertNotNull(member.getTrainingResultCrawl());
        assertEquals(SwimmingDiscipline.CRAWL, member.getTrainingResultCrawl().getDiscipline());
        assertTrue(member.getTrainingResultCrawl().getTimeResult().contains("02"));
        assertTrue(member.getTrainingResultCrawl().getDate().contains("02"));
    }

    @Test
    public void testEditOneEventResult() {
        String[] input = {"1", "2", "1", "3", "00:01:49:02", "Ny Svømmer Konkurrence", "2"};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);
        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);
        EventResult eventResult = new EventResult("Gammel Svømmer Konkurrence", 1, "00:00:45:01", SwimmingDiscipline.CRAWL);
        member.addEventResult(eventResult);
        db.storeEventResult(eventResult, member.getMember_ID());

        //act
        ctrl.editResult();
        for (String str : ui.output){
            System.out.println(str);
        }

        //assert
        assertEquals(1,member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny"));
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("02"));
        assertEquals(SwimmingDiscipline.RYGCRAWL,member.getEventResults().get(0).getDiscipline());
        assertEquals(2,member.getEventResults().get(0).getPlacement());

    }

}
