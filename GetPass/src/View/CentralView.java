package View;

/**
 * Author: Hao Li
 * Date: 05/19,2019
 * This is the main view frame class
 * JavaFX
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
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.ArrayList;

public class CentralView implements ModelListener {
    public Stage viewStage = new Stage();// main frame to display the list of records
    public Stage LogStage = new Stage(); // the logIn frame for log in windows
    public Add addNewPass = new Add(); // Secondary frame to help user all new password record
    public Tips tips = new Tips(); // Secondary frame to give user tips
    public Register register = new Register(); // Secondary frame to help user register new account.

    public Button left = new Button(); // the go left page button
    public Button right = new Button(); // the go right page button
    public Label LogIn_message = new Label(); // the message label to give user message during log in
    public TextField username_tx = new TextField(); // the textField for user name during log in
    public PasswordField password_tx = new PasswordField(); // the textField for password during log in
    public TextField search_tx = new TextField(); // the textField for search function
    public ArrayList<MyPassword> viewListShow; // the list contained addnew password records for current user

    CentralControl controller; // the controller for the system
    CentralModel model; // the model for the system
    Button search = new Button("SEARCH"); // the search button
    Button addnew = new Button("ADD"); // the view addnew password records button
    MenuItem all = new MenuItem("View All Passwords");// the all new passwords button
    MenuItem deleteAll = new MenuItem("DELETE ALL PASSWORDS"); // the delete addnew password record button
    MenuItem quit = new MenuItem("QUIT");// the quit the system button
    BorderPane listViewPane; // the borderPane for view records part


    /**
     * Construct to build the Central View
     */
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

    /**
     * Set up the controller to each secondary frame and the action of current frame
     * @param controller Central Frame
     */
    public void setController(CentralControl controller){
        this.controller = controller;
        addNewPass.setController(controller);
        tips.setController(controller);
        register.setController(controller);
        all.setOnAction(controller::ShowAllRec);
        deleteAll.setOnAction(controller::OpenDeleteConfirmFrame);
        quit.setOnAction(controller::QuitSys);
        search.setOnAction(controller::SearchRec);
        addnew.setOnAction(controller::OpenAddFrame);
        left.setOnAction(controller::PageLeft);
        right.setOnAction(controller::PageRight);
        password_tx.setOnKeyPressed(controller::KeyPress);
        search_tx.setOnKeyPressed(controller::SearchKeyPress);
        username_tx.setOnKeyPressed(controller::KeyPress);
    }

    /**
     * Set up the model for the frame
     * @param model Central Model
     */
    public void setModel(CentralModel model){
        this.model = model;
    }


    /**
     * The main pane for the menu bar
     * @return menuBox
     */
    HBox Menu(){
        HBox menuBox = new HBox();
        MenuBar menuBar = new MenuBar();
        MenuBar viewBar = new MenuBar();
        Menu setview = new Menu("View");
        Menu menu = new Menu("Menu");
        menuBar.getMenus().add(menu);
        viewBar.getMenus().add(setview);
        menu.getItems().addAll(deleteAll,quit);
        setview.getItems().add(all);
        menuBar.setPadding(new Insets(0,-5,0,0));
        viewBar.setPadding(new Insets(0,0,0,1));
        HBox.setMargin(menuBar,new Insets(0,0,0,10));
        HBox.setMargin(viewBar,new Insets(0,0,0,465));
        menuBox.getChildren().addAll(menuBar,viewBar);
        return menuBox;
    }

    /**
     * The main pane for function button and search field
     * @return funcPane
     */
    BorderPane Function(){
        BorderPane funcPane = new BorderPane();
        BorderPane func_top = new BorderPane();
        HBox Htop = new HBox();
        HBox func_bot = new HBox();


        /*
         Create triangle shapes for the buttons
         */
        Double []d1 = new Double[]{0.0, 0.0, 0.0, 3.0, 0.5, 1.5};
        Double []d2 = new Double[]{0.0, 0.0, 0.0, -3.0, -0.5, -1.5};
        Polygon triangle1 = new Polygon();
        Polygon triangle2 = new Polygon();
        triangle1.getPoints().addAll(d1);
        triangle2.getPoints().addAll(d2);
        right.setShape(triangle1);
        left.setShape(triangle2);


        /*
        Set up the id for the elements
         */
        funcPane.setId("func");
        func_top.setId("f_top");
        Htop.setId("Htop");
        func_bot.setId("f_bot");
        search.setId("search");
        addnew.setId("viewALl");
        left.setId("page");
        right.setId("page");
        search_tx.setId("s_field");


        /*
        Set up the margin
         */
        func_top.setPadding(new Insets(15,0,0,0));
        BorderPane.setMargin(addnew,new Insets(0,-75,0,110));
        BorderPane.setMargin(Htop,new Insets(0,0,0,50));
        HBox.setMargin(search_tx,new Insets(0,0,0,0));
        HBox.setMargin(search,new Insets(0,0,0,-30));
        HBox.setMargin(left,new Insets(0,0,0,10));
        HBox.setMargin(right,new Insets(0,0,0,500));
        search_tx.setPadding(new Insets(0,35,0,35));


        /*
        Add up each elements
         */
        Htop.getChildren().addAll(search_tx,search);
        func_top.setCenter(Htop);
        func_top.setLeft(addnew);
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


    /**
     * The title line of display the passwors records
     * @return titleLine
     */
    VBox titelLine(){
         /*
        Create elements
         */
        VBox titleLine = new VBox();
        Separator titleSep = new Separator();
        HBox titleHBox = new HBox();
        Label listName = new Label("NAME");
        Label listUser = new Label("USERNAME");
        Label listPass = new Label("PASSWORD");
        titleLine.setId("title");
        titleHBox.setId("titleHBox");
        listName.setId("title_element");
        listUser.setId("title_element");
        listPass.setId("title_element");
        titleSep.setId("titleSeparator");
        /*
        set position && margin
         */
        HBox.setMargin(listName,new Insets(5,0,0,50));
        HBox.setMargin(listUser,new Insets(5,0,0,45));
        HBox.setMargin(listPass,new Insets(5,0,0,45));
        VBox.setMargin(titleSep,new Insets(0,0,0,33));

        /*
        ADD up addnew
         */
        titleHBox.getChildren().addAll(listName,listUser,listPass);
        titleLine.getChildren().addAll(titleHBox,titleSep);
        return titleLine;
    }

    /**
     * The main display layout
     * @return displayBox
     */
    private VBox Display(){
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
        Separator botSep = new Separator();
        botSep.setId("titleSeparator");
        VBox.setMargin(botSep,new Insets(-2,0,0,33));
        displayBox.getChildren().add(botSep);
        return displayBox;
    }


    /**
     * The logIn frame
     * @return LogInBox
     */
    public VBox LogIn(){
        VBox LogInBox = new VBox();
        HBox userLine = new HBox();
        HBox passLine = new HBox();
        HBox btnLine = new HBox();

        /*
        Set up the id for each elements
         */
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

        /*
        Set up the actions for login buttons
         */
        reg.setOnAction(controller::OpenReg);
        con.setOnAction(controller::LogIn_submit);

        /*
        Set up the margin
         */
        user_name.setPadding(new Insets(0,0,0,15));
        pass_word.setPadding(new Insets(0,0,0,15));
        HBox.setMargin(user_name,new Insets(0,0,0,55));
        HBox.setMargin(pass_word,new Insets(0,0,0,55));
        HBox.setMargin(reg,new Insets(0,0,10,25));
        HBox.setMargin(con,new Insets(0,0,10,70));

        /*
        Add up addnew elements
         */
        userLine.getChildren().addAll(user_name,username_tx);
        passLine.getChildren().addAll(pass_word,password_tx);
        btnLine.getChildren().addAll(reg,con);
        LogInBox.getChildren().addAll(wel,userLine,passLine,LogIn_message,btnLine);

        return LogInBox;
    }


    /**
     * The listener to tell the view there is change in model.
     */
    public void modelChanged() {
        listViewPane.setCenter(Display());
    }

}
