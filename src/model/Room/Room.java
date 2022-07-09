package model.Room;

import model.Class.EnglishClass;
import model.Class.Shift;
import service.Room.RoomServiceIMPL;

import java.io.Serializable;

public class Room implements Serializable {
    private String roomId;
    private String roomName;
    private int roomNumberSeat;
    private RoomStatus roomStatus;
    private EnglishClass secondShiftClass;
    private EnglishClass firstShiftClass;


    public Room() {
    }

    public Room(String roomName, int roomNumberSeat) {
        int id;
        try {
            id = Integer.parseInt(RoomServiceIMPL.roomList.get(RoomServiceIMPL.roomList.size() - 1).getRoomId().substring(4));
        } catch (Exception e) {
            id = 0;
        }
        this.roomId = "Room" +String.valueOf( ++id);
        this.roomName = roomName;
        this.roomNumberSeat = roomNumberSeat;
        this.roomStatus=RoomStatus.AVAILABLE;
    }
    public Room(String roomId,String roomName,int roomNumberSeat,RoomStatus status){
        this.roomId=roomId;
        this.roomName=roomName;
        this.roomNumberSeat=roomNumberSeat;
        this.roomStatus=getRoomStatus();
    }

    public String getRoomId() {
        return roomId;
    }

//    public void setRoomId(String roomId) {
//        this.roomId = roomId;
//    }

    public String getRoomName() {
        try{
            return roomName;
        }catch (Exception e){
            return "";
        }
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public int getRoomNumberSeat() {
        return roomNumberSeat;
    }

    public void setRoomNumberSeat(int roomNumberSeat) {
        this.roomNumberSeat = roomNumberSeat;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }



    public EnglishClass getSecondShiftClass() {
        return secondShiftClass;
    }
    public boolean setFirstShiftClass(EnglishClass firstShiftClass) {
        EnglishClass temp=this.firstShiftClass;
        this.firstShiftClass = firstShiftClass;
        if (checkSeat(firstShiftClass.getNumberStudent())&&checkShift()) {
            temp=null;
            changeStatus();
            return true;
        }else {
            this.firstShiftClass=temp;
            temp=null;
            return false;
        }
    }
    private void changeStatus(){
        if(firstShiftClass!=null&&secondShiftClass!=null){
            if(firstShiftClass.getShift()!=secondShiftClass.getShift()){
                roomStatus=RoomStatus.UNAVAILABLE;
            }else {
                roomStatus=RoomStatus.AVAILABLE;
            }
        }else roomStatus=RoomStatus.AVAILABLE;
    }
    public boolean setSecondShiftClass(EnglishClass secondShiftClass) {
        EnglishClass temp=this.secondShiftClass;
        this.secondShiftClass = secondShiftClass;
        if (checkSeat(secondShiftClass.getNumberStudent())&&checkShift()) {
            temp=null;
            changeStatus();
            return true;
        }else {
            this.secondShiftClass=temp;
            temp=null;
            return false;
        }
    }

    private boolean checkSeat(int numberStudent) {
        return numberStudent <= roomNumberSeat;
    }

    public EnglishClass getFirstShiftClass() {
        return  firstShiftClass;
    }


    public  String getFirstShiftClassName(){
        if(firstShiftClass==null){
            return "";
        }
        return firstShiftClass.getClassName();
    }

    public  String getSecondShiftClassName(){
        if(secondShiftClass==null){
            return "";
        }
        return secondShiftClass.getClassName();
    }
    public void setShiftActive(EnglishClass englishClass) {
        if(checkShiftCanAdd(englishClass.getShift())){
            if(firstShiftClass==null){
                if(secondShiftClass!=null&&secondShiftClass.getShift()!=englishClass.getShift()){
                    setFirstShiftClass(englishClass);
                } else if (secondShiftClass==null) {
                    setFirstShiftClass(englishClass);
                }
            }else if(secondShiftClass==null){
                if(firstShiftClass.getShift() != englishClass.getShift()){
                    setSecondShiftClass(englishClass);
                }
            }
        }
    }
    private boolean checkShift() {
        if(firstShiftClass==null||secondShiftClass==null){
            return true;
        }else if(firstShiftClass.getShift()!=secondShiftClass.getShift()){
            return true;
        }
        else return false;
    }
    public Shift getFirstShift() {
        if(firstShiftClass==null){
            return Shift.Null;
        }
        return firstShiftClass.getShift();
    }
    public Shift getSecondShift() {
        if(secondShiftClass==null){
            return Shift.Null;
        }
        return secondShiftClass.getShift();
    }
    public boolean checkShiftCanAdd(Shift shift) {
        return getFirstShift() != shift && getSecondShift() != shift;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", roomName='" + roomName + '\'' +
                ", roomNumberSeat=" + roomNumberSeat +
                ", roomStatus=" + roomStatus +
                ", firstShiftClass=" + getFirstShiftClassName() +
                ", secondShiftClass=" + getSecondShiftClassName() +
                '}';
    }
}
