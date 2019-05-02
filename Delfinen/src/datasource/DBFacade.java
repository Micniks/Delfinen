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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael N. Korsgaard
 */
public class DBFacade implements Facade {

    private final Connection connect;
    private Statement statement;
    private String serverTime = "serverTimezone=UTC";

    public DBFacade(String password) throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza+?" + serverTime, "root", password);
        statement = connect.createStatement();

    }

    public DBFacade() throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Marios_Pizza+?" + serverTime, "root", "Gunstar1");
        statement = connect.createStatement();
    }

    @Override
    public void storageMember(Member member) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Members (Name, Age, Active_Member, Competitive_Swimmer, Debt) VALUES (");
        sb.append(member.getName());
        sb.append(", ");
        sb.append(member.getAge());
        sb.append(", ");
        sb.append(member.isActiveMember());
        sb.append(", ");
        sb.append(member.isCompetetiveSwimmer());
        sb.append(", )");
        sb.append(member.getDept());
        sb.append(")");

        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getMembersFromStorage() {

        ArrayList<String> members = new ArrayList();
        StringBuilder sb = new StringBuilder();
        try {
            ResultSet resultMembers = statement.executeQuery("SELECT * from members");

            ArrayList memberName = new ArrayList();
            ArrayList memberAge = new ArrayList();
            ArrayList memberCompetitiveSwimmer = new ArrayList();
            ArrayList memberActiveMember = new ArrayList();
            ArrayList memberDept = new ArrayList();

            while (resultMembers.next()) {
                memberName.add(resultMembers.getString("Name"));
                memberAge.add(resultMembers.getInt("Age"));
                memberCompetitiveSwimmer.add(resultMembers.getBoolean("Competitive_Swimmer"));
                memberActiveMember.add(resultMembers.getBoolean("Active_Member"));
                memberDept.add(resultMembers.getDouble("Debt"));
            }

            for (int i = 0; i < memberName.size(); i++) {
                sb.append("Name: ");
                sb.append(memberName.get(i));
                sb.append(", Age: ");
                sb.append(memberAge.get(i));
                sb.append(", CompetitiveSwimmer: ");
                sb.append(memberCompetitiveSwimmer.get(i));
                sb.append(", ActiveMember: ");
                sb.append(memberActiveMember.get(i));
                sb.append(", Dept: ");
                sb.append(memberDept.get(i));
                
                members.add(sb.toString());
                sb.delete(0, sb.length());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return members;
    }

    @Override
    public ArrayList<String> getMembers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
