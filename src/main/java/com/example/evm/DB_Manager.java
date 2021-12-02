package com.example.evm;

import javafx.scene.control.TextField;

import java.sql.*;

public class DB_Manager {
    static Connection con;

    public static void db_connection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded succesfully");

            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@127.0.0.1:1521:XE","system","tiger"
            );
            System.out.println("Connection Established!");
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver Not Loaded");
        }
        catch(SQLException e){
            System.out.println("Connection Not Established!");
        }
    }

    public void dataRetrieve(){
        db_connection();

        try{
            Statement stmt = con.createStatement();


            ResultSet rs =  stmt.executeQuery("select * from system.students");

            while(rs.next()){
                // int id = rs.getInt(1);
                String name = rs.getString("name");
                String section = rs.getString("section");

                System.out.println(name+" "+section);


            }
            con.close();

        }catch (Exception e){
            System.out.println("bleh");
        }

    }

    public void castVote(String voterID, String partyName){
        db_connection();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs =  stmt.executeQuery("select * from system.voter");

            while(rs.next()){
                // int id = rs.getInt(1);
                String id = rs.getString("voterID");
                if(id.equals(voterID)) {
                    String check = rs.getString("voteCast");
                    String sql = "update system.voter set voteCast = 1 where voterID = ?";

                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1,voterID);
                }
                else{
                    System.out.println("invalid voter ID");
                }

                incrementVote(partyName);

            }
            con.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void incrementVote(String partyName){
        db_connection();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs =  stmt.executeQuery("select * from system.parties");

            while(rs.next()){
                String name = rs.getString("partyName");
                if(name.equals(partyName)) {
                    String sql = "update system.parties set votes = 1 where voterID = ?";

                    PreparedStatement statement = con.prepareStatement(sql);
                    //statement.setString(1,voterID);
                }
                else{
                    System.out.println("invalid voter ID");
                }

                incrementVote(partyName);

            }
            con.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addVoterInfo(TextField address, TextField name, TextField cnic, TextField city, TextField voteID) {
        String sql = "insert into system.voter values(?, ?, ?);";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);

            statement.setString(1, voteID.getText());
            statement.setInt(2, 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
