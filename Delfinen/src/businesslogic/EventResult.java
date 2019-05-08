/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

/**
 *
 * @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
 */
public class EventResult {

    private String eventName;
    private int placement;
    private String timeResult;
    private SwimmingDiscipline discipline;

    public EventResult(String eventName, int placement, String timeResult, SwimmingDiscipline discipline) {
        this.eventName = eventName;
        this.placement = placement;
        this.timeResult = timeResult;
        this.discipline = discipline;
    }

    public String getEventName() {
        return eventName;
    }

    public int getPlacement() {
        return placement;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public SwimmingDiscipline getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return eventName + ", " + placement + ". plads i " + discipline.toString() + " med tid: " + timeResult;
    }

}
