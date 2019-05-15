package Databass;

/**
 * Author: Hao Li
 * Date: 05/01,2019
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddNewUser {
    Connect connect = new Connect();
    String message = null;

    /**
     * Add new user to the system
     * @param name user name
     * @param pass user password
     */
    public void addNewUser(String name,String pass){
        String query;
        PreparedStatement statement;
        connect.connection();
        if (connect.connect!=null){
            try{
                //One more check if the user name already been used
                query = "SELECT user_name FROM main_user WHERE user_name = ?";
                statement = connect.connect.prepareStatement(query);
                statement.setString(1,name);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()){
                    // add user in
                    query = "INSERT INTO main_user(user_name,pass_Word) VALUES(?,?)";
                    statement = connect.connect.prepareStatement(query);
                    statement.setString(1,name);
                    statement.setString(2,pass);
                    int result = statement.executeUpdate();
                    if (result > 0){
                        message = "Adding user successfully";
                    }
                    else {
                        message = "Adding user failed!";
                    }
                }
                else {
                    message = "This user name has been used";
                }
                connect.connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
