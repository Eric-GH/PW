package Controller;

import Model.Database;
import View.CentralView;
import javafx.event.ActionEvent;
import org.omg.CORBA.PUBLIC_MEMBER;

public class CentralControl {
    Database model;
    CentralView view;
    public CentralControl(){

    }

    public void setModel(Database model){
        this.model = model;
    }

    public void setView(CentralView view){
        this.view = view;
    }
    public void LogIn_submit(ActionEvent event){
        //TODO 不要忘记添加初始化view list，以便一开始就可以显示数据
    }

    public void LogIn_reg(ActionEvent event){

    }

    public void SingleDelete(ActionEvent event){

    }

}
