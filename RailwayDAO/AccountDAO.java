/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RailwayDAO;
import java.sql.*;
/**
 *
 * @author MOHANRAJ
 */
public class AccountDAO {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public AccountDAO(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway","root","root123");
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public int createAccount(Account person){
        int check=0;
        try{
            pst=con.prepareStatement("insert into account(name,email_id,username,password,mobile_no,address) values(?,?,?,?,?,?);");
            pst.setString(1, person.getName());
            pst.setString(2, person.getEmail());
            pst.setString(3,person.getUsername());
            pst.setString(4, person.getPassword());
            pst.setString(5, person.getMobile());
            pst.setString(6, person.getAddress());
            
            check=pst.executeUpdate();
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return check;
    }
    
    public String getPassword(String name){
        String pass="No record";
        try{
            pst=con.prepareStatement("select * from account where username=?;");
            pst.setString(1, name);
            rs=pst.executeQuery();
            if(rs.next()){
                pass=rs.getString("password");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return pass;
    }
    
}
