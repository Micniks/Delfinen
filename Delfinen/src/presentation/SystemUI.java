/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import businesslogic.EventResult;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        System.out.println("Indtast nyt medlems fødselsdato i formatet YYYY-MM-DD:  ");
        String birthDate = scan.nextLine();
        LocalDate birthday = LocalDate.parse(birthDate);
        LocalDate today = LocalDate.now();
        int age = Period.between(birthday, today).getYears();
        return age;

    }

    @Override
    public String getNewMemberSignUpDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
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
        System.out.println("2. Afslut Program");
    }

    @Override
    public String mainMenuSelection() {
        String selection = scan.nextLine();
        System.out.println();
        return selection;
    }

    @Override
    public int getMemberID() {
        System.out.println("Indtast medlems ID-nummer: ");
        int choice = scan.nextInt();
        scan.nextLine();
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
    public int getEventNeedingDeleting(ArrayList<EventResult> eventResults) {
        System.out.println("Indtast nr. for konkurrence der skal slettes: ");
        for (int i = 1; i <= eventResults.size(); i++) {
            System.out.println(Integer.toString(i) + ". " + eventResults.get(i - 1).toString());
        }
        int choice = scan.nextInt();
        scan.nextLine();
        return choice;
    }

}
