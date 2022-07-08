package service.Room;

import model.Class.EnglishClass;
import model.Room.Room;
import model.Room.RoomStatus;
import service.IGenericService;

public interface IRoomService extends IGenericService<Room> {
    void saveList();
    boolean exitedRoomById(String id);
    int findRoomById(String id);
    String getRoomName(int index);
    int getRoomNumberSeat(int index);
    RoomStatus getRoomStatus(int index);
    EnglishClass getFirstShiftClass(int index);
    EnglishClass getSecondShiftClass(int index);
    String getFirstShiftClassName(int index);
    String getSecondShiftClassName(int index);

    Room editRoomName(int index , String roomName);
    Room editRoomNumberSeat(int index,int numberSeat);
    Room editRoomStatus(int index,RoomStatus roomStatus);
    Room editFirstShiftClass(int index,EnglishClass englishClass);
    Room editSecondShiftClass(int index,EnglishClass englishClass);


}
