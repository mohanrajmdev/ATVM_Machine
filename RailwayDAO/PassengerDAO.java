/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RailwayDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MOHANRAJ
 */
public class PassengerDAO {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public PassengerDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","root123");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public ArrayList<UserReservation> allDetails(String username){
        ArrayList<UserReservation> list=new ArrayList<>();
        
        try{
            pst=con.prepareStatement("select * from user_reservation where username=?;");
            pst.setString(1, username);
            rs=pst.executeQuery();
            
            while(rs.next()){
                UserReservation t=new UserReservation();
                t.setTrainNo(rs.getString("train_no"));
                t.setTrainName(rs.getString("train_name"));
                t.setStartPlace(rs.getString("start_place"));
                t.setEndPlace(rs.getString("end_place"));
                t.setDate(rs.getString("date"));
                t.setTickets(rs.getString("ticket"));
                t.setTotal(rs.getString("total"));
                t.setPrice(rs.getString("price"));
                list.add(t);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
}
