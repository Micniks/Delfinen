/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import datasource.Facade;
import java.util.ArrayList;

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
        ArrayList<String> memberInfo = db.getMembers();
        for (int i = 0; i < memberInfo.size(); i++) {
            String[] info = memberInfo.get(i).split(":");
        }
        
    }
    
    

}
