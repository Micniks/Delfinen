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
public interface Facade {
    
    public void storageMember(Member member);
    public ArrayList<String> getMembers();
    
}
