/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public class FakeUI implements UI {

    private int index = 0;
    public ArrayList<String> output = new ArrayList();
    private String[] input;

    public FakeUI(String[] input) {
        this.input = input;
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    @Override
    public String getNewMemberName() {
        output.add("Nyt medlems navn: ");
        return input[index++];
    }

    @Override
    public int getNewMemberAge() {
        output.add("Nyt medlems alder: ");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public boolean getNewMemberActivityForm() {
        output.add("Skriv medlem op som: ");
        output.add("1. Motionist");
        output.add("2. Konkurrencesvømmer");
        return Integer.parseInt(input[index++]) == 2;
    }

    @Override
    public void displayMainMenu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String mainMenuSelection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNewMemberSignUpDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
