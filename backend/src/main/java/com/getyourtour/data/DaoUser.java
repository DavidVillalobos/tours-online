/*
 * File: DaoUser.java
 * author: David Villalobos
 * Date: 2021/04/03
 */

package com.getyourtour.data;

import com.getyourtour.model.Country;
import com.getyourtour.model.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoUser {

    private final ConnectionDB db = ConnectionDB.instance();

    public DaoUser(){
    }

    public User get(Integer id) throws Exception {
        try{
            String sql = "SELECT * FROM [User] WHERE Id=%d";
            sql = String.format(sql, id);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()) {
                return map(rs);
            }
            throw new Exception("Log: GET/User/{" + id + "} Does not exist in DataBase");
        } catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public List<User> get() throws Exception {
        List<User> users = new ArrayList<>();
        try{
            ResultSet resultSet = db.executeQuery("SELECT * from [User]");
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
            if(0 == users.size()){
                throw new Exception("/users Does not exist any User in DataBase");
            }
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
        return users;
    }

    public User getByEmail(String email) throws Exception {
        try{
            String sql = "SELECT * FROM [User] WHERE Email='%s'";
            sql = String.format(sql, email);
            ResultSet rs = db.executeQuery(sql);
            if(rs.next()){
                return map(rs);
            }
            throw new Exception("/user/email/{" + email + "} Does not exist in DataBase");
        } catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer post(User u) throws Exception {
        String sql = "updateInsertUser 0, %d,'%s','%s','%s', '%s', '%s','%s', %d, 'Insert'";
        sql = String.format(sql, u.getCountry().getId(), u.getEmail(), u.getPassword(), u.getName(),
                    u.getLastName(), u.getIdentification(), u.getStringDate(), u.getAdmin());
        return db.executeInsert(sql);
    }

    public Integer put(User u) throws Exception {
        try{
            String sql = "updateInsertUser %d %d,'%s','%s','%s', '%s', '%s','%s', %d, 'Update'";
            sql=String.format(sql, u.getId(), u.getCountry().getId(), u.getEmail(), u.getPassword(), u.getName(),
                    u.getLastName(), u.getIdentification(), u.getStringDate(), u.getAdmin());
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/user/{" + u.getId() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer delete(Integer Id) throws Exception {
        try{
            String sql="DELETE FROM [User] WHERE Id=%d";
            sql = String.format(sql, Id);
            int result = db.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/user/{" + Id + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    private User map(ResultSet rs) throws Exception{
        Integer id = rs.getInt("Id");
        String email = rs.getString("Email");
        String password = rs.getString("Password");
        String name = rs.getString("Name");
        String lastName = rs.getString("LastName");
        String identification = rs.getString("Identification");
        Date birthday = rs.getDate("Birthday");
        Integer admin = rs.getInt("Admin");
        DaoCountry dc = new DaoCountry();
        Country country = dc.get(rs.getInt("Id_Country"), false);
        return new User(id, country, email, password, name, lastName, identification, birthday, admin);
    }

}