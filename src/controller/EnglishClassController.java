package controller;

import model.Class.EnglishClass;
import model.Class.EnglishClassStatus;
import model.Class.Shift;
import model.Course.Course;
import model.Room.Room;
import model.User.Teacher;
import service.Class.EnglishClassServiceIMPL;
import service.Course.CourseServiceIMPL;
import service.Room.RoomServiceIMPL;
import service.user.UserServiceIMPL;
import view.AdminView.EnglishClass.AddEnglishClassView;
import view.AdminView.EnglishClass.DeleteEnglishClassView;
import view.AdminView.EnglishClass.DisplayEnglishClassView;
import view.AdminView.EnglishClass.EditEnglishClassView;
import view.Common.CommonView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnglishClassController {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    EnglishClassServiceIMPL classServiceIMPL = new EnglishClassServiceIMPL();
    CourseServiceIMPL courseServiceIMPL = new CourseServiceIMPL();
    RoomServiceIMPL roomServiceIMPL = new RoomServiceIMPL();

    LocalDateTime localDateTime = LocalDateTime.now();

    public void chooseFunction(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ ENGLISH CLASS FORM ============");
        commonView.showMessage("1.Add English Class");
        commonView.showMessage("2.Edit English Class");
        commonView.showMessage("3.Delete English Class");
        commonView.showMessage("4.Display English Class");
        commonView.showMessage("5.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 5, selection)) {
                selection = commonView.inputString("Wrong number try again please ", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                new AddEnglishClassView(commonView, scanner);
                break;
            case 2:
                new EditEnglishClassView(commonView, scanner);
                break;
            case 3:
                new DeleteEnglishClassView(commonView, scanner);
                break;
            case 4:
                new DisplayEnglishClassView(commonView, scanner);
                break;
            case 5:
                return;
        }
        chooseFunction(commonView, scanner);
    }

    public Shift addEnglishClassShift(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Please Enter the number you want to choose shift:");
        commonView.showMessage("1. Shift 1(Monday-Wednesday-Friday)");
        commonView.showMessage("2. Shift 2(Tuesday-Thursday-Saturday");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 2, selection)) {
                selection = commonView.inputString("Wrong number , try again please:", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                return Shift.SHIFT1;
            case 2:
                return Shift.SHIFT2;
            default:
                return Shift.Null;
        }
    }

    public String addEnglishClassName(CommonView commonView, Scanner scanner, Shift shift, Course course) {
        commonView.showMessage("Class+Date+Shift+Course");
        String month = String.valueOf(localDateTime.getMonthValue());
        String year = String.valueOf(localDateTime.getYear()).substring(2);
        String className = "Class" + month + year + shift + course.getType();
        commonView.showMessage(className);
        return className;
    }

    public int addNumberSlot(CommonView commonView, Scanner scanner) {
        String numberSlot = commonView.inputString("Enter the number Slot you want to set :", scanner);
        while (true) {
            if (!checkNumber(numberSlot)) {
                numberSlot = commonView.inputString("Wrong number ,try again please : ", scanner);
            } else {
                break;
            }
        }
        return Integer.parseInt(numberSlot);
    }

    public Course addCourse(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Show List Course ");
        ArrayList<Course> courseList = (ArrayList<Course>) courseServiceIMPL.findAll();
        for (Course element : courseList) {
            commonView.showMessage(element.getCourseId() + ":" + element.getCourseName() + "," + element.getType() + "," + element.getCoursePrice());
        }

        String courseId = commonView.inputString("Enter Course Id you want to set :", scanner);
        while (true) {
            if (!checkCourseId(courseId)) {
                courseId = commonView.inputString("Wrong Course Id ,try again please :", scanner);
            } else {
                break;
            }
        }
        Course course = courseServiceIMPL.findById(courseId);
        return course;
    }

    public boolean checkCourseId(String courseId) {
        return Pattern.matches("^(CRSE)[\\d]+$", courseId);
    }

    public EnglishClass addEnglishClass(CommonView commonView, Scanner scanner, Shift shift, String className, int numberSlot, Course course) {
        EnglishClass englishClass = new EnglishClass(className, numberSlot, shift, course);
        classServiceIMPL.save(englishClass);
        return englishClass;
    }

    public boolean checkNumber(String number) {
        return Pattern.matches("^[0-9]+", number);
    }

    public void saveNewClass(CommonView commonView, Scanner scanner, String className, int numberSlot, Shift shift, Course course) {
        EnglishClass englishClass = new EnglishClass(className, numberSlot, shift, course);
        classServiceIMPL.save(englishClass);
        commonView.displayObject(englishClass);
        commonView.showMessage("Add English Class Complete !");

    }

    public int findEnglishClassForDelete(CommonView commonView, Scanner scanner) {
        commonView.showMessage("You just can be edit Class Prepare Status");
        ArrayList<EnglishClass> classList = (ArrayList<EnglishClass>) classServiceIMPL
                .findEnglishClassPrepare();
        for (EnglishClass element : classList) {
            commonView.displayObject(element);
        }
        String classId = commonView.inputString("Please enter the Class Id you want to edit : ", scanner);
        while (true) {
            if (!classServiceIMPL.existedClassById(classId)) {
                classId = commonView.inputString("The Class id has failed , try again please :", scanner);
            } else {
                break;
            }
        }
        return classServiceIMPL.findClassByIndex(classId);
    }

    private boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);

        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }
//    public  EnglishClass editTeacher(CommonView commonView , Scanner scanner){
//
//        commonView.showMessage("");
//    }

    public int findClass(CommonView commonView, Scanner scanner) {
        commonView.showMessage("You just can be edit Class Start and Prepare Status");
        commonView.showMessage("You can't be edit Class with End Status");
        ArrayList<EnglishClass> englishClassList = (ArrayList<EnglishClass>)
                classServiceIMPL.findEnglishClassStartAndPrepare();
        for (EnglishClass element : englishClassList) {
            commonView.displayObject(element);
        }

        String classId = commonView.inputString("Please enter the Class Id you want to edit : ", scanner);
        while (true) {
            if (!classServiceIMPL.existedClass(classId, englishClassList)) {
                classId = commonView.inputString("The Class id has failed , try again please :", scanner);
            } else {
                break;
            }
        }
        return classServiceIMPL.findClassByIndex(classId);
    }

    public void chooseFunctionEdit(CommonView commonView, Scanner scanner, int indexClass) {
        commonView.showMessage("==================Edit Class Form ============");
        commonView.showMessage("1.Edit Teacher ");
        commonView.showMessage("2.Edit Course");
        commonView.showMessage("3.Edit Room");
        commonView.showMessage("4.Edit Number Slot");
        commonView.showMessage("5.Edit Status");
        commonView.showMessage("6.Back");
        String selection = commonView.inputString("Enter the number you want to choose :", scanner);
        while (true) {
            if (!checkInputNumber(1, 6, selection)) {
                selection = commonView.inputString("Wrong number , try again please :", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                commonView.displayObject(editTeacher(commonView, scanner, indexClass));
                break;
            case 2:
                commonView.displayObject(editCourse(commonView, scanner, indexClass));
                break;
            case 3:
                commonView.displayObject(editRoom(commonView, scanner, indexClass));
                break;
            case 4:
                commonView.displayObject(editNumberSlot(commonView, scanner, indexClass));
                break;
            case 5:
                commonView.displayObject(editStatus(commonView, scanner, indexClass));
                break;
            case 6:
                return;
        }
        chooseFunctionEdit(commonView, scanner, indexClass);
    }

//    public EnglishClass editName(CommonView commonView, Scanner scanner, int indexClass) {
//        EnglishClass englishClass = classServiceIMPL.findAll().get(indexClass);
//        commonView.displayObject(englishClass);
//        String className = englishClass.getClassName();
//        Shift shift = englishClass.getShift();
//        Shift shiftToDo = shift == Shift.SHIFT1 ? Shift.SHIFT2 : Shift.SHIFT1;
//        String newClassName = className.substring(0, 9) + shiftToDo;
//        if (isEditClassName(newClassName)) {
//
//            commonView.showMessage("Your class can only change to " + shiftToDo);
//            commonView.showMessage("1.Yes");
//            commonView.showMessage("2.No");
//            String selection = commonView.inputString(scanner);
//            while (true) {
//                if (!checkInputNumber(1, 2, selection)) {
//                    selection = commonView.inputString("Wrong number , try again please: ", scanner);
//                } else {
//                    break;
//                }
//            }
//            switch (Integer.parseInt(selection)) {
//                case 1:
//                    classServiceIMPL.editClassName(indexClass, newClassName);
//                    break;
//                case 2:
//                    return classServiceIMPL.findClassByIndex(indexClass);
//            }
//        } else {
//            commonView.showMessage("Your Class Name can not be change ! ");
//            return classServiceIMPL.findClassByIndex(indexClass);
//        }
//        return classServiceIMPL.findClassByIndex(indexClass);
//
//    }
//
//    public boolean isEditClassName(String newClassName) {
//        return !classServiceIMPL.existedClassByName(newClassName);
//    }

    public EnglishClass editTeacher(CommonView commonView, Scanner scanner, int indexClass) {
        commonView.showMessage("You can only change teachers if the teacher still has a shift so" +
                " that they can teach at the same time as the class");
        ArrayList<Teacher> teacherList = (ArrayList<Teacher>) userServiceIMPL.findUserTeacherAvailable();
        Shift classShift = classServiceIMPL.getClassShift(indexClass);
        teacherList = (ArrayList<Teacher>) teacherList.stream()
                .filter(teacher -> teacher.checkShiftCanAdd(classShift))
                .collect(Collectors.toList());
        for (Teacher element : teacherList) {
            commonView.displayObject(element.getTeacherId());
        }

//        if(teacherList.size()==0&&){
//            commonView.showMessage("There no Teacher for edit ! ");
//            return null;
//        }
        if (teacherList.size() == 0) {
            commonView.showMessage("There are no Teacher available !");
            return null;
        }

        String teacherId = commonView.inputString("Enter Teacher Id you want to change :", scanner);
        int teacherIndex = userServiceIMPL.findTeacherById(teacherId, teacherList);
        int errorIndex = -1;
        while (true) {
            if (teacherIndex == errorIndex) {
                teacherId = commonView.inputString("Teacher Id failed ,try again please : ", scanner);
            } else {
                break;
            }
        }
        classServiceIMPL.editTeacher(indexClass, teacherList.get(teacherIndex));
        commonView.showMessage("Change Teacher success!");
        return classServiceIMPL.findClassByIndex(indexClass);
    }

    public EnglishClass editCourse(CommonView commonView, Scanner scanner, int indexClass) {
        ArrayList<Course> courseList = (ArrayList<Course>) courseServiceIMPL.findAll();
        for (Course element : courseList) {
            commonView.displayObject(element);
        }
        String courseID = commonView.inputString("Enter Course Id you want to change: ", scanner);
        int courseIndex = courseServiceIMPL.findCourseById(courseID);
        while (true) {
            if (courseIndex == (-1)) {
                courseID = commonView.inputString("Wrong Course Id , try again please :", scanner);
                courseIndex = courseServiceIMPL.findCourseById(courseID);
            } else {
                break;
            }
        }
        commonView.showMessage("Edit Course complete");
        return classServiceIMPL.editCourse(indexClass, courseList.get(courseIndex));
    }

    public EnglishClass editRoom(CommonView commonView, Scanner scanner, int indexClass) {
        Shift shift = classServiceIMPL.getClassShift(indexClass);
        ArrayList<Room> roomList = roomServiceIMPL.findRoomAvailable(shift);
        for (Room element : roomList) {
            commonView.displayObject(element);
        }
        String roomId = commonView.inputString("Enter RoomId you want to change :", scanner);
        int roomIndex = roomServiceIMPL.findRoomById(roomId);
        while (true) {
            if (roomIndex == (-1)) {
                roomId = commonView.inputString("Wrong Room Id , try again please : ", scanner);
                roomIndex = courseServiceIMPL.findCourseById(roomId);
            } else {
                break;
            }
        }
            EnglishClass englishClass = classServiceIMPL.findClassByIndex(indexClass);
            Room room=roomServiceIMPL.findRoomById(roomIndex);
            roomServiceIMPL.editClassForRoom(roomIndex,englishClass);
            classServiceIMPL.editRoom(indexClass, room);

            commonView.showMessage("Edit Room complete ");
            return englishClass;
        }

//    public EnglishClass editShift(CommonView commonView, Scanner scanner, int indexClass) {
//
//
//        return null;
//    }

        public EnglishClass editNumberSlot (CommonView commonView, Scanner scanner,int indexClass){
            commonView.showMessage("You can only change class if the class has status start and prepare");
            ArrayList<EnglishClass> englishClassList = (ArrayList<EnglishClass>) classServiceIMPL.findEnglishClassStartAndPrepare();
            for (EnglishClass element : englishClassList) {
                commonView.displayObject(element);
            }
            int numberStudent = classServiceIMPL.getNumberStudent(indexClass);
            String numberSlot = commonView.inputString("Enter Number slot you want to change : ", scanner);

            while (true) {
                if (checkNumber(numberSlot)) {
                    numberSlot = commonView.inputString("Wrong number , try again please : ", scanner);
                } else {
                    if (Integer.parseInt(numberSlot) < numberStudent) {
                        numberSlot = commonView.inputString("Number student is more than number slot you change ,try again please :", scanner);
                    } else {
                        break;
                    }
                }
            }
            classServiceIMPL.editNumberSlot(indexClass, Integer.parseInt(numberSlot));
            return classServiceIMPL.findClassByIndex(indexClass);
        }


        public EnglishClass editStatus (CommonView commonView, Scanner scanner,int indexClass){
            commonView.showMessage("You can only change Status from PREPARE to START");
            commonView.showMessage("1.Yes");
            commonView.showMessage("2.No");
            String selection = commonView.inputString("Enter number you want to choose :", scanner);
            while (true) {
                if (!checkInputNumber(1, 2, selection)) {
                    selection = commonView.inputString("Wrong number , try again please :", scanner);
                } else {
                    break;
                }
            }
            switch (Integer.parseInt(selection)) {
                case 1:
                    classServiceIMPL.editStatus(indexClass, EnglishClassStatus.START);
                    break;
                case 2:
                    break;
            }
            return classServiceIMPL.findClassByIndex(indexClass);
        }

        public void removeClass (CommonView commonView,int index){
            ArrayList<EnglishClass> classList = (ArrayList<EnglishClass>) classServiceIMPL.findAll();
            commonView.displayObject(classList.remove(index));
            commonView.showMessage("Delete complete !");
        }

        public void displayEnlishClass (CommonView commonView){
            for (EnglishClass element : classServiceIMPL.findAll()) {
                commonView.displayObject(element);
            }
        }

        public void displayEnglishClassEnd (CommonView commonView){
            for (EnglishClass element : classServiceIMPL.findEnglishClassEnd()) {
                commonView.displayObject(element);
            }
        }

        public void displayEnglishClassStart (CommonView commonView){
            for (EnglishClass element : classServiceIMPL.findEnglishClassStart()) {
                commonView.displayObject(element);
            }
        }

        public void displayEnglishClassPrepare (CommonView commonView){
            for (EnglishClass element : classServiceIMPL.findEnglishClassPrepare()) {
                commonView.displayObject(element);
            }
        }

        public void chooseFunctionViewEnglishClass (CommonView commonView, Scanner scanner){
            commonView.showMessage("===============View Class Form==================");
            commonView.showMessage("1.View List Class End");
            commonView.showMessage("2.View List Class Start ");
            commonView.showMessage("3.View List Class Prepare");
            commonView.showMessage("4.View List Class ");
            commonView.showMessage("5.Back");
            String selection = commonView.inputString(scanner);
            while (true) {
                if (!checkInputNumber(1, 5, selection)) {
                    selection = commonView.inputString("Wrong number try again please ", scanner);
                } else {
                    break;
                }
            }
            switch (Integer.parseInt(selection)) {
                case 1:
                    displayEnglishClassEnd(commonView);
                    break;
                case 2:
                    displayEnglishClassStart(commonView);
                    break;
                case 3:
                    displayEnglishClassPrepare(commonView);
                    break;
                case 4:
                    displayEnlishClass(commonView);
                    break;
                case 5:
                    return;
            }
            chooseFunctionViewEnglishClass(commonView, scanner);
        }

    }
