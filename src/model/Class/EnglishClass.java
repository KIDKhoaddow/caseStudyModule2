package model.Class;

import model.Course.Course;
import model.Room.Room;
import model.User.Teacher;
import service.Class.EnglishClassServiceIMPL;

import java.io.Serializable;

public class EnglishClass implements Serializable {
    private final String classId;
    private String className="";
    private Teacher teacher;
    private Course course;
    private Room room;
    private Shift shift;
    private int numberStudent;
    private int numberSlot;
    private EnglishClassStatus status;


    public EnglishClass(String className,int numberSlot,Shift shift,Course course) {
        int id;
        try {
            id = Integer.parseInt(EnglishClassServiceIMPL.englishClassList
                    .get(EnglishClassServiceIMPL.englishClassList.size() - 1)
                    .getClassId().substring(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            id = 0;
        }
        this.classId = "Class" + String.valueOf(++id);
        this.className = className;
        this.numberSlot=numberSlot;
        this.shift=shift;
        this.status=EnglishClassStatus.PREPARE;
        this.course=course;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        if(className==null){
            return "";
        }
        else return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Shift getShift() {
        if(shift!=Shift.Null){
            return shift;
        }
        return Shift.Null;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public int getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(int numberStudent) {
        this.numberStudent = numberStudent;
    }

    public int getNumberSlot() {
        return numberSlot;
    }

    public void setNumberSlot(int numberSlot) {
        this.numberSlot = numberSlot;
    }

    public String getTeacherName() {
        if(teacher==null){
            return "";
        }
        return teacher.getUserName();
    }

    public String getCourseName() {
        if(course==null){
            return "";
        }
        return course.getCourseName();
    }

    public String getRoomName() {
        if(room==null){
            return "";
        }
        return room.getRoomName();
    }
    public  int getNumberSeat(){
        return room.getRoomNumberSeat();
    }

    public EnglishClassStatus getStatus() {
        return status;
    }

    public void setStatus(EnglishClassStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EnglishClass{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", shift=" + shift +
                ", numberStudent=" + numberStudent +
                ", numberSlot=" + numberSlot +
                ", status=" + status +
                ", teacher="+getTeacherName()+
                ", course="+getCourseName()+
                ", room ="+getRoomName()+
                '}';
    }
}
