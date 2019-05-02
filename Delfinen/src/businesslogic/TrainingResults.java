package businesslogic;

public class TrainingResults {

    private double timeResult;
    private Member member;
    private String swimmingDisciplin;

    public TrainingResults (Member member, String swimmingDisciplin, double timeResult) {
        this.member = member;
        this.timeResult = timeResult;
        this.swimmingDisciplin = swimmingDisciplin;
    }

}
