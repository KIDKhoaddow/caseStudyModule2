package service.Room;

import config.ConfigReadAndWriteFile;
import model.Class.EnglishClass;
import model.Class.Shift;
import model.Room.Room;
import model.Room.RoomStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceIMPL implements IRoomService {
    public static String PATH_ROOM = ConfigReadAndWriteFile.PATH + "room.txt";
    public static List<Room> roomList = new ConfigReadAndWriteFile<Room>().readFromFile(PATH_ROOM);

    @Override
    public void save(Room room) {
        roomList.add(room);
        saveList();
    }

    @Override
    public void saveList() {
        new ConfigReadAndWriteFile<Room>().writeToFile(PATH_ROOM, roomList);
    }

    @Override
    public boolean exitedRoomById(String id) {
       try  {
           for (Room element:roomList) {
               if(element.getRoomId().equals(id)){
                   return true;
               }
           }
       }
       catch (Exception e){
           return false;
       }
       return false;
    }

    @Override
    public void edit(int index, Room room) {
        roomList.set(index, room);
        saveList();
    }

    @Override
    public Room editRoomName(int index, String roomName) {
        roomList.get(index).setRoomName(roomName);
        saveList();
        return roomList.get(index);
    }

    @Override
    public Room editRoomNumberSeat(int index, int numberSeat) {
        roomList.get(index).setRoomNumberSeat(numberSeat);
        saveList();
        return roomList.get(index);
    }

    @Override
    public Room editRoomStatus(int index, RoomStatus roomStatus) {
        roomList.get(index).setRoomStatus(roomStatus);
        saveList();
        return roomList.get(index);
    }
    public  Room editClassForRoom(int index , EnglishClass englishClass){
        roomList.get(index).setShiftActive(englishClass);
        saveList();
        return roomList.get(index);
    }

    @Override
    public Room editFirstShiftClass(int index, EnglishClass englishClass) {
        roomList.get(index).setFirstShiftClass(englishClass);
        saveList();
        return roomList.get(index);
    }

    @Override
    public Room editSecondShiftClass(int index, EnglishClass englishClass) {
        roomList.get(index).setSecondShiftClass(englishClass);
        saveList();
        return roomList.get(index);
    }

    @Override
    public boolean delete(String id, Room room) {
        return roomList.remove(roomList.get(findRoomById(id)));
    }

    @Override
    public List<Room> findAll() {
        return roomList;
    }

    @Override
    public Room findById(String id) {
        for (Room element : roomList) {
            if (element.getRoomId().equals(id)) {
                return element;
            }
        }
        return null;
    }
    @Override
    public int findRoomById(String id){
        for (int i = 0; i < roomList.size(); i++) {
           if( roomList.get(i).getRoomId().equals(id)){
               return i;
           }
        }
        return -1;
    }
    public  Room findRoomById(int index){
        return roomList.get(index);
    }
    public ArrayList<Room> findRoomAvailable(Shift shift){
        return  (ArrayList<Room>) roomList.stream()
                .filter(room -> room.checkShiftCanAdd(shift))
                .collect(Collectors.toList());
    }

    @Override
    public void displayById(String id) {
        System.out.println(findById(id));
    }

    @Override
    public void displayALl() {
        for (Room element : roomList) {
            System.out.println(element);
        }
    }

    @Override
    public String getRoomName(int index) {
        return roomList.get(index).getRoomName();
    }

    @Override
    public int getRoomNumberSeat(int index) {
        return roomList.get(index).getRoomNumberSeat();
    }

    @Override
    public RoomStatus getRoomStatus(int index) {
        return roomList.get(index).getRoomStatus();
    }

    @Override
    public EnglishClass getFirstShiftClass(int index) {
        return roomList.get(index).getFirstShiftClass();
    }

    @Override
    public EnglishClass getSecondShiftClass(int index) {
        return roomList.get(index).getSecondShiftClass();
    }

    @Override
    public String getFirstShiftClassName(int index) {
        return roomList.get(index).getFirstShiftClassName();
    }

    @Override
    public String getSecondShiftClassName(int index) {
        return roomList.get(index).getSecondShiftClassName();
    }
}
