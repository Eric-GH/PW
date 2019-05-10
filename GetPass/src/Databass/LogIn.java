package Databass;


/**
 * Author: Hao Li
 * Date: 05/03,2019
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogIn {
    Connect connect = new Connect();
    public String message = null;

    /**
     * Let user log in their account
     * @param username user name for log in
     * @param password user password for log in
     */
    public void login(String username, String password){
        connect.connection();
        if (connect.connect !=null){
            try {
                String query = "SELECT id FROM main_user WHERE user_name = ? AND pass_word = ?";
                PreparedStatement statement = connect.connect.prepareStatement(query);
                statement.setString(1,username);
                statement.setString(2,password);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()){
                    message = "Wrong user name or password";
                    System.out.println(message);
                    connect.connect.close();
                }
                else {
                    message = "True";
                    System.out.println(message);
                    connect.connect.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
