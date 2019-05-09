/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

/**
 *
 * @author Cassandra
 */
public class Trainer {
    private int TrainerID;
    private String TrainerName;
    
    public Trainer(int TrainerID, String TrainerName) {
        this.TrainerID = TrainerID;
        this.TrainerName = TrainerName;
    }

    public int getTrainerID() {
        return TrainerID;
    }

    public void setTrainerID(int TrainerID) {
        this.TrainerID = TrainerID;
    }

    public String getTrainerName() {
        return TrainerName;
    }

    public void setTrainerName(String TrainerName) {
        this.TrainerName = TrainerName;
    }
    
    @Override
    public String toString(){
        return getTrainerID() + ". " + getTrainerName();
    }
    
}
