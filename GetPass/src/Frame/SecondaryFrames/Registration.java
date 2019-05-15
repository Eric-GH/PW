package Frame.SecondaryFrames;

import Databass.AddNewUser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Registration {
    public Stage regWindows = new Stage();
    VBox regVBox = new VBox();
    Label title = new Label("REGISTRATION");
    Label reguser = new Label("USER NAME");
    Label regpass = new Label("PASSWORD");
    Label regmail = new Label("E-MAIL");
    Label regMessage = new Label();
    public TextField reguser_tx = new TextField();
    public TextField regpass_tx = new TextField();
    public TextField regmail_tx = new TextField();
    Button regcancel = new Button("CANCEL");
    Button regconfirm = new Button("CONFIRM");
    public Registration(){
        Scene regscene = new Scene(setUpReg());
        regWindows.setScene(regscene);
    }

    /**
     * Set up all elements of the vbox
     * @return
     */
    VBox setUpReg(){

        /*
        Set up the size
         */
        regVBox.setPrefSize(300,400);
        title.setPrefSize(300,60);
        reguser.setPrefSize(300,25);
        regpass.setPrefSize(300,25);
        regmail.setPrefSize(300,25);
        regMessage.setPrefSize(300,50);
        reguser_tx.setMaxSize(185,25);
        regpass_tx.setMaxSize(185,25);
        regmail_tx.setMaxSize(185,25);

        /*
        Set up position
         */
        title.setAlignment(Pos.CENTER);
        reguser.setAlignment(Pos.CENTER);
        regpass.setAlignment(Pos.CENTER);
        regmail.setAlignment(Pos.CENTER);
        regMessage.setAlignment(Pos.CENTER);

        /*
        Set up margin
         */
        VBox.setMargin(reguser,new Insets(10,0,0,0));
        VBox.setMargin(reguser_tx,new Insets(0,0,0,55));
        VBox.setMargin(regpass,new Insets(15,0,0,0));
        VBox.setMargin(regpass_tx,new Insets(0,0,0,55));
        VBox.setMargin(regmail,new Insets(15,0,0,0));
        VBox.setMargin(regmail_tx,new Insets(0,0,0,55));
        VBox.setMargin(regMessage,new Insets(10,0,0,0));
        VBox.setMargin(setRegButton(),new Insets(20,0,0,0));

        /*
         Add children
         */
        regVBox.getChildren().addAll(title,reguser,reguser_tx,regpass,regpass_tx,regmail,regmail_tx,regMessage,setRegButton());


        return regVBox;
    }

    HBox setRegButton(){
        HBox regH = new HBox();
        /*
        Set up size
         */
        regH.setPrefSize(300,30);
        regcancel.setPrefSize(80,30);
        regconfirm.setPrefSize(80,30);
        /*
        Set up margin
         */
        HBox.setMargin(regcancel,new Insets(0,0,0,20));
        HBox.setMargin(regconfirm,new Insets(0,0,0,100));

        /*
        Set up actions on buttons
         */
        regcancel.setOnAction(ex->{
            regWindows.close();
        });
        regconfirm.setOnAction(e->{
            AddNewUser addNewUser = new AddNewUser();
            addNewUser.addNewUser(reguser_tx.getText(),regpass_tx.getText());
            if (addNewUser.message.equals("TRUE")){
                regWindows.close();
                Tip t = new Tip();
                t.tipMessage.setText("Registration Successfully!");
                t.tipsWindows.show();
            }
            else if (addNewUser.message.equals("USED")){
                regMessage.setText("This user name already exist.");
                regpass_tx.setText(null);
            }
            else {
                regMessage.setText("Error! Please try again");
                regpass_tx.setText(null);
            }
        });
        /*
         Add children
         */
        regH.getChildren().addAll(regcancel,regconfirm);
        return regH;
    }
}
