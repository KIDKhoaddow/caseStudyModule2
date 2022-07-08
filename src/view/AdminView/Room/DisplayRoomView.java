package view.AdminView.Room;

import controller.RoomController;
import view.Common.CommonView;

import java.util.Scanner;

public class DisplayRoomView {
    RoomController roomController=new RoomController();
    DisplayRoomView(CommonView commonView, Scanner scanner){
            roomController.displayRoomView(commonView);
    }
}
