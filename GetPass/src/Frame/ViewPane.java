package Frame;

/**
 * Author: Hao Li
 * Date: 05/03,2019
 */

import Databass.ReadPass;
import Frame.SecondaryFrames.LineView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewPane extends BorderPane {

    public int view_current_id=0;
    public String Search_Aim = null;
    ViewPane(){
        this.setPrefHeight(475);
        this.setPrefWidth(500);
        this.setTop(setTopShow());
        this.setCenter(setViewAllShow());
        this.setStyle("-fx-background-color: #ff8c00;");
    }

    /**
     * Create the title of the list of the passwords
     * @return HBox
     */
    HBox setTopShow(){
        /*
        Create elements
         */
        HBox topBox = new HBox();
        Label listName = new Label("NAME");
        Label listUser = new Label("USERNAME");
        Label listPass = new Label("PASSWORD");
        /*
        Set up size of elements
         */
        topBox.setPrefSize(500,30);
        listName.setPrefSize(80,25);
        listUser.setPrefSize(80,25);
        listPass.setPrefSize(80,25);
        /*
        set position && margin
         */
        listName.setAlignment(Pos.CENTER);
        listUser.setAlignment(Pos.CENTER);
        listPass.setAlignment(Pos.CENTER);
        HBox.setMargin(listName,new Insets(0,0,0,40));
        HBox.setMargin(listUser,new Insets(0,0,0,80));
        HBox.setMargin(listPass,new Insets(0,0,0,80));

        /*
        ADD up all
         */
        topBox.getChildren().addAll(listName,listUser,listPass);
        return topBox;
    }


    public VBox setViewAllShow(){
        VBox viewVBox = new VBox();
        LineView view;
        ReadPass readPass = new ReadPass();
        viewVBox.setPrefSize(500,445);
        readPass.ReadAll(view_current_id);
        if (readPass.list.size()<=17){
            for (int i=0;i<readPass.list.size();i++) {
                view = new LineView();
                view.setName_area(readPass.list.get(i).getAddress());
                view.setUser_area(readPass.list.get(i).getNames());
                view.setPass_area(readPass.list.get(i).getPassword());
                viewVBox.getChildren().add(view);
            }
        }
        else {
            for (int i=0;i<17;i++) {
                view = new LineView();
                view.setName_area(readPass.list.get(i).getAddress());
                view.setUser_area(readPass.list.get(i).getNames());
                view.setPass_area(readPass.list.get(i).getPassword());
                viewVBox.getChildren().add(view);
            }
        }

        return viewVBox;
    }

    public VBox setSearchShow(){
        VBox searchBox = new VBox();
        searchBox.setPrefSize(500,445);
        LineView view;
        ReadPass readPass = new ReadPass();
        readPass.SearchRead(view_current_id,Search_Aim);
        for (int s=0;s<readPass.searchList.size();s++){
            view = new LineView();
            view.setName_area(readPass.searchList.get(s).getAddress());
            view.setUser_area(readPass.searchList.get(s).getNames());
            view.setPass_area(readPass.searchList.get(s).getPassword());
            searchBox.getChildren().add(view);
        }
        return searchBox;
    }

}
