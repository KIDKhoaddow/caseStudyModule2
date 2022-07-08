package view.AdminView.Room;

import controller.RoomController;
import view.Common.CommonView;

import java.util.Scanner;

public class AddRoomView {
    RoomController roomController=new RoomController();
    public AddRoomView(CommonView commonView, Scanner scanner){
        String roomName=roomController.addRoomName(commonView,scanner);
        int roomNumberSeat= roomController.addRoomNumberSeat(commonView,scanner);
        roomController.saveNewRoom(commonView,scanner,roomName,roomNumberSeat);

    }
}
