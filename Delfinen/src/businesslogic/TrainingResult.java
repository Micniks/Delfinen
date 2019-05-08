package businesslogic;
/*
*@author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
*/
public class TrainingResult {

    private String timeResult;
    private SwimmingDiscipline discipline;
    private String date;

    public TrainingResult(SwimmingDiscipline discipline, String timeResult, String date) {
        this.timeResult = timeResult;
        this.discipline = discipline;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public SwimmingDiscipline getDiscipline() {
        return discipline;
    }
    
    @Override
    public String toString(){
        return discipline.toString()+ ", " + timeResult + " d. " + date;
    }

}
