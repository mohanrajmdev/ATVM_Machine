/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RailwayDAO;

import java.sql.*;
import java.util.*;
/**
 *
 * @author MOHANRAJ
 */
public class ReservationDAO {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public ReservationDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","root123");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public Set<String> getStartPlace(){
        Set<String> place=new HashSet<>();
        try{
            Statement st=con.createStatement();
            rs=st.executeQuery("select t_start from addtrain;");
            while(rs.next()){
                place.add(rs.getString(1).toLowerCase());
               
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return place;
    }

    public Set<String> getEndPlace() {
        Set<String> place=new HashSet<>();
        try{
            pst=con.prepareStatement("select t_destination from addtrain;");
            rs=pst.executeQuery();
            while(rs.next()){
                place.add(rs.getString(1).toLowerCase());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return place;
    }
    
    public Reservation search(String start,String end){
        Reservation person=new Reservation();
        try{
            pst=con.prepareStatement("select * from addtrain where t_start=? and t_destination=?;");
            pst.setString(1, start);
            pst.setString(2,end);
            rs=pst.executeQuery();
            if(rs.next()){
                person.setTrainNo(rs.getString("t_id"));
                person.setTrainName(rs.getString("t_name"));
                person.setPrice(rs.getString("t_price"));
            }
            else{
                person.setPrice("0");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return person;
    }
    
    public int setReserve(Reservation person){
        int check=0;
        
        try{
            pst=con.prepareStatement("insert into reservation values(?,?,?,?,?,?,?,?,?);");
            pst.setString(1, person.getPassengerNo());
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
