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
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author MOHANRAJ
 */
public class UserReservationDAO {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public UserReservationDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","root123");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public int setReserve(UserReservation person){
        int check=0;
        
        try{
            pst=con.prepareStatement("insert into user_reservation(username,start_place,end_place,train_no,train_name,price,date,ticket,total) values(?,?,?,?,?,?,?,?,?);");
            pst.setString(1, person.getUsername());
            pst.setString(2, person.getStartPlace());
            pst.setString(3,person.getEndPlace());
            pst.setString(4, person.getTrainNo());
            pst.setString(5, person.getTrainName());
            pst.setString(6, person.getPrice());
            pst.setString(7, person.getDate());
            pst.setString(8,person.getTickets());
            pst.setString(9, person.getTotal());
            check=pst.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return check;
    }
    
    
}
