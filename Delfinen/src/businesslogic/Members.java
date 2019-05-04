/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Members {

    private ArrayList<Member> members;
    private Facade db;

    public Members(Facade db) {
        members = new ArrayList();
        this.db = db;
        getMembersFromStorage(members);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMembers(Member member) {
        members.add(member);
    }

    private void getMembersFromStorage(ArrayList<Member> members) {
        ArrayList<HashMap<String, String>> memberInfo = db.getMembers();
        
        for(HashMap<String, String> map : memberInfo){
            String name = map.get("Name");
            int age = Integer.parseInt(map.get("Age"));
            boolean competitiveSwimmer = Boolean.parseBoolean(map.get("Competitive_Swimmer"));
            boolean activeMember = Boolean.parseBoolean(map.get("Active_Member"));
            double debt = Double.parseDouble(map.get("Debt"));
            
            
        }
        
    }
    
    

}
