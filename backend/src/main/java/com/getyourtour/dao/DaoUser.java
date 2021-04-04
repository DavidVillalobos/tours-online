/*
 * File: DaoUser.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.dao;

import com.getyourtour.model.Country;
import com.getyourtour.model.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoUser {

    private ConnectionDB db;

    public DaoUser(){
        db = ConnectionDB.instance();
    }

    public User get(Integer id){
        try{
            String sql = "SELECT * FROM [User] WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/User/{" + id + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public List<User> get(){
        List<User> users = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from User");
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
            if(0 == users.size()){
                System.out.println("Log: GET/users Does not exist any User in DataBase");
            }
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return users;
    }

    public User getByEmail(String email) {
        try{
            String sql = "SELECT * FROM [User] WHERE Email='%s'";
            sql = String.format(sql, email);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }else{
                System.out.println("Log: GET/User/Email/{" + email + "} Does not exist in DataBase");
                return null;
            }
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public Integer post(User u){
        try{
            String sql = "INSERT INTO [User](Id_Country, Email, Password, Name, LastName, Identification, Birthday, Admin)"
            + " VALUES(%d,'%s','%s','%s', '%s', '%s','%s', %b)";
            sql = String.format(sql, u.getCountry().getId(), u.getEmail(), u.getPassword(), u.getName(),
                    u.getLastName(), u.getIdentification(), u.getStringDate(), u.getAdmin());
            return db.executeUpdate(sql);
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer put(User u){
        try{
            String sql="UPDATE [User] SET Email='%s', Password='%s', Name='%s', LastName='%s', Identification='%s', " +
                    "Birthday='%s', Admin=%b WHERE Id=%d";
            sql=String.format(sql, u.getEmail(), u.getPassword(), u.getName(), u.getLastName(), u.getIdentification(),
                    u.getStringDate(), u.getId(), u.getAdmin());
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: PUT/User/{" + u.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    public Integer delete(Integer Id){
        try{
            String sql="DELETE FROM [User] WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                System.out.println("Log: DELETE/User/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return 0;
    }

    private User map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String email = rs.getString("Email");
        String password = rs.getString("Password");
        String name = rs.getString("Name");
        String lastName = rs.getString("LastName");
        String identification = rs.getString("Identification");
        Date birthday = rs.getDate("Birthday");
        Boolean admin = rs.getBoolean("Admin");
        DaoCountry dc = new DaoCountry();
        Country country = dc.get(rs.getInt("Id_Country"));
        return new User(id, country, email, password, name, lastName, identification, birthday, admin);
    }

}