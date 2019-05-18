package View;


import javafx.geometry.Insets;
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
        scene.getStylesheets().add("./CSS/SecondaryFrame.css");
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
        Label newUserName = new Label("USER NAME");
        Label newPassWord = new Label("PASSWORD");
        Button cancel = new Button("Cancel");
        Button submit = new Button("Submit");
        HBox hBox = new HBox();


        vBox.setId("vbox");
        hBox.setId("hbox");
        title.setId("title_mgs");
        warning.setId("title_mgs");
        address.setId("labels");
        newUserName.setId("labels");
        newPassWord.setId("labels");
        address_TX.setId("fields");
        user_TX.setId("fields");
        password_TX.setId("fields");
        cancel.setId("new_btn");
        submit.setId("new_btn");
        /*
        Set up the size of VBox and each elements
         */
        vBox.setPrefSize(300,400);



        /*
          Set up the margin of the VBox
         */
        VBox.setMargin(address,new Insets(10,0,0,80));
        VBox.setMargin(title,new Insets(0,0,0,80));
        VBox.setMargin(newPassWord,new Insets(0,0,0,80));
        VBox.setMargin(newUserName,new Insets(20,0,0,80));
        VBox.setMargin(user_TX,new Insets(0,0,0,50));
        VBox.setMargin(warning,new Insets(10,0,0,0));
        VBox.setMargin(address_TX,new Insets(0,0,0,50));
        VBox.setMargin(hBox,new Insets(20,0,0,0));
        VBox.setMargin(password_TX,new Insets(0,0,0,50));
        HBox.setMargin(cancel,new Insets(0,0,0,20));
        HBox.setMargin(submit,new Insets(0,0,0,100));


        /*
        Set actions on buttons
         */
        //TODO
        //cancel.setOnAction();
        //submit.setOnAction();


        /*
         ADD up all elements to the VBox
         */
        hBox.getChildren().addAll(cancel,submit);
        vBox.getChildren().addAll(title,address,address_TX,newUserName,user_TX,newPassWord,password_TX,warning,hBox);


        return vBox;
    }
}
