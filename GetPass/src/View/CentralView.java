package View;

import Controller.CentralControl;
import Model.ModelListener;
import javafx.scene.layout.HBox;

public class CentralView implements ModelListener {

    CentralControl controller;
    public void SetController(CentralControl controller){
        this.controller = controller;

    }

    /**
     * The main pane for the menu bar
     * @return
     */
    HBox Menu(){
        HBox menuBox = new HBox();
        //TODO
        return menuBox;
    }





















    public void modelChanged() {
        //TODO 每次数据库改变，在这里重画中心的密码列表
    }

}
