package model.User;


import model.Class.EnglishClass;
import model.Class.Shift;
import service.user.UserServiceIMPL;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
    private String teacherId;
    private double teacherSalary = 0.0;
    private EnglishClass firstEnglishClass;
    private EnglishClass secondEnglishClass;


    public Teacher(String userFullName, String userName, String userPassword,
                   String userEmail, int userAge, String userPhoneNumber, String userAddress, Gender gender) {
        super(userFullName, userName, userPassword, userEmail, userAge, userPhoneNumber, userAddress, gender);
        int id;
        try {

            id = Integer.parseInt(UserServiceIMPL.teacherList.get(UserServiceIMPL.teacherList.size() - 1).getUserId().substring(4));
        } catch (Exception e) {
            id = 0;
        }
        this.teacherId = "TCHR" + String.valueOf(++id);

    }


    public EnglishClass getFirstEnglishClass() {
        return firstEnglishClass;
    }

    public boolean setFirstEnglishClass(EnglishClass firstEnglishClass) {
        EnglishClass temp = this.firstEnglishClass;
        this.firstEnglishClass = firstEnglishClass;
        if (!checkShift()) {
            this.firstEnglishClass = temp;
            return false;
        } else {
            temp = null;
            return true;
        }
    }

    public EnglishClass getSecondEnglishClass() {
        return secondEnglishClass;
    }

    public boolean setSecondEnglishClass(EnglishClass secondEnglishClass) {
        EnglishClass temp = this.secondEnglishClass;
        this.secondEnglishClass = secondEnglishClass;
        if (!checkShift()) {
            this.secondEnglishClass = temp;
            return false;
        } else {
            temp = null;
            return true;
        }
    }

    public boolean getStatus() {
        return secondEnglishClass == null || firstEnglishClass == null;
    }

    public double getTeacherSalary() {
        return teacherSalary;
    }

    public void setTeacherSalary(double teacherSalary) {
        this.teacherSalary = teacherSalary;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String idTeacher) {
        this.teacherId = idTeacher;
    }

    public String getFirstCourseName() {
        return firstEnglishClass.getCourseName();
    }

    public String getFirstClassName() {
       if(firstEnglishClass==null){
           return "";
       }else return firstEnglishClass.getClassName();
    }

    public String getFirstRoomName() {
        return firstEnglishClass.getRoomName();
    }

    public Shift getFirstShift() {
        if(firstEnglishClass==null){
            return Shift.Null;
        }
        return firstEnglishClass.getShift();
    }

    public String getSecondCourseName() {
        return secondEnglishClass.getCourseName();
    }

    public String getSecondClassName() {
        if(secondEnglishClass==null){
            return "";
        }else return secondEnglishClass.getClassName();
    }

    public String getSecondRoomName() {
        return secondEnglishClass.getRoomName();
    }

    public Shift getSecondShift() {
        if(secondEnglishClass==null){
            return Shift.Null;
        }
        return secondEnglishClass.getShift();
    }

    public EnglishClass getShiftActive() {
        if (firstEnglishClass != null) {
            return secondEnglishClass;
        } else if (secondEnglishClass != null) {
            return null;
        }
        return null;
    }

    public void setShiftActive(EnglishClass englishClass) {
        if(checkShiftCanAdd(englishClass.getShift())){
            if(firstEnglishClass==null){
                if(secondEnglishClass!=null&&secondEnglishClass.getShift()!=englishClass.getShift()){
                    setFirstEnglishClass(englishClass);
                } else if (secondEnglishClass==null) {
                    setFirstEnglishClass(englishClass);
                }
            }else if(secondEnglishClass==null){
                if(firstEnglishClass.getShift() != englishClass.getShift()){
                    setSecondEnglishClass(englishClass);
                }
            }
        }
    }

    private boolean checkShift() {
        if(firstEnglishClass==null||secondEnglishClass==null){
            return true;
        }else if(firstEnglishClass.getShift()!=secondEnglishClass.getShift()){
            return true;
        }
        else return false;
    }

    public boolean checkShiftCanAdd(Shift shift) {
        return getFirstShift() != shift && getSecondShift() != shift;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userId='" + userId + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAge=" + userAge +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userRole=" + userRole +
                ", userGender=" + userGender +
                ", teacherId='" + teacherId + '\'' +
                ", teacherSalary=" + teacherSalary +
                ", firstEnglishClass=" + getFirstClassName() +
                ", secondEnglishClass=" +getSecondClassName()+
                '}';
    }
}
