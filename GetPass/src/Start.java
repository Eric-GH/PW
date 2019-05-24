/**
 * Author: Hao Li
 * Date: 05/02,2019
 * This is the main class(start class) for the program
 * JavaFX
 */
import Controller.CentralControl;
import Model.CentralModel;
import View.CentralView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    /**
     * The start function
     * @param LoginStage null
     * @throws Exception all
     */
    @Override
    public void start(Stage LoginStage) throws Exception {
        /*
        initialize the model, view and controller
         */
        CentralControl controller = new CentralControl();
        CentralModel model = new CentralModel();
        CentralView view = new CentralView();

        /*
        Set up the model, view and controller to each other
         */
        view.setController(controller);
        view.setModel(model);
        controller.setView(view);
        controller.setModel(model);
        model.addSubscriber(view);

        /*
            Start the Stage
         */
        Scene scene = new Scene(view.LogIn(),400,300);
        scene.getStylesheets().add("CSS/LogIn.css");
        view.LogStage.setScene(scene);
        view.LogStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void mian(String[] args){
        launch(args);
    }
}
