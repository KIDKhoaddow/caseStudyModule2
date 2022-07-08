package controller;

import model.Class.EnglishClass;
import model.Course.Course;
import model.User.Gender;
import model.User.Student;
import model.User.Teacher;
import model.User.User;
import service.Class.EnglishClassServiceIMPL;
import service.Course.CourseServiceIMPL;
import service.user.UserServiceIMPL;
import view.AdminView.User.Edit.EditStudent;
import view.AdminView.User.Edit.EditTeacher;
import view.AdminView.User.Edit.EditUser;
import view.AdminView.User.Edit.EditView;
import view.AdminView.User.RegisterView;
import view.Common.CommonView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserController {
    private UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    private CourseServiceIMPL courseServiceIMPL = new CourseServiceIMPL();
    private EnglishClassServiceIMPL englishClassServiceIMPL = new EnglishClassServiceIMPL();

    public void chooseFunction(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ UserList FORM ============");
        commonView.showMessage("1.Create User ");
        commonView.showMessage("2.Edit User ");
        commonView.showMessage("3.Display User list ");
        commonView.showMessage("4.Display Student List");
        commonView.showMessage("5.Display Teacher List ");
        commonView.showMessage("6.Remove User ");
        commonView.showMessage("7.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (checkInputNumber(1, 7, selection)) {
                break;
            } else {
                commonView.showMessage("Wrong number!Try Again Please :");
                selection = commonView.inputString(scanner);
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                new RegisterView(commonView, scanner);
                break;
            case 2:
                new EditView(commonView, scanner);
                break;
            case 3:
                commonView.displayUserList(userServiceIMPL.findAll());
                break;
            case 4:
                commonView.displayStudentList(userServiceIMPL.findUserStudent());
                break;
            case 5:
                commonView.displayTeacherList(userServiceIMPL.findUserTeacher());
                break;
            case 6:
            case 7:
                return;
        }
        chooseFunction(commonView, scanner);
    }

    public void chooseFunctionEdit(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ EDIT USER FORM ============");
        commonView.showMessage("1.Edit User Profile ");
        commonView.showMessage("2.Edit Student");
        commonView.showMessage("3.Edit Teacher");
        commonView.showMessage("4.Back ");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (checkInputNumber(1, 4, selection)) {
                break;
            } else {
                commonView.showMessage("Wrong number!Try Again Please :");
                selection = commonView.inputString(scanner);
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                new EditUser(commonView, scanner);
                break;
            case 2:
                new EditStudent(commonView, scanner);
                break;
            case 3:
                new EditTeacher(commonView, scanner);
            case 4:
                return;
        }
        chooseFunctionEdit(commonView, scanner);
    }

    public User editName(CommonView commonView, Scanner scanner, int indexUser) {
        String name = commonView.inputString("The Name you want to change to : ", scanner);
        return userServiceIMPL.editName(indexUser, name);
    }

    public User editUserName(CommonView commonView, Scanner scanner, int indexUser) {
        String userName = commonView.inputString("The UserName you want to change to :", scanner);
        while (true) {
            if (userServiceIMPL.exitedByUserName(userName)) {
                userName = commonView.inputString(" The UserName has already existed", scanner);
            } else if (commonView.isInputString(userName)) {
                userName = commonView.inputString("The UserName failed , try again please :", scanner);
            } else {
                break;
            }
        }
        return userServiceIMPL.editUserName(indexUser, userName);
    }

    public User editAge(CommonView commonView, Scanner scanner, int indexUser) {
        String age = commonView.inputString("The Age you want to change to :", scanner);
        while (true) {
            if (!checkInputNumber(age)) {
                age = commonView.inputString("The age failed try again please", scanner);
            } else {
                break;
            }

        }
        return userServiceIMPL.editUserAge(indexUser, Integer.parseInt(age));
    }

    public User editPhoneNumber(CommonView commonView, Scanner scanner, int indexUser) {
        String phoneNumber = commonView.inputString("The Phone Number you want to change to :", scanner);
        while (true) {
            if (!checkPhoneNumber(phoneNumber)) {
                phoneNumber = commonView.inputString("The age failed try again please", scanner);
            } else {
                break;
            }

        }
        return userServiceIMPL.editUserPhoneNumber(indexUser, phoneNumber);
    }

    public User editAddress(CommonView commonView, Scanner scanner, int indexUser) {
        String address = commonView.inputString("The Address you want to change to :", scanner);
        return userServiceIMPL.editUserAddress(indexUser, address);
    }

    public User editGender(CommonView commonView, Scanner scanner, int indexUser) {
        commonView.showMessage("The Gender you want to change to :");
        commonView.showMessage("1.Male");
        commonView.showMessage("2.Female");
        commonView.showMessage("3.Other");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                selection = commonView.inputString("Wrong number try again please", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                return userServiceIMPL.editUserGender(indexUser, Gender.Male);
            case 2:
                return userServiceIMPL.editUserGender(indexUser, Gender.Female);
            case 3:
                return userServiceIMPL.editUserGender(indexUser, Gender.Other);
            default:
                return null;
        }


    }

    public User editPassword(CommonView commonView, Scanner scanner, int indexUser) {
        String password = commonView.inputString("The password you want to change to : ", scanner);
        while (true) {
            if (!checkPassword(password)) {
                password = commonView.inputString("Wrong password ,try again please : ", scanner);
            } else {
                break;
            }
        }
        return userServiceIMPL.editUserPassword(indexUser, password);
    }

    public User editEmail(CommonView commonView, Scanner scanner, int indexUser) {
        String email = commonView.inputString("The Email you want to change to : ", scanner);
        while (true) {
            if (!checkEmail(email)) {
                email = commonView.inputString("Wrong email , try again please : ", scanner);
            } else {
                break;
            }
        }
        return userServiceIMPL.editUserEmail(indexUser, email);
    }

    public Course choseCourse(CommonView commonView, Scanner scanner) {
        String courseId = commonView.inputString("Enter the Course id you want to register (course +id):", scanner);
        int indexCourse = courseServiceIMPL.findCourseById(courseId);
        Course course = CourseServiceIMPL.courseList.get(indexCourse);
        while (true) {
            if (course == null) {
                commonView.showMessage("Wrong or don't exist Course Id, try again please :");
                courseId = commonView.inputString("Enter the Course id : ", scanner);
                indexCourse = courseServiceIMPL.findCourseById(courseId);
                course = CourseServiceIMPL.courseList.get(indexCourse);
            } else {
                break;
            }
        }
        return course;
    }


    public User choseClass(CommonView commonView, Scanner scanner, int indexStudent, Course course) {
        for (EnglishClass element : englishClassServiceIMPL.findAll()) {
            if (element.getCourseName().equals(course.getCourseName())) {
            }
        }

        String englishClassId = commonView.inputString("Enter the English Class Id you want to register(class+id)", scanner);
        int indexClass = courseServiceIMPL.findCourseById(englishClassId);
        EnglishClass englishClass = EnglishClassServiceIMPL.englishClassList.get(indexClass);
        while (true) {
            if (englishClass == null) {
                commonView.showMessage("Wrong or don't exist english class ,try again please :");
                englishClassId = commonView.inputString("Enter the English Class Id :", scanner);
                indexClass = courseServiceIMPL.findCourseById(englishClassId);
                englishClass = EnglishClassServiceIMPL.englishClassList.get(indexClass);
            } else {
                break;
            }
        }
        return userServiceIMPL.editClass(indexStudent, englishClass);
    }

    public boolean checkPassword(String string) {
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$", string);
    }

    public boolean checkEmail(String string) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", string);
    }

    public boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);

        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }

    public boolean checkPhoneNumber(String number) {
        return Pattern.matches("[0-9]{10}", number);
    }

    public boolean checkInputNumber(String number) {

        return Pattern.matches("[0-9]{1,2}", number);
    }

    public void chooseEditUserSelection(CommonView commonView, Scanner scanner, int indexUser) {
        commonView.showMessage("Step 2 : Enter Your Option");
        commonView.showMessage("1.Edit Name");
        commonView.showMessage("2.Edit Age");
        commonView.showMessage("3.Edit PhoneNumber");
        commonView.showMessage("4.Edit Address");
        commonView.showMessage("5.Edit Email");
        commonView.showMessage("6.Edit Gender");
        commonView.showMessage("7.Edit UserName");
        commonView.showMessage("8.Edit Password");
        commonView.showMessage("9.Back Form");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 9, selection)) {
                commonView.showMessage("Wrong number , try again please:");
                selection = commonView.inputString(scanner);
            } else {
                break;
            }
        }
        if(Integer.parseInt(selection)!=9){
            commonView.displayObject(userServiceIMPL.findUserById(indexUser));
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                commonView.displayObject(editName(commonView, scanner, indexUser));
                break;
            case 2:
                commonView.displayObject(editAge(commonView, scanner, indexUser));
                break;
            case 3:
                commonView.displayObject(editPhoneNumber(commonView, scanner, indexUser));
                break;
            case 4:
                commonView.displayObject(editAddress(commonView, scanner, indexUser));
                break;
            case 5:
                commonView.displayObject(editEmail(commonView, scanner, indexUser));
                break;
            case 6:
                commonView.displayObject(editGender(commonView, scanner, indexUser));
                break;
            case 7:
                commonView.displayObject(editUserName(commonView, scanner, indexUser));
                break;
            case 8:
                commonView.displayObject(editPassword(commonView, scanner, indexUser));
                break;
            case 9:
                return;
        }
        chooseEditUserSelection(commonView, scanner, indexUser);

    }

    public int findUser(CommonView commonView, Scanner scanner) {
        String id = commonView.inputString("Step 1  Enter User ID You Want Edit (User+ID):", scanner);
        int position = userServiceIMPL.findUserById(id);
        while (true) {
            if (position == (-1)) {
                commonView.showMessage("Wrong or don't exist User Id,try again please");
                id = commonView.inputString("Enter Id You Want Edit :", scanner);
                position = userServiceIMPL.findUserById(id);
            } else {
                break;
            }
        }
        User user = UserServiceIMPL.userList.get(position);
        commonView.displayObject(user);
        return userServiceIMPL.findUserById(id);
    }

    public int findStudent(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Show the new Student :");
        ArrayList<Student> studentList = userServiceIMPL.findStudentNoClass();
        for (Student element : studentList) {
            commonView.displayObject(element);
        }
        String id = commonView.inputString("Step 1 Enter Student ID You Want To Edit (STU+ID) :", scanner);
        int position = userServiceIMPL.findStudentNoClassById(id, studentList);
        while (true) {
            if (position == (-1)) {
                commonView.showMessage("Wrong or don't exist User Id,try again please");
                id = commonView.inputString("Enter Id You Want Edit :", scanner);
                position = userServiceIMPL.findStudentNoClassById(id, studentList);
            } else {
                return userServiceIMPL.findStudentById(id);
            }
        }
    }

    public Student findStudent(int index) {
        return userServiceIMPL.findStudentById(index);
    }

    public void displayStudentOption(CommonView commonView, Scanner scanner, int indexStudent) {
        commonView.showMessage("Step 2 : Select Course");
        for (Course element : courseServiceIMPL.findAll()) {
            commonView.displayObject(element);
        }
        String index = commonView.inputString("Enter Course ID you want to choose :", scanner);
        int indexCourse = courseServiceIMPL.findCourseById(index);
        while (true) {
            if (indexCourse == (-1)) {
                commonView.showMessage("Wrong or don't exist Course Id,try again please");
                index = commonView.inputString("Enter Course Id You Want Edit :", scanner);
            } else {
                break;
            }
        }
        commonView.showMessage("Step 3 : Select Class Available");
        Course course = courseServiceIMPL.findById(indexCourse);
        ArrayList<EnglishClass> englishClassList = englishClassServiceIMPL.getEnglishClassAvailable(course);
        for (EnglishClass element : englishClassList) {
            commonView.showMessage(element.getClassId() + ":" + element.getClassName());
        }
        index = commonView.inputString("Enter English Class Id you want to choose :", scanner);
        int indexEnglishClass = englishClassServiceIMPL.findClassByIndex(index);
        while (true) {
            if (indexEnglishClass == (-1)) {
                commonView.showMessage("Wrong or don't exist English Class Id , try again please:");
                index = commonView.inputString("Enter English Class Id you want to choose :", scanner);
                indexEnglishClass = englishClassServiceIMPL.findClassByIndex(index);
            } else {
                break;
            }
        }
        Student student = userServiceIMPL.editClass(indexStudent, englishClassServiceIMPL.findClassByIndex(indexEnglishClass));
        commonView.showMessage(student.getTeacherName() + ":" + student.getClassName() + "," + student.getCourseName());
        commonView.showMessage("Join Success");

    }

    public Teacher findTeacher(int index) {
        return userServiceIMPL.findTeacherById(index);
    }

    public int findTeacher(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Show Teacher :");
        ArrayList<Teacher> teacherList = (ArrayList<Teacher>) userServiceIMPL.findUserTeacherAvailable();
        for (Teacher element : teacherList) {
            commonView.showMessage(element.getTeacherId() + ":" + element.getUserFullName());
        }
        String id = commonView.inputString("Step 1 Enter Teacher ID You Want To Edit (STU+ID) :", scanner);
        int position = userServiceIMPL.findTeacherById(id, teacherList);
        while (true) {
            if (position == (-1)) {
                commonView.showMessage("Wrong or don't exist Teacher Id,try again please");
                id = commonView.inputString("Enter Id You Want Edit :", scanner);
                position = userServiceIMPL.findTeacherById(id, teacherList);
            } else {
                return userServiceIMPL.findTeacherById(id);
            }
        }
    }

    public void displayTeacherOption(CommonView commonView, Scanner scanner, int indexTeacher) {
        commonView.showMessage("Step 2 : Select Course");
        for (Course element : courseServiceIMPL.findAll()) {
            commonView.displayObject(element);
        }
        String index = commonView.inputString("Enter Course ID you want to choose :", scanner);
        int indexCourse = courseServiceIMPL.findCourseById(index);
        while (true) {
            if (indexCourse == (-1)) {
                commonView.showMessage("Wrong or don't exist Course Id,try again please");
                index = commonView.inputString("Enter Course Id You Want Edit :", scanner);
            } else {
                break;
            }
        }
        commonView.showMessage("Step 3 : Select Class Available");
        Course course = courseServiceIMPL.findById(indexCourse);
        ArrayList<EnglishClass> englishClassList = englishClassServiceIMPL.getEnglishClassNoTeacher();

        if (englishClassList.size()==0){
            commonView.showMessage("There are no matching  English Classes ");
            return;
        }

        for (EnglishClass element : englishClassList) {
            commonView.showMessage(element.getClassId() + ":" + element.getClassName());
        }
        index = commonView.inputString("Enter English Class Id you want to choose :", scanner);
        int indexEnglishClass = englishClassServiceIMPL.findClassByIndex(index);
        while (true) {
            if (indexEnglishClass == (-1)) {
                commonView.showMessage("Wrong or don't exist English Class Id , try again please:");
                index = commonView.inputString("Enter English Class Id you want to choose :", scanner);
                indexEnglishClass = englishClassServiceIMPL.findClassByIndex(index);
            } else {
                break;
            }
        }
        EnglishClass englishClass= englishClassServiceIMPL.findClassByIndex(indexEnglishClass);
        Teacher teacher = userServiceIMPL.editClassForTeacher(indexTeacher, englishClass);
        englishClassServiceIMPL.editTeacher(indexEnglishClass,teacher);
        commonView.showMessage("Join Success");
    }

    public String addUserName(CommonView commonView, Scanner scanner) {
        String userName = commonView.inputString("Enter UserName : ", scanner);
        while (true) {
            if (!commonView.isInputString(userName)) {
                System.err.println("Username Failed! Please try again!");
            } else if (userServiceIMPL.exitedByUserName(userName)) {
                System.err.println("Username is existed! Please try again!");
            } else {
                break;
            }
            userName = commonView.inputString("Enter User Name :", scanner);
        }
        return userName;
    }

    public String addUserEmail(CommonView commonView, Scanner scanner) {
        String userEmail = commonView.inputString("Enter Your Email :", scanner);
        while (true) {
            if (!checkEmail(userEmail)) {
                System.err.print("Email Failed! Please try again!");
            } else if (userServiceIMPL.exitedByEmail(userEmail)) {
                System.err.println("Email is existed!Please try again");
            } else {
                break;
            }
            userEmail = commonView.inputString("Enter Your Email  : ", scanner);
        }
        return userEmail;
    }

    public int addUserAge(CommonView commonView, Scanner scanner) {
        String userAge = commonView.inputString("Enter your age : ", scanner);
        while (true) {
            if (!checkInputNumber(userAge)) {
                userAge = commonView.inputString("Wrong number , try again please :", scanner);
            } else {
                break;
            }
        }
        return Integer.parseInt(userAge);
    }
    public String addUserPhone(CommonView commonView,Scanner scanner){
        String userPhoneNumber = commonView.inputString("Enter Your Phone :", scanner);
        while(true){
            if(!checkPhoneNumber(userPhoneNumber)){
                userPhoneNumber=commonView.inputString("Worng number , try again please :",scanner);
            }else break;
        }
        return userPhoneNumber;
    }

    public String addPassword(CommonView commonView, Scanner scanner) {
        String userPassword = commonView.inputString("Enter UserPassword : ", scanner);
        while (true) {
            if (!checkPassword(userPassword)) {
                userPassword = commonView.inputString("Wrong password ,try again please : ", scanner);
            } else {
                break;
            }
        }
        return userPassword;
    }

    public Gender addUserGender(CommonView commonView, Scanner scanner) {
        commonView.showMessage("The Gender you want to choose to :");
        commonView.showMessage("1. Male");
        commonView.showMessage("2. Female");
        commonView.showMessage("3. Other");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                selection = commonView.inputString("Wrong number try again please", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                return Gender.Male;
            case 2:
                return Gender.Female;
            case 3:
                return Gender.Other;
            default:
                return null;
        }
    }

    public void selectRole(CommonView commonView, Scanner scanner, String name,
                           String userName, String userPassword, String userEmail,
                           int userAge, String userPhoneNumber, String userAddress, Gender gender) {
        commonView.showMessage("Who will this User be");
        commonView.showMessage("1.Student");
        commonView.showMessage("2.Teacher");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 2, selection)) {
                commonView.showMessage("Wrong number , chose again please ");
                selection = commonView.inputString(scanner);
            } else break;
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                Student student = new Student(name, userName, userPassword,
                        userEmail, userAge, userPhoneNumber, userAddress, gender);
                userServiceIMPL.createStudent(student);
                userServiceIMPL.saveStudent(student);
                commonView.showMessage("Create Student Success");
                userServiceIMPL.save(student);
                commonView.displayObject(student);
                break;
            case 2:
                Teacher teacher = new Teacher(name, userName, userPassword,
                        userEmail, userAge, userPhoneNumber, userAddress, gender);
                userServiceIMPL.createTeacher(teacher);
                userServiceIMPL.saveTeacher(teacher);
                commonView.showMessage("Create Teacher Success");
                userServiceIMPL.save(teacher);
                commonView.displayObject(teacher);
                break;
        }
    }
    public int loginStudentView(CommonView commonView , Scanner scanner){
        commonView.showMessage("============ LOGIN FORM ============");
        commonView.showMessage("Enter Exit to User Name and Password to back");
        String name= commonView.inputString("Enter your User Name :",scanner);
        String password= commonView.inputString("Enter your User Password :",scanner);
        ArrayList<Student> students=(ArrayList<Student>) userServiceIMPL.findUserStudent();
        for (int i = 0; i <students.size(); i++) {
            if(name.equals(students.get(i).getUserName())&&password.equals(students.get(i).getUserPassword())){
                commonView.showMessage("Login Success ");
                return i;
            }else if(name.equals("Exit")&&password.equals("Exit")){
                commonView.showMessage("Login Fail ");
                return -1;
            }
            else {
                commonView.showMessage("Login failed ! Please check your Name or Pasword!");
                loginStudentView(commonView,scanner);
            }
        }
        return -1;
    }
    public int loginTeacherView(CommonView commonView , Scanner scanner){
        commonView.showMessage("============ LOGIN FORM ============");
        String name= commonView.inputString("Enter your User Name :",scanner);
        String password= commonView.inputString("Enter your User Password :",scanner);
        commonView.showMessage("Enter Exit to User Name and Password to back");
        ArrayList<Teacher> teachers=(ArrayList<Teacher>) userServiceIMPL.findUserTeacher();
        for (int i = 0; i <teachers.size(); i++) {
            if(name.equals(teachers.get(i).getUserName())&&password.equals(teachers.get(i).getUserPassword())){
                commonView.showMessage("Login Success ");
                return i;
            }else if(name.equals("Exit")&&password.equals("Exit")){
                commonView.showMessage("Login Fail ");
                return -1;
            }
            else {
                commonView.showMessage("Login failed ! Please check your Name or Pasword!");
                loginTeacherView(commonView,scanner);
            }
        }
        return -1;
    }

    public  void chooseFunctionStudentView(CommonView commonView , Scanner scanner,int studentIndex){

        commonView.showMessage("1.View Profile");
        commonView.showMessage("2.Edit Profile");
        commonView.showMessage("3.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                commonView.showMessage("Wrong number , try again please:");
                selection = commonView.inputString(scanner);
            } else {
                break;
            }
        }
        int userIndex=userServiceIMPL.findUserById(userServiceIMPL.findStudentById(studentIndex).getUserId());
        switch (Integer.parseInt(selection)){
            case 1:commonView.displayObject( userServiceIMPL.findStudentById(studentIndex));break;
            case 2:chooseEditUserSelection(commonView,scanner,userIndex); break;
            case 3: return;
        }

        chooseFunctionStudentView(commonView,scanner,studentIndex);
    }
    public  void chooseFunctionTeacherView(CommonView commonView , Scanner scanner,int techerIndex){
        commonView.showMessage("============= Profile Teacher Form================");
        commonView.showMessage("1.View Profile");
        commonView.showMessage("2.Edit Profile");
        commonView.showMessage("3.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                commonView.showMessage("Wrong number , try again please:");
                selection = commonView.inputString(scanner);
            } else {
                break;
            }
        }
        int userIndex=userServiceIMPL.findUserById(userServiceIMPL.findTeacherById(techerIndex).getUserId());

        switch (Integer.parseInt(selection)){
            case 1:commonView.displayObject( userServiceIMPL.findTeacherById(techerIndex));break;
            case 2:chooseEditUserSelection(commonView,scanner,userIndex); break;
            case 3: return;
        }
        chooseFunctionTeacherView(commonView,scanner,techerIndex);
    }
}
