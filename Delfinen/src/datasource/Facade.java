/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import businesslogic.Trainer;
import businesslogic.TrainingResult;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public interface Facade {

    public void storageMember(Member member);

    public ArrayList<HashMap<String, String>> getMembers();

    public int readHighestMemberID();

    public void topFiveTrainingResults();

    void updateMember(Member member);

    public void storeTrainingResult(TrainingResult trainingResult, int member_ID);

    public void storeEventResult(EventResult eventResult, int member_ID);

    public void deleteTrainingResult(int memberID, SwimmingDiscipline swimmingDiscipline);

    public void deleteEventResult(int memberID, String eventName, SwimmingDiscipline eventSD);

    public Iterable<HashMap<String, String>> getTrainingResults();

    public Iterable<HashMap<String, String>> getEventResults();
    
    public ArrayList <String> getTopFiveTrainingResults();
    
    public ArrayList <String> getTopFiveEventResults();
    
    public void removeMember(int memberID);
    
    public void storeTrainer(Trainer trainer);
    
    public int readHighestTrainerID();
    
    public ArrayList<HashMap<String, String>> getTrainers();
}
