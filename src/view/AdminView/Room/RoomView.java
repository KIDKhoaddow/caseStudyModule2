package view.AdminView.Room;

import controller.RoomController;
import view.Common.CommonView;

import java.util.Scanner;

public class RoomView {
    RoomController roomController=new RoomController();
    public RoomView(CommonView commonView, Scanner scanner){
        roomController.chooseFunction(commonView,scanner);
    }
}
