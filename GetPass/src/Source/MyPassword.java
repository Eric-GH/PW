package Source;

/**
 * Author: Hao Li
 * Date: 05/05,2019
 * This class is defined new type of variable --MyPassword
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

    public void setId(int id) {
        this.id = id;
    }

    public int getManage_id() {
        return manage_id;
    }

    public void setManage_id(int manage_id) {
        this.manage_id = manage_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Display the variable in the MyPassword
     */
    public void Display() {
        System.out.println("id: "+this.id+"\nmanage_id: "+this.manage_id+"\nAddress: "+this.address+"\nName: "+this.names+"\nPass: "+this.password);
    }
}
