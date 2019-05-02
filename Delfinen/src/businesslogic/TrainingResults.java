package businesslogic;

public class TrainingResults {

    private double timeResult;
    private Member member;
    private SwimmingDiscipline discipline;

    public TrainingResults (Member member, SwimmingDiscipline discipline, double timeResult) {
        this.member = member;
        this.timeResult = timeResult;
        this.discipline = discipline;
    }

}
