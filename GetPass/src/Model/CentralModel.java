package Model;

/**
 * Author: Hao Li
 * Date: 05/10,2019
 * This class is the model class, most of function is work to
 * exchange data between each tables in database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CentralModel {
    public Connection connection; // connection
    public boolean flag = false; // flag for each function's state
    public boolean used_check = false; // check new user name duplication.
    public int user_id = 0; // current user id
    public ArrayList<MyPassword> dataList; // list contained all password records
    public ModelListener subscriber;
    public boolean search_flag = false; // check current viewList is on search or all records
    String search_Aim = null; // current search target

    /**
     * Connect to local database
     * @return connection variable if successful or fail message if fail.
     */
    public Connection Connect(){
        // parameters of DriverManager.
        String DbURL = "jdbc:postgresql://localhost:5432/postgres";
        String DbUserName = "postgres";
        String DbUserPass = "lns945";
        // try the connection
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DbURL,DbUserName,DbUserPass);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }


    /**
     * Let user log in their account
     * @param username user name for log in
     * @param password user password for log in
     */
    public void login(String username, String password){
        Connect();
        if (connection!=null){
            try {
                String query = "SELECT id FROM main_user WHERE user_name = ? AND pass_word = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1,username);
                statement.setString(2,password);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()){
                    flag = false;
                }
                else {
                    flag = true;
                    user_id = rs.getInt(1);

                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
    }


    /**
     * Read all passwords from database
     * @param id
     */
    public void AllPassRead(int id){
        Connect();
        if (connection!=null){
            try{
                dataList = new ArrayList<>();
                String query = "SELECT id,password_address,password_name,password_pass FROM password_garage WHERE user_manage_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();

                while (rs.next()){
                    dataList.add(new MyPassword(rs.getInt(1),id,rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
        search_flag = false;
        notifySubscribers();
    }


    /**
     * Read the password data from database by search
     * @param id current user's id
     * @param names searched target
     */
    public void SearchRead(int id,String names){
        Connect();
        String searchName = "%"+names+"%";
        if (connection!=null){
            try{
                search_Aim = names;
                String query = "SELECT id,password_address,password_name,password_pass FROM password_garage WHERE user_manage_id = ? AND password_address like ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2,searchName);
                ResultSet rs = statement.executeQuery();
                dataList = new ArrayList<>();
                while (rs.next()){
                    dataList.add(new MyPassword(rs.getInt(1),id,rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
        search_flag = true;
        notifySubscribers();
    }



    /**
     * Delete the current password record from database
     * @param pass_id the id of deleted password
     */
    public void delete(int pass_id){
        Connect();
        if (connection!=null){
            try{
                System.out.println(pass_id);
                String query = "DELETE FROM password_garage WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,pass_id);
                int result = statement.executeUpdate();
                if (result > 0) {
                    flag = true;
                    AllPassRead(user_id);

                }
                else {
                    flag = false;
                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }

        if (search_flag){
            SearchRead(user_id,search_Aim);
        }
        else {
            AllPassRead(user_id);
        }
        notifySubscribers(); // tell the view the model changed
    }

    /**
     * Delete All password records from database
     * @param currentID current user id
     */
    public void deleteAllPass(int currentID){
        Connect();
        if (connection!=null){
            try{
                String Q = "DELETE FROM password_garage WHERE user_manage_id = ?";
                PreparedStatement statement = connection.prepareStatement(Q);
                statement.setInt(1,currentID);
                int R = statement.executeUpdate();
                if (R > 0){
                    flag = true;
                }
                else {
                    flag = false;
                }
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
        AllPassRead(user_id);
        notifySubscribers();
    }


    /**
     * Add new user to the system
     * @param name user name
     * @param pass user password
     */
    public void addUser(String name,String pass){
        String query;
        PreparedStatement statement;
        Connect();
        if (connection!=null){
            try{
                //One more check if the user name already been used
                query = "SELECT user_name FROM main_user WHERE user_name = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1,name);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()){
                    // add user in
                    query = "INSERT INTO main_user(user_name,pass_Word) VALUES(?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1,name);
                    statement.setString(2,pass);
                    int result = statement.executeUpdate();
                    if (result > 0){
                        flag = true;
                        used_check=false;
                    }
                    else {
                        flag = false;
                        used_check=false;
                    }
                }
                else {
                    used_check=true;
                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
    }



    /**
     * Adding the new PassWord Record to the system
     * @param user_id belong to which user
     * @param address The name of the password
     * @param nameOFpass user name of the password
     * @param thePassword // password
     */
    public void addPass(int user_id,String address, String nameOFpass, String thePassword){
        String query;
        Connect();
        if (connection!=null){
            try{
                query = "INSERT INTO password_garage(user_manage_id,password_address,password_name,password_pass) VALUES(?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1,user_id);
                statement.setString(2,address);
                statement.setString(3,nameOFpass);
                statement.setString(4,thePassword);
                int result = statement.executeUpdate();
                if (result > 0){
                    flag = true;
                }
                else {
                    flag = false;
                }
                connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Failed");
        }
        if (search_flag){
            SearchRead(user_id,search_Aim);
        }
        else {
            AllPassRead(user_id);
        }
        notifySubscribers();
    }

    /**
     * add new subscriber to list od subscriber
     * @param sub new subscriber
     */
    public void addSubscriber(ModelListener sub){
        this.subscriber = sub;
    }

    /**
     * Tell the subscriber there are something changed
     */
    private  void notifySubscribers(){
        this.subscriber.modelChanged();
    }
}
