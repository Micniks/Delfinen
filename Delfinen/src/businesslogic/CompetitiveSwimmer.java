/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class CompetitiveSwimmer extends Member {

    private ArrayList<EventResult> eventResults;
    private TrainingResult[] trainingResults;
    private int trainerID;

    public CompetitiveSwimmer(int member_ID, String name, int age, boolean competitiveSwimmer, String signUpDate, int trainerID) {
        super(member_ID, name, age, competitiveSwimmer, signUpDate);
        this.eventResults = new ArrayList();
        trainingResults = new TrainingResult[4];
        this.trainerID = trainerID;
        
        
    }

    public int getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    public CompetitiveSwimmer(int member_ID, String name, int age, boolean activeMember, boolean competitiveSwimmer, double debt, String signUpDate, String lastAddedDebtDate, int trainerID) {
        super(member_ID, name, age, activeMember, competitiveSwimmer, debt, signUpDate, lastAddedDebtDate);
        this.eventResults = new ArrayList();
        trainingResults = new TrainingResult[4];
        this.trainerID = trainerID;
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
