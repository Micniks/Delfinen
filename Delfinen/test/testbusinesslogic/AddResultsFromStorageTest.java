/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.SwimmingDiscipline;
import datasource.FakeFacade;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard
 */
public class AddResultsFromStorageTest {

    @Test
    public void testAddOneTrainingResultFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);

        HashMap<String, String> map = new HashMap();
        map.put("Swimming_Discipline", "RYGCRAWL");
        map.put("Member_ID", "1");
        map.put("Time_Result", "00:00:00:01");
        map.put("Date", "07:05:2019");
        db.testTrainingResultsHashMapArray.add(map);

        //act
        ctrl.createResultsFromStorage();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNotNull(member.getTrainingResultRygCrawl());
        assertEquals(1, ctrl.getMembers().getMembersList().size());
        assertTrue(member.getTrainingResultRygCrawl().getTimeResult().contains("00:00:00:01"));
        assertTrue(member.getTrainingResultRygCrawl().getDate().contains("07:05:2019"));
        assertEquals(SwimmingDiscipline.RYGCRAWL, member.getTrainingResultRygCrawl().getDiscipline());

    }

    @Test
    public void testAddOneEventResultFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "03-05-2019");
        ctrl.getMembers().addMembers(member);

        HashMap<String, String> map = new HashMap();
        map.put("Swimming_Discipline", "CRAWL");
        map.put("Event_Name", "Ny Svømmer Konkurrence");
        map.put("Member_ID", "1");
        map.put("Placement", "3");
        map.put("Time_Result", "00:00:00:01");
        db.testEventResultsHashMapArray.add(map);

        //act
        ctrl.createResultsFromStorage();

        //assert
        assertNull(member.getTrainingResultBrystsvømning());
        assertNull(member.getTrainingResultButterfly());
        assertNull(member.getTrainingResultCrawl());
        assertNull(member.getTrainingResultRygCrawl());
        assertEquals(1, member.getEventResults().size());
        assertTrue(member.getEventResults().get(0).getEventName().contains("Ny Svømmer Konkurrence"));
        assertTrue(member.getEventResults().get(0).getTimeResult().contains("00:00:00:01"));
        assertEquals(3,member.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
    }
    
        @Test
    public void testAddTwentyMixedResultFromStorageToFiveMembers() {
        
    
    }
    
    
    
}
