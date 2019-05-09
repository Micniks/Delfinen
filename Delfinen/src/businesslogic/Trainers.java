/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package businesslogic;

import datasource.Facade;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Cassandra
 */
public class Trainers {
    private ArrayList<Trainer> trainerList;
    private Facade db;
    
    public Trainers(Facade db) {
        trainerList = new ArrayList();
        this.db = db;
        getTrainersFromStorage(trainerList);
       
    }
    
     public ArrayList<Trainer> getTrainersList() {
        return trainerList;
    }

    public void addTrainers(Trainer trainer) {
        trainerList.add(trainer);
    }
    private void getTrainersFromStorage(ArrayList<Trainer> trainers) {
        ArrayList<HashMap<String, String>> trainerInfo = db.getTrainers();
        
        for(HashMap<String, String> map : trainerInfo){
            String name = map.get("Name");
          
        }
    }        
}
