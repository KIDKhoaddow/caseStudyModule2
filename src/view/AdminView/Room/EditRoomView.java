package view.AdminView.Room;

import controller.RoomController;
import view.Common.CommonView;

import java.util.Scanner;

public class EditRoomView {
    RoomController roomController=new RoomController();
    public EditRoomView(CommonView commonView, Scanner scanner){
        int roomIndex= roomController.findRoom(commonView,scanner);

    }
}
