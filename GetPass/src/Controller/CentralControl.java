package Controller;

import Model.Database;
import Model.MyPassword;
import View.CentralView;
import javafx.event.ActionEvent;

import java.util.ArrayList;


public class CentralControl {
    Database model;
    CentralView view;
    int current_user_ID=0;
    final int max = 14;
    boolean endFlag = false;
    int start = 0;
    int end = 14;

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
            current_user_ID = model.user_id;//set current id
            model.AllPassRead(current_user_ID);//initially read all record
            initialList();
            view.LogStage.close();//close the log in frame
            view.viewStage.show();// show main frame
        }
        else {
            view.LogIn_message.setText("user name or passwords not correct");
        }
        view.modelChanged();//let view know the model changed
    }

    public void LogIn_reg(ActionEvent event){
        view.LogStage.close();
        view.viewStage.show();
    }





    public void OpenAddFrame(ActionEvent event){
        view.addNewPass.Addwindows.show();
    }


    public void AddNewRec(ActionEvent event){
        model.addPass(current_user_ID,view.addNewPass.address_TX.getText(),view.addNewPass.user_TX.getText(),view.addNewPass.password_TX.getText());
        if (model.flag){
            view.addNewPass.Addwindows.close();
            view.addNewPass.address_TX.setText(null);
            view.addNewPass.user_TX.setText(null);
            view.addNewPass.password_TX.setText(null);
            view.tips.tipMessage.setText("Add New Password Successfully");
            view.tips.delete_flag=false;
            view.tips.tipsWindows.show();


        }
        else {
            view.addNewPass.warning.setText("Error, Please Try again");
        }
        initialList();

    }

    public void CancelAdd(ActionEvent event){
        view.addNewPass.Addwindows.close();
    }

    public void OpenDeleteConfirmFrame(ActionEvent event){

    }

    public void DeleteAllRec(ActionEvent event){
        //TODO 弹出二级窗口，向用户确认
    }

    public void CancelDelete(ActionEvent event){


    }

    public void QuitSys(ActionEvent event){
        System.exit(0);
    }






    public void SearchRec(ActionEvent event){
        model.SearchRead(current_user_ID,view.search_tx.getText());
        initialList();
        view.modelChanged();
    }


    public void ShowAllRec(ActionEvent event){
        model.AllPassRead(current_user_ID);
        initialList();
        view.search_tx.setText(null);
        view.modelChanged();
    }

    public void PageLeft(ActionEvent event){
        start = start -14;
        end = end-14;
        if (start!=0 && start>=14){
            setLeftDisable(false);
            setRightDisable(false);
        }else {
            setLeftDisable(true);
            setRightDisable(false);
        }
        setList(start,end);
    }

    public void PageRight(ActionEvent event){
        start = start + 14;
        end = end + 14;

        if (model.dataList.size()>end){
            setList(start,end);
            setLeftDisable(false);
            setRightDisable(false);
        }
        else {
            setList(start,model.dataList.size());
            setRightDisable(true);
            setLeftDisable(false);
        }


    }



    void initialParameter(){
        start=0;
        end = 14;
        endFlag = false;
    }

    void setLeftDisable(boolean set){
        view.left.setDisable(set);
    }

    void setRightDisable(boolean set){
        view.right.setDisable(set);
    }

    Boolean CheckInitialLength(ArrayList<MyPassword> list){
        return list.size()>14;
    }

    public void SingleDelete(int rec_id){
        model.delete(rec_id);
        setList(0,max);

    }

    private void setList(int start, int end){
        view.viewListShow = new ArrayList<>();
        for (int i = start;i<end;i++){
            view.viewListShow.add(model.dataList.get(i));
        }
        view.modelChanged();
    }

    void initialList(){
        if (CheckInitialLength(model.dataList)){
            setList(0,max);
            setLeftDisable(true);
            setRightDisable(false);
            initialParameter();// initial left right parameters
        }
        else{
            setList(0,model.dataList.size());
            setLeftDisable(true);
            setRightDisable(true);
        }
    }

    public void tipsFunction(ActionEvent event){
        if (view.tips.delete_flag){
            model.deleteAllPass(current_user_ID);

        }
        else {
            view.tips.tipsWindows.close();
        }
        initialList();
        view.modelChanged();
    }
}
