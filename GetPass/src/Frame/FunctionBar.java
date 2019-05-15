package Frame;

/**
 * Author: Hao Li
 * Date: 05/03,2019
 */

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FunctionBar extends BorderPane {
    //Button search = new Button();
    //Button viewAll = new Button();
    Button left = new Button("←");
    Button right = new Button("→");
    public TextField field;
    public Button viewAll;
    public int functionBar_current_userID;
    public Button search;
    FunctionBar(int functionBar_current_userID){
        this.functionBar_current_userID = functionBar_current_userID;
        this.setPrefHeight(100);
        this.setPrefWidth(500);
        this.setCenter(topH());
        this.setBottom(setH());
    }

    HBox topH(){
        HBox topH = new HBox();
        topH.setPrefHeight(70);
        topH.setPrefWidth(500);
        topH.setPadding(new Insets(20,0,0,75));
        topH.getChildren().addAll(SearchField(),SearchButton(),ViewAllButton());
        topH.setStyle("-fx-background-color: #00ffff;");
        return topH;
    }

    TextField SearchField(){
        field= new TextField();
        field.setPrefHeight(35);
        field.setPrefWidth(200);
        return field;
    }

    Button SearchButton(){
        search = new Button("Search");
        search.setPrefHeight(35);
        search.setPrefWidth(80);
        HBox.setMargin(search, new Insets(0,0,0,10));
        return search;
    }

    Button ViewAllButton(){
        viewAll = new Button("VIEW ALL");
        viewAll.setPrefHeight(35);
        viewAll.setPrefWidth(80);
        HBox.setMargin(viewAll,new Insets(0,0,0,10));
        return viewAll;
    }

    HBox setH(){
        HBox LR = new HBox();
        LR.setPrefSize(500,30);
        left.setPrefSize(40,25);
        right.setPrefSize(40,25);
        HBox.setMargin(left,new Insets(2,0,0,10));
        HBox.setMargin(right, new Insets(2,0,0,400));
        LR.getChildren().addAll(left,right);
        return LR;
    }
}
