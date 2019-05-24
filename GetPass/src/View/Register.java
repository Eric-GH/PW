package View;

import Controller.CentralControl;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Register {

    CentralControl controller;
    public Stage regWindows = new Stage();
    public Label regMessage;
    public TextField reguser_tx = new TextField();
    public TextField regpass_tx = new TextField();
    public TextField regmail_tx = new TextField();
    Button regcancel = new Button("CANCEL");
    Button regconfirm = new Button("CONFIRM");

    public Register(){
        Scene regScene = new Scene(setRegBox());

        regScene.getStylesheets().add("CSS/SecondaryFrame.css");
        regWindows.setScene(regScene);
        regWindows.initModality(Modality.APPLICATION_MODAL);
    }

    public void setController(CentralControl controller){
        this.controller = controller;
        regcancel.setOnAction(controller::reg_Cancel);
        regconfirm.setOnAction(controller::LogIn_reg);
    }


    VBox setRegBox(){
        /*
        Set up elements
         */
        VBox regVBox = new VBox();
        Label title = new Label("REGISTRATION");
        Label reguser = new Label("USER NAME");
        Label regpass = new Label("PASSWORD");
        Label regmail = new Label("E-MAIL");
        regMessage = new Label();
        HBox regH = new HBox();

        regVBox.setId("regVBox");
        title.setId("regtitle");
        reguser.setId("regLabels");
        regpass.setId("regLabels");
        regmail.setId("regLabels");
        regMessage.setId("regmessage");
        reguser_tx.setId("regFields");
        regmail_tx.setId("regFields");
        regpass_tx.setId("regFields");
        regH.setId("regHBox");
        regcancel.setId("regbtn");
        regconfirm.setId("regbtn");

        /*
        Set up margin
         */
        HBox.setMargin(regcancel,new Insets(0,0,0,25));
        HBox.setMargin(regconfirm,new Insets(0,0,0,90));
        VBox.setMargin(reguser,new Insets(10,0,0,0));
        VBox.setMargin(reguser_tx,new Insets(0,0,0,55));
        VBox.setMargin(regpass,new Insets(15,0,0,0));
        VBox.setMargin(regpass_tx,new Insets(0,0,0,55));
        VBox.setMargin(regmail,new Insets(15,0,0,0));
        VBox.setMargin(regmail_tx,new Insets(0,0,0,55));
        VBox.setMargin(regMessage,new Insets(10,0,0,0));
        VBox.setMargin(regH,new Insets(5,0,0,0));

        regH.getChildren().addAll(regcancel,regconfirm);
        regVBox.getChildren().addAll(title,reguser,reguser_tx,regpass,regpass_tx,regmail,regmail_tx,regMessage,regH);




        return regVBox;
    }

}
