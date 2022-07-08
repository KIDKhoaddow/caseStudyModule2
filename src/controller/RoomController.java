package controller;

import model.Room.Room;
import service.Room.RoomServiceIMPL;
import view.AdminView.Room.AddRoomView;
import view.Common.CommonView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RoomController {
    RoomServiceIMPL roomServiceIMPL = new RoomServiceIMPL();

    public void chooseFunction(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ ROOM FORM ============");
        commonView.showMessage("1.Add ROOM");
        commonView.showMessage("2.Display List ROOM");
        commonView.showMessage("3.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                selection = commonView.inputString("Wrong number try again please ", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                new AddRoomView(commonView, scanner);
                break;
            case 2:
                displayRoomView(commonView);
                break;
            case 3:
                return;
        }
        chooseFunction(commonView, scanner);

    }

    public String addRoomName(CommonView commonView, Scanner scanner) {
        commonView.showMessage("R+Floor+Index");
        String roomName = commonView.inputString("Enter Room name please :", scanner);
        while (true) {
            if (!checkRoomName(roomName)) {
                roomName = commonView.inputString("Wrong Room Name , try again please :", scanner);
            } else {
                break;
            }
        }
        return roomName;
    }

    public boolean checkRoomName(String roomName) {
        return Pattern.matches("^(R)(\\d){3}$", roomName);
    }

    public int addRoomNumberSeat(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Number seat need greater or equal than 10 ");
        String roomNumberSeat = commonView.inputString("Enter Room Number Seat :", scanner);
        while (true) {
            if (!checkRoomNumberSeat(roomNumberSeat)) {
                roomNumberSeat = commonView.inputString("Wrong Room Number Seat ,try again please :", scanner);
            } else {
                break;
            }
        }
        return Integer.parseInt(roomNumberSeat);
    }

    public void saveNewRoom(CommonView commonView, Scanner scanner, String roomName, int roomNumberSeat) {
        Room room = new Room(roomName, roomNumberSeat);
        roomServiceIMPL.save(room);
        commonView.displayObject(room);
        commonView.showMessage("Add Room complete ! ");
    }

    public void displayRoomView(CommonView commonView) {
        for (Room element: roomServiceIMPL.findAll()) {
            commonView.displayObject(element.toString());
        }
    }

    public boolean checkRoomNumberSeat(String roomNumberSeat) {
        return Pattern.matches("^[1-9][\\d]$", roomNumberSeat);
    }

    private boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);

        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }

    public int findRoom(CommonView commonView, Scanner scanner) {
        ArrayList<Room> roomList = (ArrayList<Room>) roomServiceIMPL.findAll();
        for (Room element : roomList) {
            commonView.displayObject(element);
        }
        String roomId = commonView.inputString("Please enter the Room Id :", scanner);
        while (true) {
            if (!checkRoomId(roomId)) {
                roomId = commonView.inputString("Wrong the Room Id , try again please", scanner);
            } else if (!checkExistedRoom(roomId)) {
                roomId = commonView.inputString("Room has not existed , try again please: ", scanner);
            } else {
                break;
            }
        }
        return roomServiceIMPL.findRoomById(roomId);


    }

    public boolean checkRoomId(String roomId) {
        return Pattern.matches("^(Room)(\\d)+$", roomId);
    }

    public boolean checkExistedRoom(String roomId) {
        return roomServiceIMPL.exitedRoomById(roomId);
    }


}
