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
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael N. Korsgaard
 */
public class SystemUI implements UI {

    Scanner scan = new Scanner(System.in);

    @Override
    public String getNewMemberName() {
        System.out.println("Nyt medlems navn: ");
        String name = scan.nextLine();
        System.out.println();
        return name;
    }

    @Override
    public int getNewMemberAge() {
        int age = 0;
        System.out.println("Indtast nyt medlems fødselsdato i formatet YYYY-MM-DD:  ");
        boolean temp = true;
        while (temp) {
            try {
                String birthDate = scan.nextLine();
                LocalDate birthday = LocalDate.parse(birthDate);
                LocalDate today = LocalDate.now();
                age = Period.between(birthday, today).getYears();
                temp = false;
            } catch (Exception e) {
                System.out.println("Du indtastede ikke fødselsdagen i det korrekte format. Prøv igen.");
            }
        }
        return age;

    }

    @Override
    public String getNewMemberSignUpDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-ll-dd");
        String signUpDate = today.format(formatter);
        return signUpDate;
    }

    @Override
    public boolean getNewMemberActivityForm() {
        System.out.println("Skriv medlem op som: ");
        System.out.println("1. Motionist");
        System.out.println("2. Konkurrencesvømmer");
        String age = scan.nextLine();
        System.out.println();
        return Integer.parseInt(age) == 2;
    }

    @Override
    public void displayMainMenu() {
        System.out.println();
        System.out.println("   Svømmeklubben Delfinen");
        System.out.println();
        System.out.println("1. Opret nyt medlem");
        System.out.println("2. Vis medlemmer");
        System.out.println("3. Slet medlemmer");
        System.out.println("4. Adm. Resultater");
        System.out.println("5. Vis Resultater");
        System.out.println("6. Vis gæld");
        System.out.println("7. Afslut Program");
    }

    @Override
    public void displayEditResultMenu() {
        System.out.println();
        System.out.println("   Svømmeklubben Delfinen - Adm. Resultater");
        System.out.println();
        System.out.println("1. Opret Resultat");
        System.out.println("2. Ændre Resultat");
        System.out.println("3. Slet Resultat");
        System.out.println("4. Gå tilbage");
    }

    @Override
    public void showResultsMenu() {
        System.out.println("   Svømmeklubben Delfinen - Vis Resultater");
        System.out.println();
        System.out.println("1. Vis alle resultater");
        System.out.println("2. Vis top-5 træningsresultater");
        System.out.println("3. Vis top-5 konkurrenceresultater");
        System.out.println("4. Gå tilbage");
    }

    @Override
    public void showMembersMenu() {
        System.out.println();
        System.out.println("   Svømmeklubben Delfinen - Medlemmer");
        System.out.println();
        System.out.println("1. Vis alle");
        System.out.println("2. Vis motionister");
        System.out.println("3. Vis konkurrence-svømmere");
        System.out.println("4. Vis juniormedlemmer");
        System.out.println("5. Vis seniormedlemmer");
        System.out.println("6. Gå tilbage");
    }

    @Override
    public String getMenuSelection() {
        String selection = scan.nextLine();
        System.out.println();
        return selection;
    }

    @Override
    public int getMemberID() {
        int choice = 0;
        System.out.println("Indtast medlems ID-nummer: ");
        boolean temp = true;
        while (temp) {
            try {
                choice = Integer.parseInt(scan.nextLine());
                //scan.nextLine();
                temp = false;
            } catch (Exception e) {
                System.out.println("Du indtastede ikke et gyldigt ID, prøv igen.");
            }
        }
        return choice;
    }

    @Override
    public int resultType() {
        System.out.println("Vælg resultattype: ");
        System.out.println("1: Træningsresultat");
        System.out.println("2: Konkurrenceresultat");
        int choice = scan.nextInt();
        scan.nextLine();
        return choice;
    }

    @Override
    public int swimmingDiscipline() {
        System.out.println("Vælg svømmediscplin:");
        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rycrawl");
        System.out.println("4. Brystsvømning");
        int choice = scan.nextInt();
        scan.nextLine();
        return choice;
    }

    @Override
    public String timeResult() {
        System.out.println("Indtast tidsresultatet i formattet TT:MM:SS:MM");
        return scan.nextLine();
    }

    @Override
    public String resultDate() {
        System.out.println("Indtast dato for træningsresultatet i formattet DD:MM:ÅÅÅÅ");
        return scan.nextLine();
    }

    @Override
    public String getPasswordForDatabase() {
        System.out.println("Please write password for database: ");
        String password = scan.nextLine();
        return password;
    }

    @Override
    public String getEventName() {
        System.out.println("Indtast navnet for konkurrencen: ");
        return scan.nextLine();
    }

    @Override
    public int getEventPlacement() {
        System.out.println("Indtast svømmerens placeing i konkurrencen: ");
        int choice = scan.nextInt();
        scan.nextLine();
        return choice;
    }

    @Override
    public int getEventNeedingDeleting(ArrayList<EventResult> eventResults
    ) {
        System.out.println("Indtast nr. for konkurrence der skal slettes: ");
        for (int i = 1; i <= eventResults.size(); i++) {
            System.out.println(Integer.toString(i) + ". " + eventResults.get(i - 1).toString());
        }
        int choice = scan.nextInt();
        scan.nextLine();
        return choice;
    }

    @Override
    public boolean confirmTrainingResultOverride(TrainingResult oldTrainingResult, TrainingResult newTrainingResult
    ) {
        System.out.println("Vil du overskrive: " + oldTrainingResult.toString());
        System.out.println("Med følgende: " + newTrainingResult.toString());
        System.out.println("1. Ja");
        System.out.println("2. Nej");
        int choice = scan.nextInt();
        scan.nextLine();
        return choice == 1;
    }

    @Override
    public void notCompetitveSwimmerMessage(int memberID
    ) {
        System.out.println("FEJL: Medlem med ID: " + memberID + " er ikke en konkurrence svømmer.");
    }

    @Override
    public void showAllMembers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            System.out.println(member);
        }
    }

    @Override
    public void showNonCompetitiveSwimmers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (!member.isCompetitiveSwimmer()) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void showCompetitiveSwimmers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (member.isCompetitiveSwimmer()) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void showResults(ArrayList<Member> membersList
    ) {
        ArrayList<String> resultsList;
        CompetitiveSwimmer temp;
        for (Member member : membersList) {
            resultsList = new ArrayList();
            if (member.isCompetitiveSwimmer()) {
                temp = (CompetitiveSwimmer) member;

                if (temp.getTrainingResultBrystsvømning() != null) {
                    resultsList.add(temp.getTrainingResultBrystsvømning().toString());
                }
                if (temp.getTrainingResultButterfly() != null) {
                    resultsList.add(temp.getTrainingResultButterfly().toString());
                }
                if (temp.getTrainingResultCrawl() != null) {
                    resultsList.add(temp.getTrainingResultCrawl().toString());
                }
                if (temp.getTrainingResultRygCrawl() != null) {
                    resultsList.add(temp.getTrainingResultRygCrawl().toString());
                }
                for (EventResult eventResult : temp.getEventResults()) {
                    if (temp.getEventResults() != null) {
                        resultsList.add(eventResult.toString());
                    }

                }
                if (resultsList.size() > 0) {
                    System.out.println(temp);
                    for (String result : resultsList) {
                        System.out.println(result);

                    }
                }

            }
        }

    }

    @Override
    public void showYoungTeamMembers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (member.getAge() < 18) {
                System.out.println(member);
            }
        }
    }

    @Override
    public void showSeniorTeamMembers(ArrayList<Member> membersList
    ) {
        for (Member member : membersList) {
            if (member.getAge() >= 18) {
                System.out.println(member);
            }
        }
    }

    public void showTopFiveResults(ArrayList<Member> membersList) {
        ArrayList<String> resultsList = new ArrayList();
        CompetitiveSwimmer temp;
        for (Member member : membersList) {
            //resultsList = new ArrayList();
            if (member.isCompetitiveSwimmer()) {
                temp = (CompetitiveSwimmer) member;

                if (temp.getTrainingResultBrystsvømning() != null) {
                    resultsList.add(temp.getTrainingResultBrystsvømning().toString());
                }
                if (temp.getTrainingResultButterfly() != null) {
                    resultsList.add(temp.getTrainingResultButterfly().toString());
                }
                if (temp.getTrainingResultCrawl() != null) {
                    resultsList.add(temp.getTrainingResultCrawl().toString());
                }
                if (temp.getTrainingResultRygCrawl() != null) {
                    resultsList.add(temp.getTrainingResultRygCrawl().toString());
                }
                for (EventResult eventResult : temp.getEventResults()) {
                    if (temp.getEventResults() != null) {
                        resultsList.add(eventResult.toString());
                    }

                }
            }

        }

        for (int i = 0; i < resultsList.size(); i++) {
            String smallest = resultsList.get(i);
            int smallestIndex = i;
            for (int j = i; j < resultsList.size(); j++) {
                String value = resultsList.get(j);
                if (value.compareTo(smallest) < 0) {
                    smallest = value;
                    smallestIndex = j;
                }
            }

            if (smallestIndex != i) {
                String head = resultsList.get(i);
                resultsList.set(i, smallest);
                resultsList.set(smallestIndex, head);
            }
        }

        System.out.println("Nummer 1: " + resultsList.get(0));
        System.out.println("Nummer 2: " + resultsList.get(1));
        System.out.println("Nummer 3: " + resultsList.get(2));
        System.out.println("Nummer 4: " + resultsList.get(3));
        System.out.println("Nummer 5: " + resultsList.get(4));
    }

    @Override
    public void showTopTimes(ArrayList<String> times) {
        for (String time : times) {
            System.out.println(time);

        }
    }

    @Override
    public void showDebt(ArrayList<Member> membersList) {
        for (Member member : membersList) {
            if (member.getDebt() > 0){
                System.out.print("MedlemsID: ");
                System.out.print(member.getMember_ID());
                System.out.print(", Medlemsnavn: ");
                System.out.print(member.getName());
                System.out.print(", Gæld: ");
                System.out.print(member.getDebt());
                System.out.println("");
            }
        }
    }

}
