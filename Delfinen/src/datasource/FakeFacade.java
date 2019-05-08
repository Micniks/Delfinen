/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import businesslogic.TrainingResult;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Michael N. Korsgaard
 */
public class FakeFacade implements Facade {

    public ArrayList<HashMap<String, String>> testMembersHashMapArray;
    public ArrayList<HashMap<String, String>> testTrainingResultsHashMapArray;
    public ArrayList<HashMap<String, String>> testEventResultsHashMapArray;

    public FakeFacade() {
        this.testMembersHashMapArray = new ArrayList();
        this.testTrainingResultsHashMapArray = new ArrayList();
        this.testEventResultsHashMapArray = new ArrayList();
    }

    @Override
    public void storageMember(Member member) {
        HashMap<String, String> map = new HashMap();
        map.put("Member_ID", Integer.toString(member.getMember_ID()));
        map.put("Name", member.getName());
        map.put("Age", Integer.toString(member.getAge()));
        map.put("Competitive_Swimmer", Boolean.toString(member.isCompetetiveSwimmer()));
        map.put("Active_Member", Boolean.toString(member.isActiveMember()));
        map.put("Debt", Double.toString(member.getDebt()));
        map.put("Sign_Up_Date", member.getSignUpDate());
        testMembersHashMapArray.add(map);
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        return testMembersHashMapArray;
    }

    @Override
    public int readHighestMemberID() {
        return 1;
    }

    @Override
    public void topFiveTrainingResults() {

    }

    @Override
    public void updateMember(Member member) {
        int index = 0;
        for(HashMap<String, String> haspMap : testMembersHashMapArray){
            if(haspMap.get("Member_ID").contains(Integer.toString(member.getMember_ID()))){
                testMembersHashMapArray.remove(index);
                break;
            }
            index++;
        }
        storageMember(member);
    }

    @Override
    public void deleteMember() {

    }

    @Override
    public void storeTrainingResult(TrainingResult trainingResult, int member_ID) {
        HashMap<String, String> map = new HashMap();
        map.put("Swimming_Discipline", trainingResult.getDiscipline().toString());
        map.put("Member_ID", Integer.toString(member_ID));
        map.put("Time_Result", trainingResult.getTimeResult());
        map.put("Result_Date", trainingResult.getDate());
        testTrainingResultsHashMapArray.add(map);
    }

    @Override
    public void storeEventResult(EventResult eventResult, int member_ID) {
        HashMap<String, String> map = new HashMap();
        map.put("Swimming_Discipline", eventResult.getDiscipline().toString());
        map.put("Event_Name", eventResult.getEventName());
        map.put("Member_ID", Integer.toString(member_ID));
        map.put("Placement", Integer.toString(eventResult.getPlacement()));
        map.put("Time_Result", eventResult.getTimeResult());
        testEventResultsHashMapArray.add(map);
    }

    @Override
    public void deleteTrainingResult(int memberID, SwimmingDiscipline swimmingDiscipline) {
        String compareID;
        String compareSD;
        boolean correctMemberID;
        boolean correctSwimmingDiscipline;
        for (int i = 0; i < testTrainingResultsHashMapArray.size(); i++) {
            compareID = testTrainingResultsHashMapArray.get(i).get("Member_ID");
            compareSD = testTrainingResultsHashMapArray.get(i).get("Swimming_Discipline");
            correctMemberID = compareID.contains(Integer.toString(memberID));
            correctSwimmingDiscipline = compareSD.contains(swimmingDiscipline.toString());
            if (correctMemberID && correctSwimmingDiscipline) {
                testTrainingResultsHashMapArray.remove(i);
                break;
            }
        }
    }

    @Override
    public void deleteEventResult(int memberID, String eventName, SwimmingDiscipline eventSD) {
        String compareMemberID;
        String compareDiscipline;
        String compareEventName;
        boolean correctMemberID;
        boolean correctSwimmingDiscipline;
        boolean correctEventName;
        for (int i = 0; i < testEventResultsHashMapArray.size(); i++) {
            compareMemberID = testEventResultsHashMapArray.get(i).get("Member_ID");
            compareDiscipline = testEventResultsHashMapArray.get(i).get("Swimming_Discipline");
            compareEventName = testEventResultsHashMapArray.get(i).get("Event_Name");
            correctMemberID = compareMemberID.contains(Integer.toString(memberID));
            correctSwimmingDiscipline = compareDiscipline.contains(eventSD.toString());
            correctEventName = compareEventName.contains(eventName);
            if (correctMemberID && correctSwimmingDiscipline && correctEventName) {
                testEventResultsHashMapArray.remove(i);
                break;
            }
        }
    }

    @Override
    public Iterable<HashMap<String, String>> getTrainingResults() {
        return testTrainingResultsHashMapArray;
    }

    @Override
    public Iterable<HashMap<String, String>> getEventResults() {
        return testEventResultsHashMapArray;
    }

    @Override
    public ArrayList<String> getTopFiveTrainingResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
