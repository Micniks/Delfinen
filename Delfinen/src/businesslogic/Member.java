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
    private int dept;
    private String signUpDate;

    /* We might need to set a sign-up date to have the system auto-check debt
    private Date signUpDate;
     */
    public Member(String name, int age, boolean competetiveSwimmer, String signUpDate) {
        this.name = name;
        this.age = age;
        this.competetiveSwimmer = competetiveSwimmer;
        this.signUpDate = signUpDate;
        this.activeMember = true;
        this.dept = 0;
        /* We might need to set a sign-up date to have the system auto-check debt
        this.signUpDate = new Date();
         */
    }

    public int getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
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
        sb.append(", motionist, ");
        if (!activeMember) {
            sb.append("ikke ");
        }
        sb.append("aktivt medlem.");
        if (dept > 0){
            sb.append(" Gæld: ");
            sb.append(dept);
        }
        return sb.toString();
    }

}
