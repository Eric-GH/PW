package Databass;

/**
 * Author: Hao Li
 * Date: 05/01,2019
 */

import java.sql.PreparedStatement;

public class AddNewPass {
    Connect connect = new Connect();
    String message = null;


    /**
     * Adding the new PassWord Record to the system
     * @param user_id belong to which user
     * @param address The name of the password
     * @param nameOFpass user name of the password
     * @param thePassword // password
     */
    void add(int user_id,String address, String nameOFpass, String thePassword){
        String query;
        connect.connection();
        if (connect.connect!=null){
            try{
                query = "INSERT INTO password_garage(user_manage_id,password_address,password_name,password_pass) VALUES(?,?,?,?)";
                PreparedStatement statement = connect.connect.prepareStatement(query);
                statement.setInt(1,user_id);
                statement.setString(2,address);
                statement.setString(3,nameOFpass);
                statement.setString(4,thePassword);
                int result = statement.executeUpdate();
                if (result > 0){
                    message = "Adding successful";

                }
                else {
                    message= " adding fail!!!";
                }
                connect.connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
