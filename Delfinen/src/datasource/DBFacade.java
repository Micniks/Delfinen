/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasource;

import businesslogic.Member;
import java.sql.Connection;
import java.sql.DriverManager;
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

        sb.append("INSERT INTO Member (Name, Age, Active_Member, Competitive_Swimmer, Debt) VALUES (");
        sb.append(member.getName());
        sb.append(",");
        sb.append(member.getAge());
        sb.append(",");
        sb.append(member.isActiveMember());
        sb.append(",");
        sb.append(member.isCompetetiveSwimmer());
        sb.append(", 0)");


        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> getMembers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
