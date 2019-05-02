/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

/**
 *
 * @author Michael N. Korsgaard
 */
public class EventResults {
    
    private String eventName;
    private int placement;
    private double timeResult;
    private Member member;
    
    public EventResults(Member member, String eventName, int placement, double timeResult){
        this.member = member;
        this.eventName = eventName;
        this.placement = placement;
        this.timeResult = timeResult;
    }
    
    
    
}
