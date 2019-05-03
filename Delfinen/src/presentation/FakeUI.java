/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String signUpDate = today.format(formatter);
        return signUpDate;
    }

    @Override
    public int getMemberID() {
        output.add("Indtast medlems ID-nummer: ");
        return Integer.parseInt(input[index++]);
        
    }

    @Override
    public int resultType() {
        output.add("Vælg resultattype: ");
        output.add("1: Træningsresultat");
        output.add("2: Konkurrenceresultat");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public int swimmingDiscipline() {
        output.add("Vælg svømmediscplin:");
        output.add("1. Butterfly");
        output.add("2. Crawl");
        output.add("3. Rycrawl");
        output.add("4. Brystsvømning");
        return Integer.parseInt(input[index++]);
        
    }

    @Override
    public String trainingResult() {
        output.add("Indtast træningresultatet i formattet TT:MM:SS:MM");
        return (input[index++]);
    }

    @Override
    public String resultDate() {
        output.add("Indtast dato for træningsresultatet i formattet DD:MM:ÅÅÅÅ");
        return (input[index++]);
    }

}
