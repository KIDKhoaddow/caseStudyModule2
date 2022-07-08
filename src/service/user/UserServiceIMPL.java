package service.user;

import config.ConfigReadAndWriteFile;
import model.Class.EnglishClass;
import model.Class.Shift;
import model.Room.RoomStatus;
import model.User.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceIMPL implements IUserService {
    public static String PATH_USER = ConfigReadAndWriteFile.PATH + "user.txt";
    public static List<User> userList = new ConfigReadAndWriteFile<User>().readFromFile(PATH_USER);


    public static String PATH_STUDENT = ConfigReadAndWriteFile.PATH + "student.txt";
    public static List<Student> studentList = new ConfigReadAndWriteFile<Student>().readFromFile(PATH_STUDENT);


    public static String PATH_TEACHER = ConfigReadAndWriteFile.PATH + "teacher.txt";
    public static List<Teacher> teacherList = new ConfigReadAndWriteFile<Teacher>().readFromFile(PATH_TEACHER);


    @Override
    public void save(User user) {
        userList.add(user);
        new ConfigReadAndWriteFile<User>().writeToFile(PATH_USER, userList);

    }

    @Override
    public void saveAllList() {
        saveUserList();
        saveStudentList();
        saveTeacherList();
    }

    @Override
    public void saveUserList() {
        new ConfigReadAndWriteFile<User>().writeToFile(PATH_USER, userList);
    }

    @Override
    public void saveStudentList() {
        new ConfigReadAndWriteFile<User>().writeToFile(PATH_USER, userList);
        new ConfigReadAndWriteFile<Student>().writeToFile(PATH_STUDENT, studentList);
    }

    @Override
    public void saveTeacherList() {
        new ConfigReadAndWriteFile<User>().writeToFile(PATH_USER, userList);
        new ConfigReadAndWriteFile<Teacher>().writeToFile(PATH_TEACHER, teacherList);
    }

    @Override
    public boolean delete(String id, User user) {
        return userList.remove(findById(id));
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public List<Student> findUserStudent() {
        return studentList;
    }

    @Override
    public List<Teacher> findUserTeacher() {
        return teacherList;
    }

    @Override
    public List<Teacher> findUserTeacherAvailable() {
        return teacherList.stream().filter(teacher -> teacher.getStatus())
                .collect(Collectors.toList());
    }

    @Override
    public User findById(String id) {
        for (User element : userList) {
            if (element.getUserId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public int findUserById(String id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public User findUserById(int index){
        return userList.get(index);
    }

    @Override
    public int findStudentById(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public  int findStudentNoClassById(String id,ArrayList<Student> studentList){
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(id)) {
                return i;
            }
        }
        return -1;

    }

    public Student findStudentById(int index) {
        return studentList.get(index);
    }


    public ArrayList<Student> findStudentNoClass() {
        return (ArrayList<Student>) studentList.stream()
                .filter(student -> student.getEnglishClass() == null)
                .collect(Collectors.toList());
    }

    @Override
    public int findTeacherById(String id) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getTeacherId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public Teacher findTeacherById(int index){
        return teacherList.get(index);
    }
    public  int findTeacherById(String id, ArrayList<Teacher> teacherList){
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getTeacherId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
//    public ArrayList<Student> findStudentByClassId(String classId){
//        return (ArrayList<Student>) studentList.stream()
//                .filter(student -> student.getEnglishClassId()
//                        .equals(classId)).collect(Collectors.toList());
//    }


    @Override
    public void displayById(String id) {
        System.out.println(findById(id));
    }

    @Override
    public void displayALl() {
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Override
    public void createStudent(User user) {
        user.setUserRole(Role.STUDENT);

    }

    @Override
    public void saveStudent(Student student) {
        studentList.add(student);
        saveStudentList();

    }

    @Override
    public void createTeacher(User user) {
        user.setUserRole(Role.TEACHER);

    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherList.add(teacher);
        saveTeacherList();
    }

    @Override
    public void edit(int index, User user) {
        userList.set(index, user);
        saveAllList();
    }

    @Override
    public User editName(int index, String name) {
        userList.get(index).setUserFullName(name);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserName(int index, String userName) {
        userList.get(index).setUserName(userName);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserPassword(int index, String password) {
        userList.get(index).setUserPassword(password);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserAge(int index, int age) {
        userList.get(index).setUserAge(age);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserPhoneNumber(int index, String phoneNumber) {
        userList.get(index).setUserPhoneNumber(phoneNumber);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserAddress(int index, String address) {
        userList.get(index).setUserAddress(address);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserGender(int index, Gender gender) {
        userList.get(index).setUserGender(gender);
        saveUserList();
        return userList.get(index);
    }

    @Override
    public User editUserEmail(int index, String email) {
        userList.get(index).setUserEmail(email);
        saveUserList();
        return userList.get(index);
    }


    @Override
    public boolean checkLogin(String userName, String userPassword) {
        for (User element : userList) {
            if (element.getUserName().equals(userName) && element.getUserPassword().equals(userPassword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exitedByUserName(String userName) {
        for (User element : userList) {
            if (element.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean exitedByEmail(String email) {
        for (User element : userList) {
            if (element.getUserEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email) {
        for (User element : userList) {
            if (element.getUserEmail().equals(email)) {
                return element;
            }
        }
        return null;
    }
    public  EnglishClass getShiftActive(int index ){
       return teacherList.get(index).getShiftActive();
    }

    @Override
    public String getTeacherName(int index) {
        return studentList.get(index).getTeacherName();
    }

    @Override
    public String getCourseName(int index) {
        return studentList.get(index).getCourseName();
    }

    @Override
    public String getClassName(int index) {
        return studentList.get(index).getClassName();
    }

    @Override
    public String getRoomName(int index) {
        return studentList.get(index).getRoomName();
    }

    @Override
    public Shift getShift(int index) {
        return studentList.get(index).getShift();
    }


    @Override
    public Student editClass(int index, EnglishClass englishClass) {
        studentList.get(index).setEnglishClass(englishClass);
        saveStudentList();
        return studentList.get(index);
    }
    public boolean editClassForStudent(ArrayList<Student> studentList,EnglishClass englishClass){
        try {

            for (Student element:studentList) {
                element.setEnglishClass(englishClass);
            }
        }catch (Exception e)    {
            return false;
        }
        return true;
    }


    @Override
    public String getFirstCourseName(int index) {
        return teacherList.get(index).getFirstCourseName();
    }

    @Override
    public String getFirstClassName(int index) {
        return teacherList.get(index).getFirstClassName();
    }

    @Override
    public String getFirstRoomName(int index) {
        return teacherList.get(index).getFirstRoomName();
    }

    @Override
    public Shift getFirstShift(int index) {
        return teacherList.get(index).getFirstShift();
    }

    @Override
    public String getSecondCourseName(int index) {
        return teacherList.get(index).getSecondCourseName();
    }

    @Override
    public String getSecondClassName(int index) {
        return teacherList.get(index).getSecondClassName();
    }

    @Override
    public String getSecondRoomName(int index) {
        return teacherList.get(index).getSecondRoomName();
    }

    @Override
    public Shift getSecondShift(int index) {
        return teacherList.get(index).getSecondShift();
    }

    @Override
    public int getNumberStudentInClass(int index) {
        return 0;
    }

    @Override
    public RoomStatus getRoomStatus(int index) {
        return null;
    }

    @Override
    public Teacher editClassForTeacher(int index, EnglishClass englishClass) {
        teacherList.get(index).setShiftActive(englishClass);
        saveTeacherList();
        return teacherList.get(index);
    }

    @Override
    public Teacher editFirstClass(int index, EnglishClass englishClass) {
        teacherList.get(index).setFirstEnglishClass(englishClass);
        saveTeacherList();
        return teacherList.get(index);
    }

    @Override
    public Teacher editSecondClass(int index, EnglishClass englishClass) {
        teacherList.get(index).setSecondEnglishClass(englishClass);
        saveTeacherList();
        return teacherList.get(index);
    }

    @Override
    public int showNumberUser() {
        return userList.size();
    }

    @Override
    public int showNumberStudent() {
        return studentList.size();
    }

    @Override
    public int showNumberTeacher() {
        return teacherList.size();
    }
}
