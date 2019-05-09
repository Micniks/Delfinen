/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import businesslogic.CompetitiveSwimmer;
import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.TrainingResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
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
    public void displayEditResultMenu() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-LLLL-dd");
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

    @Override
    public void notCompetitveSwimmerMessage(int memberID) {
        output.add("FEJL: Medlem med ID: " + memberID + " er ikke en konkurrence svømmer.");
    }

    @Override
    public void showMembersMenu() {
        output.add("");
        output.add("   Svømmeklubben Delfinen - Medlemmer");
        output.add("");
        output.add("1. Vis alle");
        output.add("2. Vis motionister");
        output.add("3. Vis konkurrence-svømmere");
        output.add("4. Gå tilbage");
    }

    @Override
    public void showAllMembers(ArrayList<Member> membersList) {
        for (Member member : membersList) {
            output.add(member.toString());
        }
    }

    @Override
    public void showNonCompetitiveSwimmers(ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (!member.isCompetitiveSwimmer()) {
                output.add(member.toString());
            }
        }
    }

    @Override
    public void showCompetitiveSwimmers(ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (member.isCompetitiveSwimmer()) {
                output.add(member.toString());
            }
        }
    }

    @Override
    public void showResults(ArrayList<Member> membersList) {
        ArrayList<String> resultsList;
        CompetitiveSwimmer competitiveSwimmer;
        for (Member member : membersList) {
            resultsList = new ArrayList();
            if (member.isCompetitiveSwimmer()) {
                competitiveSwimmer = (CompetitiveSwimmer) member;

                if (competitiveSwimmer.getTrainingResultBrystsvømning() != null) {
                    resultsList.add(competitiveSwimmer.getTrainingResultBrystsvømning().toString());
                }
                if (competitiveSwimmer.getTrainingResultButterfly() != null) {
                    resultsList.add(competitiveSwimmer.getTrainingResultButterfly().toString());
                }
                if (competitiveSwimmer.getTrainingResultCrawl() != null) {
                    resultsList.add(competitiveSwimmer.getTrainingResultCrawl().toString());
                }
                if (competitiveSwimmer.getTrainingResultRygCrawl() != null) {
                    resultsList.add(competitiveSwimmer.getTrainingResultRygCrawl().toString());
                }
                for (EventResult eventResult : competitiveSwimmer.getEventResults()) {
                    if (competitiveSwimmer.getEventResults() != null) {
                        resultsList.add(eventResult.toString());
                    }

                }
                if (resultsList.size() > 0) {
                    output.add(competitiveSwimmer.toString());
                    for (String result : resultsList) {
                        output.add(result);

                    }
                }

            }
        }
    }

    @Override
    public void showResultsMenu() {
        output.add("   Svømmeklubben Delfinen - Vis Resultater");
        output.add("");
        output.add("1. Vis alle resultater");
        output.add("2. Vis træningsresultater");
        output.add("3. Vis konkurrenceresultater");
        output.add("4. Vis Top-5 resultater");
        output.add("5. Gå tilbage");
    }

    @Override
    public void showYoungTeamMembers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (member.getAge() < 18) {
                output.add(member.toString());
            }
        }
    }

    @Override
    public void showSeniorTeamMembers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (member.getAge() >= 18) {
                output.add(member.toString());
            }
        }
    }

    @Override
    public void showTopTimes(ArrayList<String> times) {
        for (String time : times) {
            output.add(time);
        }
    }

    @Override
    public void showDebt(ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (member.getDebt() > 0) {
                output.add("MedlemsID: ");
                output.add(Integer.toString(member.getMember_ID()));
                output.add(", Medlemsnavn: ");
                output.add(member.getName());
                output.add(", Gæld: ");
                output.add(Double.toString(member.getDebt()));
                output.add("");
            }
        }
    }

    @Override
    public Boolean confirmPayDebt(Member member) {
        output.add("Gennemfør betaling for medlem: ");
        output.add(member.toString());
        output.add("");
        output.add("1. Bekræft");
        output.add("2. Annuller");
        return input[index++].contains("1");
    }

    @Override
    public String getNewTrainerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void errorMessage(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
