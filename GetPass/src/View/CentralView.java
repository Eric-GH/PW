package View;

import Controller.CentralControl;
import Model.Database;
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
    public Stage viewStage = new Stage();
    public Stage LogStage = new Stage();
    public Add addNewPass = new Add();
    public Tips tips = new Tips();

    Button search = new Button("SEARCH");
    Button all = new Button("ViewAll");
    public Button left = new Button("←");
    public Button right = new Button("→");
    MenuItem add = new MenuItem("ADD NEW PASSWORD");
    MenuItem deleteAll = new MenuItem("DELETE ALL PASSWORDS");
    MenuItem quit = new MenuItem("QUIT");


    BorderPane listViewPane;
    CentralControl controller;
    Database model;
    public Label LogIn_message = new Label();
    public TextField username_tx = new TextField();
    public PasswordField password_tx = new PasswordField();
    public TextField search_tx = new TextField();
    public ArrayList<MyPassword> viewListShow;
    public CentralView(){
        viewListShow = new ArrayList<>();
        VBox view_B = new VBox();
        view_B.setPrefSize(600,600);
        view_B.getChildren().addAll(Menu(),Function(),ListView());
        Scene view_scene = new Scene(view_B,600,600);
        view_scene.getStylesheets().add("CSS/View_css.css");
        viewStage.setScene(view_scene);
        viewStage.setResizable(false);
    }

    public void setController(CentralControl controller){
        this.controller = controller;
        addNewPass.setController(controller);
        tips.setController(controller);
        add.setOnAction(controller::OpenAddFrame);
        deleteAll.setOnAction(controller::OpenDeleteConfirmFrame);
        quit.setOnAction(controller::QuitSys);
        search.setOnAction(controller::SearchRec);
        all.setOnAction(controller::ShowAllRec);
        left.setOnAction(controller::PageLeft);
        right.setOnAction(controller::PageRight);

        // TODO 在这里设置actions
    }
    public void setModel(Database model){
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
        all.setId("search");
        left.setId("page");
        right.setId("page");
        search_tx.setId("s_field");

        func_top.setPadding(new Insets(0,0,0,120));
        HBox.setMargin(search,new Insets(0,0,0,10));
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

        reg.setOnAction(controller::LogIn_reg);
        con.setOnAction(controller::LogIn_submit);


        HBox.setMargin(user_name,new Insets(0,0,0,70));
        HBox.setMargin(pass_word,new Insets(0,0,0,70));
        HBox.setMargin(username_tx,new Insets(0,0,0,10));
        HBox.setMargin(password_tx,new Insets(0,0,0,10));
        HBox.setMargin(reg,new Insets(0,0,0,40));
        HBox.setMargin(con,new Insets(0,0,0,80));

        userLine.getChildren().addAll(user_name,username_tx);
        passLine.getChildren().addAll(pass_word,password_tx);
        btnLine.getChildren().addAll(reg,con);
        LogInBox.getChildren().addAll(wel,userLine,passLine,LogIn_message,btnLine);

        return LogInBox;
    }











    public void modelChanged() {
        //TODO 每次数据库改变，在这里重画中心的密码列表
        listViewPane.setCenter(Display());
    }

}
