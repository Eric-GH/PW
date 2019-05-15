package Frame;

/**
 * Author: Hao Li
 * Date: 05/02,2019
 */

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFrame {

    public Stage windows = new Stage();// create the main windows for the program
    public VBox firstFrame = new VBox();
    public int current_user = 0;
    public FunctionBar top = new FunctionBar();
    public ViewPane bot = new ViewPane();
    public MenuBar menu = new MenuBar();
    public int mainframe_userID=0;
    public int MAXNum = 17;

    MainFrame(){
        this.setFirstFrame();
        this.setWindows();
        this.top.viewAll.setOnAction(e->{
            bot.setTop(bot.setTopShow());
            bot.setCenter(bot.setViewAllShow());
        });
        this.top.search.setOnAction(ex->{
            bot.Search_Aim = top.field.getText();
            bot.setTop(bot.setTopShow());
            bot.setCenter(bot.setSearchShow());
        });
        this.top.right.setOnAction(er->{

            //TODO
        });
        this.top.left.setOnAction(el->{
            //TODO
        });
    }
    void setID(int id){
        this.mainframe_userID = id;
        bot.view_current_id = id;
        bot.setCenter(bot.setViewAllShow());
    }
    private void setFirstFrame(){
        firstFrame.setPrefWidth(500);
        firstFrame.setPrefHeight(600);
        //System.out.println(current_user);
        menu.menu_user_id = current_user;
        //System.out.println(menu.menu_user_id);
        firstFrame.getChildren().addAll(menu,top,bot);
    }


    private void setWindows(){
        windows.setTitle("Password Collection");
        Scene scene = new Scene(firstFrame);
        windows.setResizable(false);
        windows.setScene(scene);
    }

    public void setCurrent_user(int current_user) {
        this.current_user = current_user;
    }
}
