/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

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

    public String mainMenuSelection();
    public String getNewMemberSignUpDate();

    public int getMemberID();

    public int resultType();

    public int swimmingDiscipline();

    public String timeResult();

    public String resultDate();

    public String getPasswordForDatabase();

    public String getEventName();

    public int getEventPlacement();

}
