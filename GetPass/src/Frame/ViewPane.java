package Frame;

/**
 * Author: Hao Li
 * Date: 05/03,2019
 */

import Databass.DeletePass;
import Databass.ReadPass;
import Frame.SecondaryFrames.LineView;
import Frame.SecondaryFrames.Tip;
import Source.MyPassword;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ViewPane extends BorderPane {

    public int view_current_id;
    public String Search_Aim = null;
    int initialNum = 14;
    public ReadPass readPass;
    LineView view;
    public ArrayList<MyPassword> viewList;
    ViewPane(int view_current_id){
        this.view_current_id = view_current_id;
        this.setPrefHeight(475);
        this.setPrefWidth(500);
        this.setTop(setTopShow());
        this.setCenter(setViewAllInitialShow());
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
        Label lisName = new Label("NAME");
        Label lisUser = new Label("USERNAME");
        Label lisPass = new Label("PASSWORD");
        /*
        Set up size of elements
         */
        topBox.setPrefSize(500,30);
        lisName.setPrefSize(80,25);
        lisUser.setPrefSize(80,25);
        lisPass.setPrefSize(80,25);
        /*
        set position && margin
         */
        lisName.setAlignment(Pos.CENTER);
        lisUser.setAlignment(Pos.CENTER);
        lisPass.setAlignment(Pos.CENTER);
        HBox.setMargin(lisName,new Insets(0,0,0,40));
        HBox.setMargin(lisUser,new Insets(0,0,0,80));
        HBox.setMargin(lisPass,new Insets(0,0,0,80));

        /*
        ADD up all
         */
        topBox.getChildren().addAll(lisName,lisUser,lisPass);
        return topBox;
    }


    public VBox setViewAllInitialShow(){
        VBox viewVBox = new VBox();
        readPass = new ReadPass();
        viewVBox.setPrefSize(600,445);
        readPass.ReadAll(view_current_id);
        viewList = new ArrayList<>();
        viewList.addAll(readPass.list);
        if (viewList.size()<=initialNum){
            initialNum = readPass.list.size();
        }
        for (int i=0;i<initialNum;i++) {
            final int fine = i;
            view = new LineView();
            view.setName_area(viewList.get(i).getAddress());
            view.setUser_area(viewList.get(i).getNames());
            view.setPass_area(viewList.get(i).getPassword());
            view.view_delete.setOnAction(e->{
                DeletePass deletePass = new DeletePass();
                deletePass.Delete(viewList.get(fine).getId());
                Tip t = new Tip();
                t.tipMessage.setText("Delete Successfully!");
                t.tipsWindows.show();
            });
            viewVBox.getChildren().add(view);
        }

        return viewVBox;
    }

    public VBox setSearchShow(){
        VBox searchBox = new VBox();
        searchBox.setPrefSize(600,445);
        readPass = new ReadPass();
        readPass.SearchRead(view_current_id,Search_Aim);
        viewList = new ArrayList<>();
        viewList.addAll(readPass.searchList);
        if (viewList.size()<=initialNum){
            for (int s=0;s<viewList.size();s++){
                final int fine = s;
                view = new LineView();
                view.setName_area(viewList.get(s).getAddress());
                view.setUser_area(viewList.get(s).getNames());
                view.setPass_area(viewList.get(s).getPassword());
                view.view_delete.setOnAction(e->{
                    DeletePass deletePass = new DeletePass();
                    deletePass.Delete(viewList.get(fine).getId());
                    Tip t = new Tip();
                    t.tipMessage.setText("Delete Successfully!");
                    t.tipsWindows.show();

                });
                searchBox.getChildren().add(view);
            }
        }
        else {
            for (int s=0;s<initialNum;s++){
                final int fine = s;
                view = new LineView();
                view.setName_area(viewList.get(s).getAddress());
                view.setUser_area(viewList.get(s).getNames());
                view.setPass_area(viewList.get(s).getPassword());
                view.view_delete.setOnAction(e->{
                    DeletePass deletePass = new DeletePass();
                    deletePass.Delete(viewList.get(fine).getId());
                    Tip t = new Tip();
                    t.tipMessage.setText("Delete Successfully!");
                    t.tipsWindows.show();

                });
                searchBox.getChildren().add(view);
            }
        }

        return searchBox;
    }

    public VBox ShowOnPage(int start, int end){
        VBox pageVBox = new VBox();
        pageVBox.setPrefSize(600,445);
        //readPass = new ReadPass();
        //readPass.ReadAll(view_current_id);
        for (int i = start;i<end;i++){
            final int fine = i;
            view = new LineView();
            view.setName_area(viewList.get(i).getAddress());
            view.setUser_area(viewList.get(i).getNames());
            view.setPass_area(viewList.get(i).getPassword());
            view.view_delete.setOnAction(e->{
                DeletePass deletePass = new DeletePass();
                deletePass.Delete(viewList.get(fine).getId());
                Tip t = new Tip();
                t.tipMessage.setText("Delete Successfully!");
                t.tipsWindows.show();
            });
            pageVBox.getChildren().add(view);
        }
        return pageVBox;
    }
}
