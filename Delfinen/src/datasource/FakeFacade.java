/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.Member;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Michael N. Korsgaard
 */
public class FakeFacade implements Facade {

    public ArrayList<HashMap<String, String>> testArray;

    

    public FakeFacade() {
        this.testArray = new ArrayList();

    }

    @Override
    public void storageMember(Member member) {
        HashMap<String, String> map = new HashMap();
        map.put("Name", member.getName());
        map.put("Age", Integer.toString(member.getAge()));
        map.put("Competitive_Swimmer", Boolean.toString(member.isCompetetiveSwimmer()));
        map.put("Active_Member", Boolean.toString(member.isActiveMember()));
        map.put("Debt", Double.toString(member.getDept()));
        map.put("Sign_Up_Date", member.getSignUpDate());
        testArray.add(map);
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        return testArray;
    }

    @Override
    public int readHighestMemberID() {
        return 1;
    }

}
