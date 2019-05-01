/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.Member;
import java.util.ArrayList;

/**
 *
 * @author Michael N. Korsgaard
 */
public class FakeFacade implements Facade {
    
    public ArrayList<String> testArray;

    public FakeFacade() {
        this.testArray = new ArrayList();

    }

    @Override
    public void storageMember(Member member){
        testArray.add(member.toString());
    }

    @Override
    public ArrayList<String> getMembers() {
        return testArray;
    }


}
