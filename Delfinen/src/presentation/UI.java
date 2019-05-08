/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.TrainingResult;
import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public interface UI {

    // New Member Calls
    public String getNewMemberName();

    public int getNewMemberAge();

    public boolean getNewMemberActivityForm();

    public void displayMainMenu();

    public String getMenuSelection();

    public String getNewMemberSignUpDate();

    public int getMemberID();

    public int resultType();

    public int swimmingDiscipline();

    public String timeResult();

    public String resultDate();

    public String getPasswordForDatabase();

    public String getEventName();

    public int getEventPlacement();

    public int getEventNeedingDeleting(ArrayList<EventResult> eventResults);

    public void displayEditResultMenu();

    public boolean confirmTrainingResultOverride(TrainingResult oldTrainingResult, TrainingResult newTrainingResult);

    public void notCompetitveSwimmerMessage(int memberID);

    public void showMembersMenu();

    public void showAllMembers(ArrayList<Member> membersList);

    public void showNonCompetitiveSwimmers(ArrayList<Member> membersList);

    public void showCompetitiveSwimmers(ArrayList<Member> membersList);

    
    public void showResultsMenu();

    public void showResults(ArrayList<Member> membersList);
    
    public void showTopFiveResults (ArrayList<Member> membersList);

    public void showYoungTeamMembers(ArrayList<Member> membersList);

    public void showSeniorTeamMembers(ArrayList<Member> membersList);
    
    public void showTopTimes(ArrayList <String> times);

}
