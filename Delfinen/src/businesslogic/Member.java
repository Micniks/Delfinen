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
    private boolean competetiveSwimmer;

    /* We might need to set a sign-up date to have the system auto-check debt
    private Date signUpDate;
     */
    public Member(String name, int age, boolean competetiveSwimmer) {
        this.name = name;
        this.age = age;
        this.competetiveSwimmer = competetiveSwimmer;
        this.activeMember = true;
        /* We might need to set a sign-up date to have the system auto-check debt
        this.signUpDate = new Date();
         */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActiveMember() {
        return activeMember;
    }

    public void setActiveMember(boolean activeMember) {
        this.activeMember = activeMember;
    }

    public boolean isCompetetiveSwimmer() {
        return competetiveSwimmer;
    }

    public void setCompetetiveSwimmer(boolean compedetiveSwimmer) {
        this.competetiveSwimmer = compedetiveSwimmer;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(", alder ");
        sb.append(age);
        sb.append(", ");
        if (competetiveSwimmer) {
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
