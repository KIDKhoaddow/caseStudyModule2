package model.User;


import model.Class.EnglishClass;
import model.Class.EnglishClassStatus;
import model.Class.Shift;
import service.user.UserServiceIMPL;

import java.io.Serializable;

public class Student extends User implements Serializable {

    private String studentId;
    private EnglishClass englishClass;

    public Student(String userFullName, String userName, String userPassword, String userEmail,
                   int userAge, String userPhoneNumber, String userAddress, Gender gender) {
        super(userFullName, userName, userPassword, userEmail, userAge, userPhoneNumber, userAddress, gender);
        int id;
        try {
            id = Integer.parseInt(UserServiceIMPL.studentList.get(UserServiceIMPL.studentList.size() - 1).getStudentId().substring(3));
        } catch (Exception e) {
            id = 0;
        }
        this.studentId = "STU" + String.valueOf(++id);
    }

    public EnglishClass getEnglishClass() {
        return englishClass;
    }

    public boolean setEnglishClass(EnglishClass englishClass) {
        if (this.englishClass == null) {
            englishClass.setNumberStudent(englishClass.getNumberStudent() + 1);
            this.englishClass = englishClass;
            return true;
        } else {
            boolean checkNumberSlot = englishClass.getNumberStudent() < englishClass.getNumberSlot();
            boolean checkClassStatus = englishClass.getStatus() == EnglishClassStatus.PREPARE;
            if (checkNumberSlot && checkClassStatus) {
                englishClass.setNumberStudent(englishClass.getNumberStudent() + 1);
                this.englishClass.setNumberStudent(this.englishClass.getNumberStudent() - 1);
                this.englishClass = englishClass;
                return true;
            } else {
                return false;
            }
        }

    }
//    public  String getEnglishClassId(){
//        return englishClass.getClassId();
//    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        if (englishClass == null) {
            return "";
        }
        return englishClass.getCourseName();
    }

    public String getTeacherName() {
        if (englishClass == null) {
            return "";
        }
        return englishClass.getTeacherName();
    }

    public String getRoomName() {
        if (englishClass == null) {
            return "";
        }
        return englishClass.getRoomName();
    }

    public String getClassName() {
        if (englishClass == null) {
            return "";
        }
        return englishClass.getClassName();
    }

    public Shift getShift() {
        if (englishClass == null) {
            return Shift.Null;
        }
        return englishClass.getShift();
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", userId='" + userId + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAge=" + userAge +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userRole=" + userRole +
                ", userGender=" + userGender +
                ", englishClass=" + getClassName() +
                ", teacherName=" + getTeacherName() +
                ", courseName=" + getCourseName() +
                ", roomName=" + getRoomName() +
                '}';
    }
}
