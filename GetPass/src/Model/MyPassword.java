package Model;

/**
 * Author: Hao Li
 * Date: 04/27,2019
 * This class is defined new type of variable
 */
public class MyPassword {
    public int id;
    public int manage_id;
    public String address;
    public String names;
    public String password;

    /*
    Construct
     */
    public MyPassword(int id, int manage_id,String address, String names, String password){
        this.id = id;
        this.manage_id = manage_id;
        this.address = address;
        this.names = names;
        this.password = password;
    }

    /*
     * Geter and Setter for all variables
     */

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getNames() {
        return names;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Display the variable in the MyPassword
     */
    public void Display() {
        System.out.println("id: "+this.id+"\nmanage_id: "+this.manage_id+"\nAddress: "+this.address+"\nName: "+this.names+"\nPass: "+this.password);
    }
}
