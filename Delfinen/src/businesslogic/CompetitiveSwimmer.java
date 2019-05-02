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
    private TrainingResult trainingResult;
    
    public CompetitiveSwimmer(String name, int age, boolean competetiveSwimmer) {
        super(name, age, competetiveSwimmer);
        this.eventResults = new ArrayList();
    }
    
}
