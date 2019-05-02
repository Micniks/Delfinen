/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Members {

    private ArrayList<Member> members;

    public Members() {
        members = new ArrayList();
        getMembersFromStorage(members);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addMembers(Member member) {
        members.add(member);
    }

    private void getMembersFromStorage(ArrayList<Member> members) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
