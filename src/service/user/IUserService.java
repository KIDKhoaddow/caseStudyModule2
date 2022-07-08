package service.user;

import model.Class.EnglishClass;
import model.Class.Shift;
import model.Room.RoomStatus;
import model.User.Gender;
import model.User.Student;
import model.User.Teacher;
import model.User.User;
import service.IGenericService;

import java.util.List;

public interface IUserService extends IGenericService<User> {
    void createStudent(User user);
    void createTeacher(User user);
    int findUserById(String id);
    int findStudentById(String id);
    int findTeacherById(String id);
    List<Student> findUserStudent();
    List<Teacher> findUserTeacher();
    List<Teacher> findUserTeacherAvailable();
    void saveStudent(Student student);
    void saveTeacher(Teacher teacher);
    String getTeacherName(int index);
    String getCourseName(int index);
    String getClassName(int index);
    String getRoomName(int index);
    Shift getShift(int index);
    Student editClass(int index,EnglishClass englishClass);


    String getFirstCourseName(int index);
    String getFirstClassName(int index);
    String getFirstRoomName(int index);
    Shift getFirstShift(int index);
    String getSecondCourseName(int index);
    String getSecondClassName(int index);
    String getSecondRoomName(int index);
    Shift getSecondShift(int index);
    int getNumberStudentInClass(int index);
    RoomStatus getRoomStatus(int index);
    Teacher editFirstClass(int index,EnglishClass englishClass);
    Teacher editSecondClass(int index,EnglishClass englishClass);
    Teacher editClassForTeacher(int index , EnglishClass englishClass);

    User editName(int index , String name);
    User editUserName(int index , String userName);
    User editUserPassword(int index , String password);
    User editUserAge(int index , int age);
    User editUserPhoneNumber(int index , String phoneNumber);
    User editUserAddress(int index , String address);
    User editUserGender(int index , Gender gender);
    User editUserEmail(int index , String email);
    boolean checkLogin(String userName,String userPassword);
    boolean exitedByUserName(String userName);
    boolean exitedByEmail(String Email);
    int showNumberUser();
    int showNumberStudent();
    int showNumberTeacher();
    void saveAllList();
    void saveUserList();
    void saveStudentList();
    void saveTeacherList();

}
