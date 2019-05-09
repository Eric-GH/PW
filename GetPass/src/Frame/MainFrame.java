package Frame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame extends Application {

    private Stage windows = new Stage();// create the main windows for the program
    private VBox firstFrame = new VBox();
    private Scene scene = new Scene(firstFrame);
    private FunctionBar top = new FunctionBar();
    private ViewPane bot = new ViewPane();
    private MenuBar menu = new MenuBar();

    private void setFirstFrame(){
        firstFrame.setPrefWidth(500);
        firstFrame.setPrefHeight(600);
        firstFrame.getChildren().addAll(menu,top,bot);



    }


    private void setWindows(){
        windows.setTitle("Password Collection");
        windows.setResizable(false);
        windows.setScene(scene);
        windows.show();
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setFirstFrame();
        this.setWindows();
    }
    public static void main(String[] args){
        launch(args);
    }
}
