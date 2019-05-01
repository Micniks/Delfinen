
package datasource;

import businesslogic.Member;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael N. Korsgaard
 */
public class FileFacade implements Facade {
    
    File orderHistory = new File("MemberList.txt");
    FileWriter fw;
    BufferedWriter bufWriter;
    FileReader fr;
    BufferedReader bufReader;
    
        public FileFacade() {

        try {
            fw = new FileWriter(orderHistory, true);
            bufWriter = new BufferedWriter(fw);
            fr = new FileReader(orderHistory);
            bufReader = new BufferedReader(fr);
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void storageMember(Member member) {
        try {
            bufWriter.write(member.toString());
            bufWriter.newLine();
            bufWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> getMembers() {
        ArrayList<String> memberList = new ArrayList();
        try {
            while (bufReader.ready()) {
                memberList.add(bufReader.readLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return memberList;
    }
    
}
