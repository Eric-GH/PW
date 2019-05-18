import Controller.CentralControl;
import Model.Database;
import View.CentralView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Start extends Application {
    VBox loginPane;
    Label welcomeLogo = new Label("WELCOME");
    Label loginMessage = new Label();
    TextField user_tx = new TextField();
    PasswordField pass_tx = new PasswordField();
    CentralControl controller = new CentralControl();
    Database model = new Database();
    CentralView view = new CentralView();

    @Override
    public void start(Stage LoginStage) throws Exception {
        CentralView view = new CentralView();
        Database model = new Database();

        view.setController(controller);
        view.setModel(model);
        controller.setView(view);
        controller.setModel(model);
        model.addSubscriber(view);

        controller = new CentralControl();
        loginPane = new VBox();
        loginPane.setPrefSize(400,300);
        welcomeLogo.setPrefSize(400,100);
        loginMessage.setPrefSize(400,40);
        welcomeLogo.setAlignment(Pos.CENTER);
        loginMessage.setAlignment(Pos.CENTER);
        loginPane.getChildren().addAll(welcomeLogo,UserLine(), PassLine(),loginMessage,ButtonLine());
        Scene scene = new Scene(loginPane,400,300);
        scene.getStylesheets().add("./CSS/LogIn.css");
        LoginStage.setScene(scene);
        LoginStage.show();
    }

    /**
     * Set Up user name line
     * @return HBox container
     */
    HBox UserLine(){
        HBox usrL = new HBox();
        Label user_name = new Label("USER NAME:");
        user_name.setPrefSize(80,30);
        user_tx.setPrefSize(150,30);
        user_tx.setMaxSize(150,30);
        usrL.setPrefSize(400,50);
        HBox.setMargin(user_name,new Insets(0,0,0,70));
        HBox.setMargin(user_tx,new Insets(0,0,0,10));
        usrL.getChildren().addAll(user_name,user_tx);
        return usrL;
    }

    /**
     * Set Up password line
     * @return HBox container
     */
    HBox PassLine(){
        HBox passwordL = new HBox();
        Label pass_word = new Label("PASSWORD:");
        pass_word.setPrefSize(80,30);
        pass_tx.setPrefSize(150,30);
        pass_tx.setMaxSize(150,30);
        passwordL.setPrefSize(400,40);
        HBox.setMargin(pass_word,new Insets(0,0,0,70));
        HBox.setMargin(pass_tx,new Insets(0,0,0,10));
        passwordL.getChildren().addAll(pass_word,pass_tx);
        return passwordL;
    }


    /**
     * Set Up button line
     * @return HBox container
     */
    HBox ButtonLine(){
        HBox btnL = new HBox();
        Button reg = new Button("REGISTRATION");
        reg.setId("reg_btn");
        Button con = new Button("SUBMIT");


        con.setOnAction(e->{
            CentralView view = new CentralView();
            view.viewStage.show();
        });


        reg.setPrefSize(120,30);
        con.setPrefSize(80,30);
        btnL.setPrefSize(400,50);
        HBox.setMargin(reg,new Insets(0,0,0,40));
        HBox.setMargin(con,new Insets(0,0,0,120));
        con.setOnAction(controller::LogIn_submit);
        reg.setOnAction(controller::LogIn_reg);
        btnL.getChildren().addAll(reg,con);
        return btnL;
    }



    /**
     * Main function to run the project
     * @param args //
     */
    public static void mian(String[] args){
        launch(args);
    }
}
