package businesslogic;

import java.util.Date;          // Remove if we don't need to use Sign Up Date

/**
 *
 * @author Michael N. Korsgaard
 */
public class Member {

    private int member_ID;
    private String name;
    private int age;
    private boolean activeMember;
    private boolean competetiveSwimmer;
    private double debt;
    private String signUpDate;
    private String payDate;

    public Member(int member_ID, String name, int age, boolean competetiveSwimmer, String signUpDate) {
        this.member_ID=member_ID;
        this.name = name;
        this.age = age;
        this.competetiveSwimmer = competetiveSwimmer;
        this.signUpDate = signUpDate;
        this.activeMember = true;
        this.debt = 0;
    }

    public Member(int member_ID, String name, int age, boolean activeMember, boolean competetiveSwimmer, double debt, String signUpDate) {
        this.member_ID=member_ID;
        this.name = name;
        this.age = age;
        this.competetiveSwimmer = competetiveSwimmer;
        this.signUpDate = signUpDate;
        this.activeMember = activeMember;
        this.debt = debt;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
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

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
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
    
    public int getMember_ID() {
        return member_ID;
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
        if (debt > 0){
            sb.append(" Gæld: ");
            sb.append(debt);
        }
        return sb.toString();
    }

}
