import Controller.CentralControl;
import Model.CentralModel;
import View.CentralView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
    CentralControl controller;
    @Override
    public void start(Stage LoginStage) throws Exception {
        controller = new CentralControl();
        CentralModel model = new CentralModel();
        CentralView view = new CentralView();

        view.setController(controller);
        view.setModel(model);
        controller.setView(view);
        controller.setModel(model);
        model.addSubscriber(view);


        Scene scene = new Scene(view.LogIn(),400,300);
        scene.getStylesheets().add("./CSS/LogIn.css");
        view.LogStage.setScene(scene);
        view.LogStage.show();
    }

    /**
     * Main function to run the project
     * @param args //
     */
    public static void mian(String[] args){
        launch(args);
    }
}
