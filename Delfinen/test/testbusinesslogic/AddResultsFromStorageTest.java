/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbusinesslogic;

import businesslogic.CompetitiveSwimmer;
import businesslogic.Controller;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import datasource.FakeFacade;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import presentation.FakeUI;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class AddResultsFromStorageTest {

    @Test
    public void testAddOneTrainingResultFromStorage() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03");
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

        CompetitiveSwimmer member = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-03");
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
        assertEquals(3, member.getEventResults().get(0).getPlacement());
        assertEquals(SwimmingDiscipline.CRAWL, member.getEventResults().get(0).getDiscipline());
    }

    @Test
    public void testAddTwentyMixedResultFromStorageToFourMembers() {

        String[] input = {};
        FakeUI ui = new FakeUI(input);
        FakeFacade db = new FakeFacade();
        Controller ctrl = new Controller(ui, db);

        CompetitiveSwimmer member1 = new CompetitiveSwimmer(1, "Michael", 26, true, "2019-05-01");
        ctrl.getMembers().addMembers(member1);
        CompetitiveSwimmer member2 = new CompetitiveSwimmer(2, "Oscar", 25, true, "2019-05-02");
        ctrl.getMembers().addMembers(member2);
        CompetitiveSwimmer member3 = new CompetitiveSwimmer(3, "Jens", 22, true, "2019-05-03");
        ctrl.getMembers().addMembers(member3);
        Member member4 = new CompetitiveSwimmer(4, "Cassandra", 20, false, "2019-05-04");
        ctrl.getMembers().addMembers(member4);

        HashMap<String, String> map1 = new HashMap();
        map1.put("Swimming_Discipline", "BUTTERFLY");
        map1.put("Member_ID", "1");
        map1.put("Time_Result", "00:00:00:01");
        map1.put("Date", "01:04:2019");
        db.testTrainingResultsHashMapArray.add(map1);

        HashMap<String, String> map2 = new HashMap();
        map2.put("Swimming_Discipline", "CRAWL");
        map2.put("Member_ID", "1");
        map2.put("Time_Result", "00:00:00:02");
        map2.put("Date", "02:05:2019");
        db.testTrainingResultsHashMapArray.add(map2);

        HashMap<String, String> map3 = new HashMap();
        map3.put("Swimming_Discipline", "RYGCRAWL");
        map3.put("Member_ID", "1");
        map3.put("Time_Result", "00:00:00:03");
        map3.put("Date", "03:04:2019");
        db.testTrainingResultsHashMapArray.add(map3);

        HashMap<String, String> map4 = new HashMap();
        map4.put("Swimming_Discipline", "BRYSTSVØMNING");
        map4.put("Member_ID", "1");
        map4.put("Time_Result", "00:00:00:04");
        map4.put("Date", "04:05:2019");
        db.testTrainingResultsHashMapArray.add(map4);

        HashMap<String, String> map5 = new HashMap();
        map5.put("Swimming_Discipline", "BUTTERFLY");
        map5.put("Member_ID", "2");
        map5.put("Time_Result", "00:00:00:05");
        map5.put("Date", "05:05:2019");
        db.testTrainingResultsHashMapArray.add(map5);

        HashMap<String, String> map6 = new HashMap();
        map6.put("Swimming_Discipline", "CRAWL");
        map6.put("Member_ID", "2");
        map6.put("Time_Result", "00:00:00:06");
        map6.put("Date", "06:05:2019");
        db.testTrainingResultsHashMapArray.add(map6);

        HashMap<String, String> map7 = new HashMap();
        map7.put("Swimming_Discipline", "RYGCRAWL");
        map7.put("Member_ID", "2");
        map7.put("Time_Result", "00:00:00:07");
        map7.put("Date", "07:05:2019");
        db.testTrainingResultsHashMapArray.add(map7);

        HashMap<String, String> map8 = new HashMap();
        map8.put("Swimming_Discipline", "CRAWL");
        map8.put("Member_ID", "3");
        map8.put("Time_Result", "00:00:00:08");
        map8.put("Date", "08:05:2019");
        db.testTrainingResultsHashMapArray.add(map8);

        HashMap<String, String> map9 = new HashMap();
        map9.put("Swimming_Discipline", "RYGCRAWL");
        map9.put("Member_ID", "3");
        map9.put("Time_Result", "00:00:00:09");
        map9.put("Date", "09:05:2019");
        db.testTrainingResultsHashMapArray.add(map9);

        HashMap<String, String> map10 = new HashMap();
        map10.put("Swimming_Discipline", "RYGCRAWL");
        map10.put("Member_ID", "4");
        map10.put("Time_Result", "00:00:00:10");
        map10.put("Date", "10:05:2019");
        db.testTrainingResultsHashMapArray.add(map10);

        HashMap<String, String> map11 = new HashMap();
        map11.put("Swimming_Discipline", "CRAWL");
        map11.put("Event_Name", "Konkurrence 1");
        map11.put("Member_ID", "1");
        map11.put("Placement", "1");
        map11.put("Time_Result", "00:00:00:11");
        db.testEventResultsHashMapArray.add(map11);

        HashMap<String, String> map12 = new HashMap();
        map12.put("Swimming_Discipline", "CRAWL");
        map12.put("Event_Name", "Konkurrence 2");
        map12.put("Member_ID", "1");
        map12.put("Placement", "2");
        map12.put("Time_Result", "00:00:00:12");
        db.testEventResultsHashMapArray.add(map12);

        HashMap<String, String> map13 = new HashMap();
        map13.put("Swimming_Discipline", "CRAWL");
        map13.put("Event_Name", "Konkurrence 3");
        map13.put("Member_ID", "1");
        map13.put("Placement", "3");
        map13.put("Time_Result", "00:00:00:13");
        db.testEventResultsHashMapArray.add(map13);

        HashMap<String, String> map14 = new HashMap();
        map14.put("Swimming_Discipline", "CRAWL");
        map14.put("Event_Name", "Konkurrence 4");
        map14.put("Member_ID", "1");
        map14.put("Placement", "4");
        map14.put("Time_Result", "00:00:00:14");
        db.testEventResultsHashMapArray.add(map14);

        HashMap<String, String> map15 = new HashMap();
        map15.put("Swimming_Discipline", "CRAWL");
        map15.put("Event_Name", "Konkurrence 5");
        map15.put("Member_ID", "2");
        map15.put("Placement", "5");
        map15.put("Time_Result", "00:00:00:15");
        db.testEventResultsHashMapArray.add(map15);

        HashMap<String, String> map16 = new HashMap();
        map16.put("Swimming_Discipline", "CRAWL");
        map16.put("Event_Name", "Konkurrence 6");
        map16.put("Member_ID", "2");
        map16.put("Placement", "6");
        map16.put("Time_Result", "00:00:00:16");
        db.testEventResultsHashMapArray.add(map16);

        HashMap<String, String> map17 = new HashMap();
        map17.put("Swimming_Discipline", "CRAWL");
        map17.put("Event_Name", "Konkurrence 7");
        map17.put("Member_ID", "2");
        map17.put("Placement", "7");
        map17.put("Time_Result", "00:00:00:17");
        db.testEventResultsHashMapArray.add(map17);

        HashMap<String, String> map18 = new HashMap();
        map18.put("Swimming_Discipline", "CRAWL");
        map18.put("Event_Name", "Konkurrence 8");
        map18.put("Member_ID", "3");
        map18.put("Placement", "8");
        map18.put("Time_Result", "00:00:00:18");
        db.testEventResultsHashMapArray.add(map18);

        HashMap<String, String> map19 = new HashMap();
        map19.put("Swimming_Discipline", "CRAWL");
        map19.put("Event_Name", "Konkurrence 9");
        map19.put("Member_ID", "3");
        map19.put("Placement", "9");
        map19.put("Time_Result", "00:00:00:19");
        db.testEventResultsHashMapArray.add(map19);

        HashMap<String, String> map20 = new HashMap();
        map20.put("Swimming_Discipline", "CRAWL");
        map20.put("Event_Name", "Konkurrence 10");
        map20.put("Member_ID", "4");
        map20.put("Placement", "10");
        map20.put("Time_Result", "00:00:00:20");
        db.testEventResultsHashMapArray.add(map20);

        //act
        ctrl.createResultsFromStorage();
        for(String str : ui.output){
            System.out.println(str);
        }

        //assert
        assertEquals(4, member1.getEventResults().size());
        assertEquals(3, member2.getEventResults().size());
        assertEquals(2, member3.getEventResults().size());

        assertTrue(member1.getTrainingResultButterfly().getTimeResult().contains("01"));
        assertTrue(member1.getTrainingResultCrawl().getTimeResult().contains("02"));
        assertTrue(member1.getTrainingResultRygCrawl().getTimeResult().contains("03"));
        assertTrue(member1.getTrainingResultBrystsvømning().getTimeResult().contains("04"));
        assertTrue(member2.getTrainingResultButterfly().getTimeResult().contains("05"));
        assertTrue(member2.getTrainingResultCrawl().getTimeResult().contains("06"));
        assertTrue(member2.getTrainingResultRygCrawl().getTimeResult().contains("07"));
        assertTrue(member3.getTrainingResultCrawl().getTimeResult().contains("08"));
        assertTrue(member3.getTrainingResultRygCrawl().getTimeResult().contains("09"));
        
        assertTrue(member1.getEventResults().get(0).getTimeResult().contains("11"));
        assertTrue(member1.getEventResults().get(1).getTimeResult().contains("12"));
        assertTrue(member1.getEventResults().get(2).getTimeResult().contains("13"));
        assertTrue(member1.getEventResults().get(3).getTimeResult().contains("14"));
        assertTrue(member2.getEventResults().get(0).getTimeResult().contains("15"));
        assertTrue(member2.getEventResults().get(1).getTimeResult().contains("16"));
        assertTrue(member2.getEventResults().get(2).getTimeResult().contains("17"));
        assertTrue(member3.getEventResults().get(0).getTimeResult().contains("18"));
        assertTrue(member3.getEventResults().get(1).getTimeResult().contains("19"));
        
        //Fejl hvis vi retter at der ikke længere bliver printet fejl under startup.
        assertTrue(ui.output.get(0).contains("FEJL: Medlem med ID: 4 er ikke en konkurrence svømmer"));
        assertTrue(ui.output.get(0).contains("FEJL: Medlem med ID: 4 er ikke en konkurrence svømmer"));

        

    }

}
