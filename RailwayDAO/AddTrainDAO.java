/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package RailwayDAO;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author MOHANRAJ
 */

public class AddTrainDAO {
    private int no,price;
    private String name,start,destination;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    public AddTrainDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","root123");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    //data Access Object
    
    public int addTrainDetail(){
        int check=0;
        try{
            pst=con.prepareStatement("Insert into addtrain values(?,?,?,?,?);");
            pst.setString(1,String.valueOf(no));
            pst.setString(2,name);
            pst.setString(3,start);
            pst.setString(4,destination);
            pst.setString(5,String.valueOf(price));
            check=pst.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return check;
    }
    
    public int updateTrainDetails(){
        int check=0;
        try{
            pst=con.prepareStatement("update addtrain set t_name=? ,t_start=? ,t_destination=? ,t_price=? where t_id=? ;");
            pst.setString(1,name);
            pst.setString(2,start);
            pst.setString(3,destination);
            pst.setString(4,String.valueOf(price));
            pst.setString(5,String.valueOf(no));
            check=pst.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return check;
    }
    
    public ArrayList<AddTrain> allDetails(){
        ArrayList<AddTrain> list=new ArrayList<AddTrain>();
        
        try{
            pst=con.prepareStatement("select * from addtrain;");
            rs=pst.executeQuery();
            
            while(rs.next()){
                AddTrain t=new AddTrain();
                t.setNumber(rs.getString("t_id"));
                t.setName(rs.getString("t_name"));
                t.setStartPlace(rs.getString("t_start"));
                t.setDestinationPlace(rs.getString("t_destination"));
                t.setPrice(rs.getString("t_price"));
                list.add(t);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public int deleteTrainDetails() {
        int check=0;
        try{
            pst=con.prepareStatement("delete from addtrain where t_id=? ;");
            pst.setString(1,String.valueOf(no));
            check=pst.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return check;
    }
    
}
