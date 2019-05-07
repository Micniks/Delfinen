/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import businesslogic.TrainingResult;
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

        sb.append("INSERT INTO Members (Member_ID, Name, Age, Active_Member, Competitive_Swimmer, Debt, Sign_Up_Date, Pay_Date) VALUES (");
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
        sb.append("\", \"");
        sb.append(member.getPayDate());
        sb.append("\")");
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
            ResultSet resultMembers = statement.executeQuery("SELECT * from Members");

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

    @Override
    public void storeTrainingResult(TrainingResult trainingResult, int member_ID) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Training_Results (Swimming_Discipline, Member_ID, Time_Result, Date) VALUES (\"");
        sb.append(trainingResult.getDiscipline().toString());
        sb.append("\", ");
        sb.append(member_ID);
        sb.append(", \"");
        sb.append(trainingResult.getTimeResult());
        sb.append("\", \"");
        sb.append(trainingResult.getDate());
        sb.append("\")");
        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void storeEventResult(EventResult eventResult, int member_ID) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Event_Results (Swimming_Discipline, Event_Name, Member_ID, Placement, Time_Result) VALUES (\"");
        sb.append(eventResult.getDiscipline().toString());
        sb.append("\", \"");
        sb.append(eventResult.getEventName());
        sb.append("\", ");
        sb.append(member_ID);
        sb.append(", ");
        sb.append(eventResult.getPlacement());
        sb.append(", \"");
        sb.append(eventResult.getTimeResult());
        sb.append("\")");
        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteTrainingResult(int memberID, SwimmingDiscipline swimmingDiscipline) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM Training_Results WHERE Member_ID = ");
        sb.append(Integer.toString(memberID));
        sb.append(" AND Swimming_Discipline = \"");
        sb.append(swimmingDiscipline.toString());
        sb.append("\"");
        System.out.println(sb.toString());
        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEventResult(int memberID, String eventName, SwimmingDiscipline eventSD) {
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM Event_Results WHERE Member_ID = ");
        sb.append(Integer.toString(memberID));
        sb.append(" AND Swimming_Discipline = \"");
        sb.append(eventSD.toString());
        sb.append(" AND Event_Name = \"");
        sb.append(eventName);
        sb.append("\"");
        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Iterable<HashMap<String, String>> getTrainingResults() {
        ArrayList<HashMap<String, String>> trainingResultInfo = new ArrayList<>();
        try {
            ResultSet resultTrainingResults = statement.executeQuery("SELECT * from Training_Results");

            while (resultTrainingResults.next()) {
                HashMap<String, String> map = new HashMap();
                
                map.put("Swimming_Discipline", resultTrainingResults.getString("Swimming_Discipline"));
                map.put("Member_ID", Integer.toString(resultTrainingResults.getInt("Member_ID")));
                map.put("Time_Result", resultTrainingResults.getString("Time_Result"));
                map.put("Date", resultTrainingResults.getString("Date"));

                trainingResultInfo.add(map);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return trainingResultInfo;
    }

    @Override
    public Iterable<HashMap<String, String>> getEventResults() {
        ArrayList<HashMap<String, String>> eventResultInfo = new ArrayList<>();
        try {
            ResultSet resultTrainingResults = statement.executeQuery("SELECT * from Event_Results");

            while (resultTrainingResults.next()) {
                HashMap<String, String> map = new HashMap();
                
                map.put("Swimming_Discipline", resultTrainingResults.getString("Swimming_Discipline"));
                map.put("Event_Name", resultTrainingResults.getString("Event_Name"));
                map.put("Member_ID", Integer.toString(resultTrainingResults.getInt("Member_ID")));
                map.put("Placement", resultTrainingResults.getString("Placement"));
                map.put("Time_Result", resultTrainingResults.getString("Time_Result"));

                eventResultInfo.add(map);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return eventResultInfo;
    }

}
