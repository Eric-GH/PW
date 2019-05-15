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
    public int mainframe_userID;
    public VBox firstFrame = new VBox();
    public FunctionBar top;
    public ViewPane bot;
    public MenuBar menu;
    public int start=0;
    public int end = 14;
    public boolean endFlag = false;
    //public ArrayList<MyPassword> MainList = new ArrayList<>();

    MainFrame(int mainframe_userID){


        this.mainframe_userID = mainframe_userID;
        this.top = new FunctionBar(mainframe_userID);
        this.bot = new ViewPane(mainframe_userID);
        this.menu = new MenuBar(mainframe_userID);
        DisableCheck();
        this.top.viewAll.setOnAction(e->{
            bot.setTop(bot.setTopShow());
            bot.setCenter(bot.setViewAllInitialShow());
            initialParameter();
        });

        this.top.search.setOnAction(ex->{
            bot.Search_Aim = top.field.getText();
            bot.setTop(bot.setTopShow());
            bot.setCenter(bot.setSearchShow());
            initialParameter();
        });

        this.top.right.setOnAction(er->{
            start = start+14;
            DisableCheck();
            if (bot.viewList.size()-start>14){
                end = end + 14;
                bot.setCenter(bot.ShowOnPage(start,end));
            }
            else { //bot.readPass.list.size()-start<17
                end = bot.viewList.size();
                endFlag = true;
                bot.setCenter(bot.ShowOnPage(start,end));
                DisableCheck();
            }

        });
        this.top.left.setOnAction(el->{
            if (!endFlag){
                start = start-14;
                end = end -14;
                bot.setCenter(bot.ShowOnPage(start,end));

            }
            else {//endFlag = true
                int temp = this.bot.viewList.size();
                end = temp-(temp-start);
                start=start -14;
                bot.setCenter(bot.ShowOnPage(start,end));
                endFlag = false;
            }
            DisableCheck();
        });
        this.setFirstFrame();
        this.setWindows();
    }

    private void setFirstFrame(){
        firstFrame.setPrefWidth(600);
        firstFrame.setPrefHeight(600);
        firstFrame.getChildren().addAll(menu,top,bot);
    }


    private void setWindows(){
        windows.setTitle("Password Collection");
        Scene scene = new Scene(firstFrame);
        windows.setResizable(false);
        windows.setScene(scene);
    }

    void DisableCheck(){
        if (start==0 || end<=14){
            this.top.left.setDisable(true);
        }
        else {
            this.top.left.setDisable(false);
        }
        if (bot.viewList.size()<=14 || endFlag){
            this.top.right.setDisable(true);
        }
        else {
            this.top.right.setDisable(false);
        }
    }

    void initialParameter(){
        start=0;
        end = 14;
        endFlag = false;
        DisableCheck();
    }
}
