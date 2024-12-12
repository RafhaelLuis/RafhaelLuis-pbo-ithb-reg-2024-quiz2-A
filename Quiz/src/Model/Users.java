package Model;

import Controller.DatabaseHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private int users_id;
    private String name;
    private String email;
    private String password;
    public int getUsers_id() {
        return users_id;
    }
    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Users(int users_id, String name, String email, String password) {
        this.users_id = users_id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public Users() {
    }
    
    public static Users getUser(int userId) {
        DatabaseHandler conn = new DatabaseHandler();
        Users user = new Users();

        try {
            conn.connect();
            String query = "SELECT * FROM users WHERE user_id=?";
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setUsers_id(rs.getInt("userId"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return user;
    }

    

}
