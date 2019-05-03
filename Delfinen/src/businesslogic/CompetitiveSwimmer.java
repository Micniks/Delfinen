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

    private ArrayList<EventResults> eventResults;
    private TrainingResults[] trainingResults;

    public CompetitiveSwimmer(int member_ID, String name, int age, boolean competetiveSwimmer, String signUpDate) {
        super(member_ID, name, age, competetiveSwimmer, signUpDate);
        this.eventResults = new ArrayList();
        trainingResults = new TrainingResults[4];
    }

    public ArrayList<EventResults> getEventResults() {
        return eventResults;
    }

    public TrainingResults[] getTrainingResults() {
        return trainingResults;
    }

    public TrainingResults getTrainingResultButterfly() {
        return trainingResults[0];
    }

    public TrainingResults getTrainingResultCrawl() {
        return trainingResults[1];
    }

    public TrainingResults getTrainingResultRygCrawl() {
        return trainingResults[2];
    }

    public TrainingResults getTrainingResultBrystsvømning() {
        return trainingResults[3];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(", alder ");
        sb.append(getAge());
        sb.append(", konkurrencesvømmer, ");
        if (!isActiveMember()) {
            sb.append("ikke ");
        }
        sb.append("aktivt medlem.");
        if (getDept() > 0) {
            sb.append(" Gæld: ");
            sb.append(getDept());
        }
        return sb.toString();
    }

}
