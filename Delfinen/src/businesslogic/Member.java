package businesslogic;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class Member {

    private int member_ID;
    private String name;
    private int age;
    private boolean activeMember;
    private boolean competitiveSwimmer;
    private double debt;
    private String signUpDate;
    private String lastAddedDebtDate;

    public Member(int member_ID, String name, int age, boolean competitiveSwimmer, String signUpDate) {
        this.member_ID = member_ID;
        this.name = name;
        this.age = age;
        this.competitiveSwimmer = competitiveSwimmer;
        this.signUpDate = signUpDate;
        this.activeMember = true;
        this.debt = 0;
        this.lastAddedDebtDate = signUpDate;
    }

    public Member(int member_ID, String name, int age, boolean activeMember, boolean competitiveSwimmer, double debt, String signUpDate, String lastAddedDebtDate) {
        this.member_ID = member_ID;
        this.name = name;
        this.age = age;
        this.competitiveSwimmer = competitiveSwimmer;
        this.signUpDate = signUpDate;
        this.activeMember = activeMember;
        this.debt = debt;
        this.lastAddedDebtDate = lastAddedDebtDate;
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

    public String getLastAddedDebtDate() {
        return lastAddedDebtDate;
    }

    public void setLastAddedDebtDate(String lastAddedDebtDate) {
        this.lastAddedDebtDate = lastAddedDebtDate;
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

    public boolean isCompetitiveSwimmer() {
        return competitiveSwimmer;
    }

    public void setCompetitiveSwimmer(boolean compedetiveSwimmer) {
        this.competitiveSwimmer = compedetiveSwimmer;
    }

    public int getMember_ID() {
        return member_ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(member_ID).append(". ");
        sb.append(name).append(", alder ");
        sb.append(age).append(", motionist, ");
        if (!activeMember) {
            sb.append("ikke ");
        }
        sb.append("aktivt medlem.");
        if (debt > 0) {
            sb.append(" Gæld: ");
            sb.append(debt);
        }
        return sb.toString();
    }

}
