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
    CentralControl controller;
    @Override
    public void start(Stage LoginStage) throws Exception {
        controller = new CentralControl();
        Database model = new Database();
        CentralView view = new CentralView();

        view.setController(controller);
        view.setModel(model);
        controller.setView(view);
        controller.setModel(model);
        model.addSubscriber(view);


        Scene scene = new Scene(view.LogIn(),400,300);
        scene.getStylesheets().add("./CSS/LogIn.css");
        LoginStage.setScene(scene);
        LoginStage.show();
    }

    /**
     * Main function to run the project
     * @param args //
     */
    public static void mian(String[] args){
        launch(args);
    }
}
