package View;

/**
 * Author: Hao Li
 * Date: 05/19,2019
 * This is the view class
 * It is the frame work
 * build by JavaFX
 */


import Controller.CentralControl;
import Model.CentralModel;
import Model.ModelListener;
import Model.MyPassword;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CentralView implements ModelListener {
    public Stage viewStage = new Stage();// main frame to display the list of records
    public Stage LogStage = new Stage(); // the logIn frame for log in windows
    public Add addNewPass = new Add(); // Secondary frame to help user add new password record
    public Tips tips = new Tips(); // Secondary frame to give user tips
    public Register register = new Register(); // Secondary frame to help user register new account.

    public Button left = new Button("←"); // the go left page button
    public Button right = new Button("→"); // the go right page button
    public Label LogIn_message = new Label(); // the message label to give user message during log in
    public TextField username_tx = new TextField(); // the textField for user name during log in
    public PasswordField password_tx = new PasswordField(); // the textField for password during log in
    public TextField search_tx = new TextField(); // the textField for search function
    public ArrayList<MyPassword> viewListShow; // the list contained all password records for current user

    CentralControl controller; // the controller for the system
    CentralModel model; // the model for the system
    Button search = new Button("SEARCH"); // the search button
    Button all = new Button("ViewAll"); // the view all password records button
    MenuItem add = new MenuItem("ADD NEW PASSWORD");// the add new passwords button
    MenuItem deleteAll = new MenuItem("DELETE ALL PASSWORDS"); // the delete all password record button
    MenuItem quit = new MenuItem("QUIT");// the quit the system button
    BorderPane listViewPane; // the borderPane for view records part


    public CentralView(){
        viewListShow = new ArrayList<>();
        VBox view_B = new VBox();
        view_B.setId("VB");
        view_B.getChildren().addAll(Menu(),Function(),ListView());
        Scene view_scene = new Scene(view_B,600,750);
        view_scene.getStylesheets().add("CSS/View_css.css");
        viewStage.setScene(view_scene);
        viewStage.setResizable(false);
    }

    public void setController(CentralControl controller){
        this.controller = controller;
        addNewPass.setController(controller);
        tips.setController(controller);
        register.setController(controller);
        add.setOnAction(controller::OpenAddFrame);
        deleteAll.setOnAction(controller::OpenDeleteConfirmFrame);
        quit.setOnAction(controller::QuitSys);
        search.setOnAction(controller::SearchRec);
        all.setOnAction(controller::ShowAllRec);
        left.setOnAction(controller::PageLeft);
        right.setOnAction(controller::PageRight);
        password_tx.setOnKeyPressed(controller::KeyPress);

        // TODO 在这里设置actions
    }
    public void setModel(CentralModel model){
        this.model = model;
    }
    /**
     * The main pane for the menu bar
     * @return menuBox
     */
    HBox Menu(){
        //TODO action
        HBox menuBox = new HBox();
        MenuButton menuButton = new MenuButton("Menu");

        menuBox.setId("mBox");
        menuButton.setId("mbtn");

        menuBox.setPadding(new Insets(0,0,0,10));
        menuButton.getItems().addAll(add,deleteAll,quit);
        menuBox.getChildren().addAll(menuButton);
        return menuBox;
    }

    /**
     * The main pane for function button and search field
     * @return funcPane
     */
    BorderPane Function(){
        BorderPane funcPane = new BorderPane();
        HBox func_top = new HBox();
        HBox func_bot = new HBox();
        //TextField search_tx = new TextField();

        funcPane.setId("func");
        func_top.setId("f_top");
        func_bot.setId("f_bot");
        search.setId("search");
        all.setId("viewALl");
        left.setId("page");
        right.setId("page");
        search_tx.setId("s_field");

        func_top.setPadding(new Insets(17,0,0,120));
        HBox.setMargin(search,new Insets(0,0,0,-20));
        HBox.setMargin(search_tx,new Insets(0,-10,0,0));
        HBox.setMargin(all,new Insets(0,0,0,10));
        HBox.setMargin(left,new Insets(2,0,0,10));
        HBox.setMargin(right,new Insets(2,0,0,500));
        func_top.getChildren().addAll(search_tx,search,all);
        func_bot.getChildren().addAll(left,right);
        funcPane.setCenter(func_top);
        funcPane.setBottom(func_bot);
        return funcPane;
    }

    /**
     * The main pane for display the list of passwords
     * @return listViewPane
     */
    BorderPane ListView(){
        listViewPane = new BorderPane();
        listViewPane.setId("LVP");

        listViewPane.setTop(titelLine());
        listViewPane.setCenter(Display());
        return listViewPane;
    }

    HBox titelLine(){
         /*
        Create elements
         */
        HBox titleLine = new HBox();
        Label listName = new Label("NAME");
        Label listUser = new Label("USERNAME");
        Label listPass = new Label("PASSWORD");
        titleLine.setId("title");
        listName.setId("title_element");
        listUser.setId("title_element");
        listPass.setId("title_element");
        /*
        set position && margin
         */
        HBox.setMargin(listPass,new Insets(0,0,0,80));
        HBox.setMargin(listName,new Insets(0,0,0,40));
        HBox.setMargin(listUser,new Insets(0,0,0,80));

        /*
        ADD up all
         */
        titleLine.getChildren().addAll(listName,listUser,listPass);

        return titleLine;
    }

    public VBox Display(){
        VBox displayBox = new VBox();
        displayBox.setId("displayBox");
        LinePane line;
        for (int i=0;i<viewListShow.size();i++){
            line = new LinePane();
            final int m = i;
            line.setName_area(viewListShow.get(i).getAddress());
            line.setUser_area(viewListShow.get(i).getNames());
            line.setPass_area(viewListShow.get(i).getPassword());
            line.view_delete.setOnAction(event -> controller.SingleDelete(viewListShow.get(m).getId()));
            VBox.setMargin(line,new Insets(5,0,0,0));
            displayBox.getChildren().add(line);
        }
        return displayBox;
    }


    /**
     * This is the logIn frame
     * @return LogInBox
     */
    public VBox LogIn(){
        VBox LogInBox = new VBox();
        HBox userLine = new HBox();
        HBox passLine = new HBox();
        HBox btnLine = new HBox();
        LogInBox.setId("log_main");
        userLine.setId("u_pHBox");
        passLine.setId("u_pHBox");
        btnLine.setId("u_pHBox");
        Label wel = new Label("WELCOME");
        Label user_name = new Label("USER NAME:");
        Label pass_word = new Label("PASSWORD:");
        wel.setId("welcome");
        user_name.setId("u_pLabel");
        pass_word.setId("u_pLabel");
        username_tx.setId("u_pText");
        password_tx.setId("u_pText");
        LogIn_message.setId("lgMessage");
        Button reg = new Button("REGISTRATION");
        Button con = new Button("SUBMIT");
        reg.setId("log_btn");
        con.setId("log_btn");

        reg.setOnAction(controller::OpenReg);
        //TODO 测试用
        //reg.setOnAction(controller::test);
        con.setOnAction(controller::LogIn_submit);

        user_name.setPadding(new Insets(0,0,0,15));
        pass_word.setPadding(new Insets(0,0,0,15));
        HBox.setMargin(user_name,new Insets(0,0,0,55));
        HBox.setMargin(pass_word,new Insets(0,0,0,55));
        HBox.setMargin(reg,new Insets(0,0,10,25));
        HBox.setMargin(con,new Insets(0,0,10,70));

        userLine.getChildren().addAll(user_name,username_tx);
        passLine.getChildren().addAll(pass_word,password_tx);
        btnLine.getChildren().addAll(reg,con);
        LogInBox.getChildren().addAll(wel,userLine,passLine,LogIn_message,btnLine);

        return LogInBox;
    }


    public void modelChanged() {
        listViewPane.setCenter(Display());
    }

}
