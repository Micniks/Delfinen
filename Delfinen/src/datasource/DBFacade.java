/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.EventResult;
import businesslogic.Member;
import businesslogic.SwimmingDiscipline;
import businesslogic.Trainer;
import businesslogic.TrainingResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *  @author Michael N. Korsgaard, Jens Brønd, Oscar Laurberg, Cassandra Lynge.
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
        sb.append(member.isCompetitiveSwimmer());
        sb.append(", ");
        sb.append(member.getDebt());
        sb.append(", \"");
        sb.append(member.getSignUpDate());
        sb.append("\", \"");
        sb.append(member.getLastAddedDebtDate());
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
                map.put("Pay_Date", resultMembers.getString("Pay_Date"));
                memberinformation.add(map);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return memberinformation;
    }

    @Override
    public int readHighestMemberID() {
        ArrayList databaseMemberInfo = new ArrayList();
        int highestMemberID = 0;
        ResultSet result;
        try {
            result = statement.executeQuery("SELECT * from Members");

            while (result.next()) {
                databaseMemberInfo.add(result.getInt("Member_ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (databaseMemberInfo.size() != 0) {
            highestMemberID = (int) Collections.max(databaseMemberInfo);
            highestMemberID++;
        } else {
            highestMemberID = 1;
        }

        return highestMemberID;

    }

    @Override
    public void topFiveTrainingResults() {

    }

    @Override
    public void deleteMember() {

    }

    @Override
    public void updateMember(Member member) {

        try {
            PreparedStatement statement = connect.prepareStatement("UPDATE Members SET Name = ?, Age = ?, "
                    + "Active_Member = ?, Competitive_Swimmer = ?, Debt = ?, Sign_Up_Date = ?, Pay_Date = ?"
                    + "WHERE Member_ID = ?");
            statement.setString(1, member.getName());
            statement.setInt(2, member.getAge());
            statement.setBoolean(3, member.isActiveMember());
            statement.setBoolean(4, member.isCompetitiveSwimmer());
            statement.setDouble(5, member.getDebt());
            statement.setString(6, member.getSignUpDate());
            statement.setString(7, member.getLastAddedDebtDate());
            statement.setInt(8, member.getMember_ID());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
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
        sb.append(" AND Event_Name = \"\"");
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

    @Override
    public ArrayList<String> getTopFiveTrainingResults() {
        ArrayList<String> results = new ArrayList();

        try {
            String selectSql = "select Members.Name, Training_Results.Time_Result\n"
                    + "    from Members\n"
                    + "    join Training_Results on Members.Member_ID = Training_Results.Member_ID\n"
                    + "    where Training_Results.Swimming_Discipline = ? order by Time_Result asc;";
            PreparedStatement preparedStatement = connect.prepareStatement(selectSql);
            for (int i = 1; i < 5; i++) {
                switch (i) {
                    case 1:
                        results.add("BUTTERLFY");
                        preparedStatement.setString(1, "BUTTERFLY");
                        break;
                    case 2:
                        results.add("CRAWL");
                        preparedStatement.setString(1, "CRAWL");
                        break;
                    case 3:
                        results.add("RYGCRAWL");
                        preparedStatement.setString(1, "RYGCRAWL");
                        break;
                    case 4:
                        results.add("BRYSTSVØMNING");
                        preparedStatement.setString(1, "BRYSTSVØMNING");

                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    results.add(resultSet.getString(1) + " " + resultSet.getString(2));
                    

                }

            }
            return results;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        
    }

    @Override
    public ArrayList<String> getTopFiveEventResults() {
                ArrayList<String> results = new ArrayList();

        try {
            String selectSql = "select Members.Name, Event_Results.Time_Result\n"
                    + "    from Members\n"
                    + "    join Event_Results on Members.Member_ID = Event_Results.Member_ID\n"
                    + "    where Event_Results.Swimming_Discipline = ? order by Time_Result asc;";
            PreparedStatement preparedStatement = connect.prepareStatement(selectSql);
            for (int i = 1; i < 5; i++) {
                switch (i) {
                    case 1:
                        results.add("BUTTERLFY");
                        preparedStatement.setString(1, "BUTTERFLY");
                        break;
                    case 2:
                        results.add("CRAWL");
                        preparedStatement.setString(1, "CRAWL");
                        break;
                    case 3:
                        results.add("RYGCRAWL");
                        preparedStatement.setString(1, "RYGCRAWL");
                        break;
                    case 4:
                        results.add("BRYSTSVØMNING");
                        preparedStatement.setString(1, "BRYSTSVØMNING");

                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    results.add(resultSet.getString(1) + " " + resultSet.getString(2));
                    

                }

            }
            return results;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        
    }

    @Override
    public void removeMember(int memberID){
        String sqlStatement1 = "DELETE FROM Training_Results WHERE Member_ID = ?;";
        String sqlStatement2 = "DELETE FROM Event_Results WHERE Member_ID = ?;";
        String sqlStatement3 = "DELETE FROM Team_Members where Member_ID = ?;";
        String sqlStatement4 = "DELETE FROM Members where Member_ID = ?;";
        
        try {
            PreparedStatement statement1 = connect.prepareStatement(sqlStatement1);
            statement1.setInt(1, memberID);
            statement1.executeUpdate();
            PreparedStatement statement2 = connect.prepareStatement(sqlStatement2);
            statement2.setInt(1,memberID);
            statement2.executeUpdate();
            PreparedStatement statement3 = connect.prepareStatement(sqlStatement3);
            statement3.setInt(1,memberID);
            statement3.executeUpdate();
            PreparedStatement statement4 = connect.prepareStatement(sqlStatement4);
            statement4.setInt(1, memberID);
            statement4.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }

    @Override
    public void storeTrainer(Trainer trainer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
