package Frame;

/**
 * Author: Hao Li
 * Date: 04/24,2019
 */

import Databass.AddNewUser;
import Databass.LogIn;
import Frame.SecondaryFrames.Registration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class LogIn_Frame extends Application {

    private LogIn logInDB = new LogIn();
    private Stage LogInWindow = new Stage();
    Label name = new Label("USER NAME: ");
    Label pass = new Label("PASSWORD: ");
    TextField name_TX = new TextField();
    PasswordField pass_TX = new PasswordField();
    Button submit = new Button("SUBMIT");
    Button Reg = new Button("REGISTRATION");
    MainFrame frame;
    Registration registration;

    /**
     * This function define the shape of all elements on the login frame
     * AND add actions on each elements
     */
    void setLogIn(){
        name.setPrefSize(90,20);
        pass.setPrefSize(90,20);
        name_TX.setMaxWidth(120);
        pass_TX.setMaxWidth(120);
        Reg.setPrefSize(120,30);
        submit.setPrefSize(80,30);
        /*
        set action on submit button, let user log in
         */
        submit.setOnAction(e->{
            logInDB.login(name_TX.getText(),pass_TX.getText());
            if (logInDB.message.equals("True")){

                try {
                    frame = new MainFrame(logInDB.user_id);
                    frame.windows.show();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
                LogInWindow.close();
            }
            else {
                name_TX.setText("User name or password wrong");
            }
        });
        /*
        Set action on text field in case if user press enter to submit
         */
        pass_TX.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                if (!pass_TX.getText().isEmpty()){
                    logInDB.login(name_TX.getText(),pass_TX.getText());
                    if (logInDB.message.equals("True")){
                        try {
                            frame = new MainFrame(logInDB.user_id);
                            frame.windows.show();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        LogInWindow.close();
                    }
                    else {
                        name_TX.setText("User name or password wrong");
                    }
                }
            }
        });

        Reg.setOnAction(event -> {
            registration = new Registration();
            registration.regWindows.show();
        });

    }


    /**
     * This is main frame of Login, defined the size and margin
     * @return the GridPane
     */
    GridPane mainPane(){
        GridPane gridPane = new GridPane();
        /*
        SET UP each columns and rows' size
         */
        gridPane.setPrefSize(400,210);
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col1.setPrefWidth(180);
        col2.setPrefWidth(220);
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row1.setPrefHeight(70);
        row2.setPrefHeight(70);
        row3.setPrefHeight(70);
        gridPane.getColumnConstraints().addAll(col1,col2);
        gridPane.getRowConstraints().addAll(row1,row2,row3);

        setLogIn();// set up all items' function and size

        /*
        Add up all items and set margin
         */
        gridPane.add(name,0,0);
        gridPane.add(name_TX,1,0);
        gridPane.add(pass,0,1);
        gridPane.add(pass_TX,1,1);
        gridPane.add(Reg,0,2);
        gridPane.add(submit,1,2);
        GridPane.setMargin(name,new Insets(40,0,0,90));
        GridPane.setMargin(pass,new Insets(0,0,0,90));
        GridPane.setMargin(name_TX,new Insets(40,0,0,0));
        GridPane.setMargin(pass_TX,new Insets(0,0,0,0));
        GridPane.setMargin(Reg,new Insets(0,0,0,40));
        GridPane.setMargin(submit,new Insets(0,0,0,100));

        return gridPane;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(mainPane());
        LogInWindow.setScene(scene);
        LogInWindow.setResizable(false);
        LogInWindow.show();
    }

    /**
     * Launch the Frame
     * @param args the frame
     */
    public static void main(String[] args){
        launch(args);
    }

}
