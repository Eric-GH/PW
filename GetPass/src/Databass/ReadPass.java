package Databass;



import Source.MyPassword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReadPass {
    ArrayList<MyPassword> list;
    ArrayList<MyPassword> searchList;
    Connect connect = new Connect();

    void ReadAll(int id){
        connect.connection();
        if (connect.connect!=null){
            try{
                String query = "SELECT id,password_address,password_name,password_pass FROM password_garage WHERE user_manage_id = ?";
                PreparedStatement statement = connect.connect.prepareStatement(query);
                statement.setInt(1,id);
                ResultSet rs = statement.executeQuery();
                list = new ArrayList<>();
                while (rs.next()){
                    list.add(new MyPassword(rs.getInt(1),id,rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                connect.connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    void SearchRead(int id,String names){
        connect.connection();
        String searchName = "%"+names+"%";
        if (connect.connect!=null){
            try{
                String query = "SELECT id,password_address,password_name,password_pass FROM password_garage WHERE user_manage_id = ? AND password_address like ?";
                PreparedStatement statement = connect.connect.prepareStatement(query);
                statement.setInt(1,id);
                statement.setString(2,searchName);
                ResultSet rs = statement.executeQuery();
                searchList = new ArrayList<>();
                while (rs.next()){
                    searchList.add(new MyPassword(rs.getInt(1),id,rs.getString(2),rs.getString(3),rs.getString(4)));
                }
                connect.connect.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
