package Frame.SecondaryFrames;

/**
 * Author: Hao Li
 * Date: 05/07,2019
 */

import Databass.AddNewPass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Add {
    public Stage Addwindows = new Stage();
    VBox vBox = new VBox();
    HBox hBox = new HBox();
    Tip tips;
    public int current_user_id;
    /*
        Create each TextField elements
         */
    TextField address_TX = new TextField();
    TextField user_TX = new TextField();
    TextField password_TX = new TextField();
    Label warning = new Label();

    public Add(int current_user_id){
        this.current_user_id = current_user_id;
        Scene scene = new Scene(setVBox());
        Addwindows.setScene(scene);
    }



    /**
     * Set Up the VBox frame and it's elements
     * @return VBox
     */
    VBox setVBox(){

        /*
        Create labels for VBox
         */
        Label title = new Label("Add New Password");
        Label address = new Label("Name of Password");
        Label user_name = new Label("USER NAME");
        Label pass_word = new Label("PASSWORD");


        /*
        Set up the size of VBox and each elements
         */
        vBox.setPrefSize(300,400);

        title.setPrefSize(140,60);
        title.setAlignment(Pos.CENTER);

        address.setPrefSize(120,20);
        address.setAlignment(Pos.CENTER);

        user_name.setPrefSize(120,20);
        user_name.setAlignment(Pos.CENTER);

        pass_word.setPrefSize(120,20);
        pass_word.setAlignment(Pos.CENTER);

        warning.setPrefSize(300,40);
        warning.setAlignment(Pos.CENTER);

        address_TX.setPrefHeight(30);
        address_TX.setMaxWidth(200);

        user_TX.setPrefHeight(30);
        user_TX.setMaxWidth(200);

        password_TX.setPrefHeight(30);
        password_TX.setMaxWidth(200);

        /*
          Set up the margin of the VBox
         */
        VBox.setMargin(title,new Insets(0,0,0,80));
        VBox.setMargin(address,new Insets(10,0,0,80));
        VBox.setMargin(user_name,new Insets(20,0,0,80));
        VBox.setMargin(pass_word,new Insets(0,0,0,80));
        VBox.setMargin(warning,new Insets(10,0,0,0));
        VBox.setMargin(address_TX,new Insets(0,0,0,50));
        VBox.setMargin(user_TX,new Insets(0,0,0,50));
        VBox.setMargin(password_TX,new Insets(0,0,0,50));
        VBox.setMargin(hBox,new Insets(20,0,0,0));
        /*
         ADD up all elements to the VBox
         */
        vBox.getChildren().addAll(title,address,address_TX,user_name,user_TX,pass_word,password_TX,warning,setHBox());


        return vBox;
    }

    HBox setHBox(){
        /*
        Create elements
         */
        Button cancel = new Button("Cancel");
        Button submit = new Button("Submit");

        /*
        Set up the size of HBox and its elements
         */
        hBox.setPrefSize(300,60);
        cancel.setPrefSize(80,30);
        submit.setPrefSize(80,30);

        /*
        Set up margin of the HBox
         */
        HBox.setMargin(cancel,new Insets(0,0,0,20));
        HBox.setMargin(submit,new Insets(0,0,0,100));

        /*
        ADD actions on each button
         */
        cancel.setOnAction(e->{
            address_TX.setText(null);
            user_TX.setText(null);
            password_TX.setText(null);
            Addwindows.close();
        });
        submit.setOnAction(e->{
            AddNewPass addNewPass = new AddNewPass();
            if (!user_TX.getText().isEmpty() && !password_TX.getText().isEmpty()){
                addNewPass.add(current_user_id,this.address_TX.getText(),this.user_TX.getText(),this.password_TX.getText());
                if (addNewPass.message.equals("true")){
                    warning.setText("Adding Successfully");
                    address_TX.setText(null);
                    user_TX.setText(null);
                    password_TX.setText(null);
                    Addwindows.close();
                    tips = new Tip();
                    tips.tipMessage.setText("successful");
                    tips.tipsWindows.show();
                }
                else {
                    warning.setText("Adding Error");
                    tips = new Tip();
                    tips.tipMessage.setText("Adding Error");
                    tips.tipsWindows.show();
                }
            }
            else if (user_TX.getText().isEmpty()){
                warning.setText("User Name should not be EMPTY!");
            }
            else {
                warning.setText("password should not be EMPTY!");
            }
        });
        /*
        Add up all elements to the HBox
         */
        hBox.getChildren().addAll(cancel,submit);


        return hBox;
    }
}
