package businesslogic;

import java.util.Date;          // Remove if we don't need to use Sign Up Date

/**
 *
 * @author Michael N. Korsgaard
 */
public class Member {

    private String name;
    private int age;
    private boolean activeMember;
    private boolean compedetiveSwimmer;

    /* We might need to set a sign-up date to have the system auto-check debt
    private Date signUpDate;
     */
    public Member(String name, int age, boolean compedetiveSwimmer) {
        this.name = name;
        this.age = age;
        this.compedetiveSwimmer = compedetiveSwimmer;
        this.activeMember = true;
        /* We might need to set a sign-up date to have the system auto-check debt
        this.signUpDate = new Date();
         */
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(", alder ");
        sb.append(age);
        sb.append(", ");
        if (compedetiveSwimmer) {
            sb.append("konkurrencesvømmer, ");
        } else {
            sb.append("motionist, ");
        }
        if (!activeMember) {
            sb.append("ikke ");
        }
        sb.append("aktivt medlem.");
        return sb.toString();
    }

}
