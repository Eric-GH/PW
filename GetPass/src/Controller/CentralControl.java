package Controller;


/**
 * Author: Hao Li
 * Date: 05/21,2019
 * The controller class, interactive between user and program
 */
import Model.CentralModel;
import Model.MyPassword;
import View.CentralView;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class CentralControl {
    private CentralModel model;
    private CentralView view;
    private int current_user_ID=0;
    private final int max = 14;
    private int start = 0;
    private int end = 14;


    /**
     * Set up the model
     * @param model Central model
     */
    public void setModel(CentralModel model){
        this.model = model;
    }

    /**
     * Set up the view
     * @param view Central View
     */
    public void setView(CentralView view){
        this.view = view;
    }


    /**
     * Action for Login submit button
     * @param event pressed
     */
    public void LogIn_submit(ActionEvent event){
        /*
        Try to log in to the program through the database
         */
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

    /**
     * Add up the key event for login
     * @param event
     */
    public void KeyPress(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            LogIn_submit(null);
        }

    }

    /**
     * Action for login frame registration button
     * @param event open new frame for register
     */
    public void OpenReg(ActionEvent event){
        view.register.regWindows.show();
    }


    /**
     * Action for registration frame to cancel the registration
     * @param event pressed
     */
    public void reg_Cancel(ActionEvent event){
        view.register.reguser_tx.setText("");
        view.register.regpass_tx.setText("");
        view.register.regmail_tx.setText("");
        view.register.regMessage.setText("");
        view.register.regWindows.close();
    }

    /**
     * Action for Registration frame to register as new user
     * @param event pressed
     */
    public void LogIn_reg(ActionEvent event){
        /*
        Make sure user fill in all boxes
         */
        if (view.register.reguser_tx.getText().isEmpty()){
            view.register.regMessage.setText("user name cannot be empty");
        }
        else if (view.register.regpass_tx.getText().isEmpty()){
            view.register.regMessage.setText("password cannot be empty");
        }
        else {
            model.addUser(view.register.reguser_tx.getText(),view.register.regpass_tx.getText());//register
            //make sure the user name not exist
            if (model.used_check){
                view.register.regMessage.setText("This user name already exist, please try again");
            }
            else {
                if (model.flag){
                    view.register.reguser_tx.setText("");
                    view.register.regpass_tx.setText("");
                    view.register.regmail_tx.setText("");
                    view.register.regMessage.setText("");
                    view.register.regWindows.close();
                    view.tips.tipMessage.setText("Congratulations! Register Successfully!");
                    view.tips.delete_flag=false;
                    view.tips.tipMessage.setTextFill(Color.BLACK);
                    view.tips.tipsWindows.show();

                }
                else {
                    view.register.regMessage.setText("There are something in error, please try again");
                }
            }
        }

    }


    /**
     * The action for open new frame to add new password record
     * @param event pressed
     */
    public void OpenAddFrame(ActionEvent event){
        view.addNewPass.Addwindows.show();
    }

    /**
     * The action for add new password record
     * @param event pressed
     */
    public void AddNewRec(ActionEvent event){
        /*
        Make sure user fill in all boxes
         */
        if (view.addNewPass.address_TX.getText().isEmpty()){
            view.addNewPass.warning.setText("Password name cannot be Empty");
        }
        else if (view.addNewPass.user_TX.getText().isEmpty()){
            view.addNewPass.warning.setText("User name cannot be Empty");
        }
        else if (view.addNewPass.password_TX.getText().isEmpty()){
            view.addNewPass.warning.setText("Password cannot be Empty");
        }
        else {
            model.addPass(current_user_ID,view.addNewPass.address_TX.getText(),view.addNewPass.user_TX.getText(),view.addNewPass.password_TX.getText());
            if (model.flag){
                view.addNewPass.Addwindows.close();
                view.addNewPass.address_TX.setText("");
                view.addNewPass.user_TX.setText("");
                view.addNewPass.password_TX.setText("");
                view.addNewPass.warning.setText("");
                view.tips.tipMessage.setText("Add New Password Successfully");
                view.tips.delete_flag=false;
                view.tips.tipMessage.setTextFill(Color.BLACK);
                view.tips.tipsWindows.show();

            }
            else {
                view.addNewPass.warning.setText("Error, Please Try again");
            }
        }
        // after add new record, update the display
        initialList();
    }

    /**
     * the action for user cancel add new password record
     * @param event pressed
     */
    public void CancelAdd(ActionEvent event){
        view.addNewPass.address_TX.setText("");
        view.addNewPass.user_TX.setText("");
        view.addNewPass.password_TX.setText("");
        view.addNewPass.warning.setText("");
        view.addNewPass.Addwindows.close();
    }

    /**
     * The action for open new frame ask user delete the all password records
     * @param event pressed
     */
    public void OpenDeleteConfirmFrame(ActionEvent event){
        view.tips.delete_flag = true;
        view.tips.tipMessage.setTextFill(Color.RED);
        view.tips.tipMessage.setText("Are you sure to delete ALL password records?");
        view.tips.tipsWindows.show();
    }


    /**
     * Action to quit the program
     * @param event pressed
     */
    public void QuitSys(ActionEvent event){
        System.exit(0);
    }


    /**
     * Action for search the password records
     * @param event pressed
     */
    public void SearchRec(ActionEvent event){
        model.SearchRead(current_user_ID,view.search_tx.getText());
        initialList();
        view.modelChanged();
    }

    /**
     * Action to show all the password records
     * @param event pressed
     */
    public void ShowAllRec(ActionEvent event){
        model.AllPassRead(current_user_ID);
        initialList();
        view.search_tx.setText(null);
        view.modelChanged();
    }


    /**
     * Action to turn page to prev
     * @param event pressed
     */
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

    /**
     * Action to turn page next
     * @param event pressed
     */
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


    /**
     * Action to delete one record for the password records
     * @param rec_id
     */
    public void SingleDelete(int rec_id){
        model.delete(rec_id);
        initialList();

    }

    /**
     * Action for give user meesages
     * @param event pressed
     */
    public void tipsFunction(ActionEvent event){
        if (view.tips.delete_flag){
            model.deleteAllPass(current_user_ID);
            view.tips.tipsWindows.close();
            initialList();
            view.modelChanged();
        }
        else {
            view.tips.tipsWindows.close();
        }

    }


    /**
     * Initial the count page parameters
     */
    void initialParameter(){
        start=0;
        end = 14;
    }

    /**
     * set left page button disable or not
     * @param set set
     */
    void setLeftDisable(boolean set){
        view.left.setDisable(set);
    }

    /**
     * Set right page button disable or not
     * @param set set
     */
    void setRightDisable(boolean set){
        view.right.setDisable(set);
    }

    /**
     *  Check the length of the list
     * @param list list
     * @return length
     */
    Boolean CheckInitialLength(ArrayList<MyPassword> list){
        return list.size()>14;
    }



    /**
     * Set up the list of password records
     * @param start start
     * @param end end
     */
    private void setList(int start, int end){
        view.viewListShow = new ArrayList<>();
        for (int i = start;i<end;i++){
            view.viewListShow.add(model.dataList.get(i));
        }
        view.modelChanged();
    }

    /**
     * Initialize the list of password records
     */
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




}
