package View;

/**
 * Author: Hao Li
 * Date: 05/19,2019
 * The Secondary frame for all new password records
 * JavaFX
 */

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

public class Add {
    public Stage Addwindows = new Stage();//Stage for all new password frame
    private Button cancel; // cancel adding button
    private Button submit; // submit the new password
    CentralControl control; // central controller
    VBox vBox; // VBox layout for components

    /*
        Create each TextField elements
         */
    public TextField address_TX;
    public TextField user_TX;
    public TextField password_TX;
    public Label warning;

    /**
     * Constructor for all frame
     */
    public Add(){
        Scene scene = new Scene(setVBox());
        scene.getStylesheets().add("CSS/SecondaryFrame.css");
        Addwindows.initModality(Modality.APPLICATION_MODAL);// Set this frame always be the top and primary when called
        Addwindows.setScene(scene);
    }

    /**
     * set controller
     * @param controller Central controller
     */
    void setController(CentralControl controller){
        this.control = controller;
        submit.setOnAction(controller::AddNewRec);
        cancel.setOnAction(controller::CancelAdd);
        address_TX.setOnKeyPressed(controller::AddKeyPress);
        user_TX.setOnKeyPressed(controller::AddKeyPress);
        password_TX.setOnKeyPressed(controller::AddKeyPress);

    }

    /**
     * Set Up the VBox frame and it's elements
     * @return VBox
     */
    VBox setVBox(){
        vBox= new VBox();
        address_TX = new TextField();
        user_TX = new TextField();
        password_TX = new TextField();
        warning = new Label();
        /*
        Create labels for VBox
         */
        Label title = new Label("Add New Password");
        Label address = new Label("Name of Password");
        Label newUserName = new Label("USER NAME");
        Label newPassWord = new Label("PASSWORD");
        cancel = new Button("Cancel");
        submit = new Button("Submit");
        HBox hBox = new HBox();

        /*
        Set up the id for each components
         */
        vBox.setId("vbox");
        hBox.setId("hbox");
        title.setId("title_mgs");
        warning.setId("warning_mgs");
        address.setId("labels");
        newUserName.setId("labels");
        newPassWord.setId("labels");
        address_TX.setId("fields");
        user_TX.setId("fields");
        password_TX.setId("fields");
        cancel.setId("new_btn");
        submit.setId("new_btn");

        /*
          Set up the margin of the VBox
         */
        VBox.setMargin(address,new Insets(10,0,0,0));
        //VBox.setMargin(title,new Insets(0,0,0,80));
        VBox.setMargin(newPassWord,new Insets(20,0,0,0));
        VBox.setMargin(newUserName,new Insets(20,0,0,0));
        VBox.setMargin(user_TX,new Insets(0,0,0,50));
        VBox.setMargin(warning,new Insets(10,0,0,0));
        VBox.setMargin(address_TX,new Insets(0,0,0,50));
        VBox.setMargin(hBox,new Insets(10,0,0,0));
        VBox.setMargin(password_TX,new Insets(0,0,0,50));
        HBox.setMargin(cancel,new Insets(0,0,0,20));
        HBox.setMargin(submit,new Insets(0,0,0,100));

        /*
         ADD up addnew elements to the VBox
         */
        hBox.getChildren().addAll(cancel,submit);
        vBox.getChildren().addAll(title,address,address_TX,newUserName,user_TX,newPassWord,password_TX,warning,hBox);


        return vBox;
    }
}
