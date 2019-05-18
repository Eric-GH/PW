package Controller;

import Model.Database;
import View.CentralView;
import javafx.event.ActionEvent;


public class CentralControl {
    Database model;
    CentralView view;
    int current_user_ID=0;

    public void setModel(Database model){
        this.model = model;
    }

    public void setView(CentralView view){
        this.view = view;
    }






    public void LogIn_submit(ActionEvent event){
        //TODO 不要忘记添加初始化view list，以便一开始就可以显示数据
        model.login(view.username_tx.getText(),view.password_tx.getText());
        if (model.flag){
            current_user_ID = model.user_id;
            view.viewStage.show();
        }
        else {
            view.LogIn_message.setText("user name or passwords not correct");
        }

    }

    public void LogIn_reg(ActionEvent event){
        view.viewStage.show();
    }








    public void AddNewRec(ActionEvent event){

    }


    public void DeleteAllRec(ActionEvent event){

    }

    public void QuitSys(ActionEvent event){
        System.exit(0);
    }






    public void SearchRec(ActionEvent event){

    }


    public void ShowAllRec(ActionEvent event){

    }

    public void PageLeft(ActionEvent event){

    }

    public void PageRight(ActionEvent event){

    }







    public void SingleDelete(ActionEvent event){

    }
}
