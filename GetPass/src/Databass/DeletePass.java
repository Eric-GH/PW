package Databass;

/**
 * Author: Hao Li
 * Date: 05/03,2019
 */

import java.sql.PreparedStatement;

public class DeletePass {
    public Connect connect = new Connect();
    public String message=null;

    /**
     * Delete the password record
     * @param pass_id the id of deleted password
     */
    public void Delete(int pass_id){
        connect.connection();
        if (connect.connect!=null){
            try{
                String query = "DELETE FROM password_garage WHERE id = ?";
                PreparedStatement statement = connect.connect.prepareStatement(query);
                statement.setInt(1,pass_id);
                int result = statement.executeUpdate();
                if (result > 0) {
                    message = "true";
                }
                else {
                    message = "false";
                }
                connect.connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void DeleteAll(int currentID){
        connect.connection();
        if (connect.connect!=null){
            try{
                String Q = "DELETE FROM password_garage WHERE user_manage_id = ?";
                PreparedStatement statement = connect.connect.prepareStatement(Q);
                statement.setInt(1,currentID);
                int R = statement.executeUpdate();
                if (R > 0){
                    message = "true";
                }
                else {
                    message = "false";
                }
            connect.connect.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
