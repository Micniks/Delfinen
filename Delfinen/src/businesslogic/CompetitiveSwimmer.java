/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CompetitiveSwimmer extends Member {

    private ArrayList<EventResult> eventResults;
    private TrainingResult[] trainingResults;

    public CompetitiveSwimmer(int member_ID, String name, int age, boolean competetiveSwimmer, String signUpDate) {
        super(member_ID, name, age, competetiveSwimmer, signUpDate);
        this.eventResults = new ArrayList();
        trainingResults = new TrainingResult[4];
    }

    public CompetitiveSwimmer(int member_ID, String name, int age, boolean activeMember, boolean competetiveSwimmer, double debt, String signUpDate, String payDate) {
        super(member_ID, name, age, activeMember, competetiveSwimmer, debt, signUpDate, payDate);
        this.eventResults = new ArrayList();
        trainingResults = new TrainingResult[4];
    }

    public ArrayList<EventResult> getEventResults() {
        return eventResults;
    }
    
    public void addEventResult(EventResult result) {
        eventResults.add(result);
    }

    public TrainingResult[] getTrainingResults() {
        return trainingResults;
    }

    public TrainingResult getTrainingResultButterfly() {
        return trainingResults[0];
    }

    public TrainingResult getTrainingResultCrawl() {
        return trainingResults[1];
    }

    public TrainingResult getTrainingResultRygCrawl() {
        return trainingResults[2];
    }

    public TrainingResult getTrainingResultBrystsvømning() {
        return trainingResults[3];
    }
    
    public void setTrainingResultButterfly(TrainingResult tr){
        trainingResults[0] = tr;
    }    
    
    public void setTrainingResultCrawl(TrainingResult tr){
        trainingResults[1] = tr;
    }    
    
    public void setTrainingResultRygCrawl(TrainingResult tr){
        trainingResults[2] = tr;
    }    
    
    public void setTrainingResultBrystsvømning(TrainingResult tr){
        trainingResults[3] = tr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getMember_ID()).append(". ");
        sb.append(getName()).append(", alder ");
        sb.append(getAge()).append(", konkurrencesvømmer, ");
        if (!isActiveMember()) {
            sb.append("ikke ");
        }
        sb.append("aktivt medlem.");
        if (getDebt() > 0) {
            sb.append(" Gæld: ");
            sb.append(getDebt());
        }
        return sb.toString();
    }

}
