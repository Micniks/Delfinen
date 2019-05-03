/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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

}
