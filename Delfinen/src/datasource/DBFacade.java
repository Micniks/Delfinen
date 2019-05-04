/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael N. Korsgaard
 */
public class DBFacade implements Facade {

    private Connection connect;
    private Statement statement;
    private String serverTime = "serverTimezone=UTC";

    public DBFacade(String password) {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Delfinen+?" + serverTime, "root", password);
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBFacade() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Delfinen+?" + serverTime, "root", "Gunstar1");
            statement = connect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void storageMember(Member member) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Members (Member_ID, Name, Age, Active_Member, Competitive_Swimmer, Debt, Sign_Up_Date) VALUES (");
        sb.append(member.getMember_ID());
        sb.append(", \"");
        sb.append(member.getName());
        sb.append("\", ");
        sb.append(member.getAge());
        sb.append(", ");
        sb.append(member.isActiveMember());
        sb.append(", ");
        sb.append(member.isCompetetiveSwimmer());
        sb.append(", ");
        sb.append(member.getDebt());
        sb.append(", \"");
        sb.append(member.getSignUpDate());
        sb.append("\")");
        System.out.println(sb.toString());
        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {

        ArrayList<HashMap<String, String>> memberinformation = new ArrayList<>();
        try {
            ResultSet resultMembers = statement.executeQuery("SELECT * from members");

            ArrayList memberName = new ArrayList();
            ArrayList memberAge = new ArrayList();
            ArrayList memberCompetitiveSwimmer = new ArrayList();
            ArrayList memberActiveMember = new ArrayList();
            ArrayList memberDept = new ArrayList();

            while (resultMembers.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("Member_ID", resultMembers.getString("Member_ID"));
                map.put("Name", resultMembers.getString("Name"));
                map.put("Age", resultMembers.getString("Age"));
                map.put("Competitive_Swimmer", Boolean.toString(resultMembers.getString("Competitive_Swimmer").contains("1")));
                map.put("Active_Member", Boolean.toString(resultMembers.getString("Active_Member").contains("1")));
                map.put("Debt", resultMembers.getString("Debt"));
                map.put("Sign_Up_Date", resultMembers.getString("Sign_Up_Date"));
                memberinformation.add(map);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return memberinformation;
    }

    @Override
    public int readHighestMemberID() {
        ArrayList temp = new ArrayList();
        int highestMemberID = 0;
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * from Members");

            while (result.next()) {
                temp.add(result.getInt("Member_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (temp.size() != 0) {
            highestMemberID = (int) Collections.max(temp);
            highestMemberID++;
        } else {
            highestMemberID = 1;
        }

        return highestMemberID;

    }

}
