/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import businesslogic.EventResult;
import businesslogic.TrainingResult;
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
        output.add("");
        output.add("   Svømmeklubben Delfinen");
        output.add("");
        output.add("1. Opret nyt medlem");
        output.add("2. Resultater");
        output.add("3. Afslut Program");
    }

    @Override
    public void displayResultMenu() {
        output.add("");
        output.add("   Svømmeklubben Delfinen - Resultater");
        output.add("");
        output.add("1. Opret Resultat");
        output.add("2. Ændre Resultat");
        output.add("3. Slet Resultat");
        output.add("4. Gå tilbage");
    }

    @Override
    public String getMenuSelection() {
        return input[index++];
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
    public String timeResult() {
        output.add("Indtast tidsresultatet i formattet TT:MM:SS:MM");
        return input[index++];
    }

    @Override
    public String resultDate() {
        output.add("Indtast dato for træningsresultatet i formattet DD:MM:ÅÅÅÅ");
        return input[index++];
    }

    @Override
    public String getPasswordForDatabase() {
        // This is not meant to be tested with.
        return null;
    }

    @Override
    public String getEventName() {
        output.add("Indtast navnet for konkurrencen: ");
        return input[index++];
    }

    @Override
    public int getEventPlacement() {
        output.add("Indtast svømmerens placeing i konkurrencen: ");
        return Integer.parseInt(input[index++]);
    }

    @Override
    public int getEventNeedingDeleting(ArrayList<EventResult> eventResults) {
        output.add("Indtast nr. for konkurrence der skal slettes: ");
        for (int i = 1; i <= eventResults.size(); i++) {
            output.add(Integer.toString(i) + ". " + eventResults.get(i - 1).toString());
        }
        return Integer.parseInt(input[index++]) - 1;
    }

    @Override
    public boolean confirmTrainingResultOverride(TrainingResult oldTrainingResult, TrainingResult newTrainingResult) {
        output.add("Vil du overskrive: " + oldTrainingResult.toString());
        output.add("Med følgende: " + newTrainingResult.toString());
        output.add("1. Ja");
        output.add("2. Nej");
        return Integer.parseInt(input[index++]) == 1;
    }

}
