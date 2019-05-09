package Databass;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Function for connect the database
 */
public class Connect {
    // connection variables
    public Connection connect = null;

    /**
     * Connect to local database
     * @return connection variable if successful or fail message if fail.
     */
    public Connection connection(){
        // parameters of DriverManager.
        String DbURL = "jdbc:postgresql://localhost:5432/postgres";
        String DbUserName = "postgres";
        String DbUserPass = "lns945";
        // try the connection
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(DbURL,DbUserName,DbUserPass);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Connection Fail");
            System.exit(1);
        }
        System.out.println("Connection Successful");
        return connect;
    }
/*
    public static void main(String[] args){
        Connect c = new Connect();
        c.connection();
    }

 */
}
