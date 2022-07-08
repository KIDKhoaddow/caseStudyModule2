package controller;

import model.Course.Course;
import model.Course.CourseType;
import service.Course.CourseServiceIMPL;
import view.AdminView.Course.AddCourseView;
import view.AdminView.Course.DeleteCouresView;
import view.AdminView.Course.EditCourseView;
import view.Common.CommonView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CourseController {
    CourseServiceIMPL courseServiceIMPL = new CourseServiceIMPL();

    public void chooseFunction(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ COURSE FORM ============");
        commonView.showMessage("1.Add Course");
        commonView.showMessage("2.Edit Course");
        commonView.showMessage("3.Delete Course");
        commonView.showMessage("4.Display List Course");
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
                new AddCourseView(commonView, scanner);
                break;
            case 2:
                new EditCourseView(commonView, scanner);
                break;
            case 3:
                new DeleteCouresView(commonView, scanner);
                break;
            case 4:
                displayCourseList(commonView);
                break;
            case 5:
                return;
        }
        chooseFunction(commonView,scanner);

    }

    public void saveNewCourse(CommonView commonView, Scanner scanner, String courseName, CourseType courseType, double coursePrice) {
        Course course = new Course(courseName, coursePrice, courseType);
        courseServiceIMPL.save(course);
        commonView.displayObject(course);
        commonView.showMessage("Add Course Complete !");
    }

    public double addCoursePrice(CommonView commonView, Scanner scanner) {
        String price = commonView.inputString("Enter the price you want : ", scanner);
        while (true) {
            if (!checkCoursePrice(price)) {
                price = commonView.inputString("Wrong number , try again please :", scanner);
            } else {
                return Double.parseDouble(price);
            }
        }
    }

    public CourseType addCourseType(CommonView commonView, Scanner scanner) {
        commonView.showMessage("Type :");
        commonView.showMessage("1." + CourseType.BEGINNER);
        commonView.showMessage("2." + CourseType.FOUNDATION);
        commonView.showMessage("3." + CourseType.PRACTICE);
        String selection = commonView.inputString("Choose the type you want to create ", scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                selection = commonView.inputString("Wrong number ,try again please :", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                return CourseType.BEGINNER;
            case 2:
                return CourseType.FOUNDATION;
            case 3:
                return CourseType.PRACTICE;

        }
        return null;
    }

    public String addCourseName(CommonView commonView, Scanner scanner, CourseType type) {
        commonView.showMessage("Course+Type+Serial");
        commonView.showMessage("Type : " + CourseType.BEGINNER + "," + CourseType.FOUNDATION + "," + CourseType.PRACTICE);
        int id;
        try {
            Course course = courseServiceIMPL.findLastCourseByType(type);
            id = Integer.parseInt(course.getCourseName().substring(course.getCourseName().indexOf("-")));
        } catch (Exception e) {
            id = 0;
        }
        String courseName = "Course-" + type + "-" + (++id);
        commonView.showMessage(courseName);
//        while (true) {
//            if (checkCourseName(courseName)) {
//                courseName = commonView.inputString("The Course Name has Wrong , try again please :", scanner);
//            } else if (courseServiceIMPL.existedCourseName(courseName)) {
//                courseName = commonView.inputString("The Course Name has Existed , try again please :", scanner);
//            } else {
//                break;
//            }
//        }
        return courseName;
    }

    public void chooseFunctionEdit(CommonView commonView, Scanner scanner, int indexCourse) {
        commonView.showMessage("1.Edit Name ");
        commonView.showMessage("2.Edit Price");
        commonView.showMessage("3.Edit Type");
        commonView.showMessage("4.Back");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 4, selection)) {
                selection = commonView.inputString("Wrong number , try again please :", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                editCourseName(commonView, scanner, indexCourse);
                break;
            case 2:
                editCoursePrice(commonView, scanner, indexCourse);
                break;
            case 3:
                editCourseType(commonView, scanner, indexCourse);
                break;
            case 4:
               return;
        }
        chooseFunctionEdit(commonView, scanner, indexCourse);


    }

    public Course editCourseName(CommonView commonView, Scanner scanner, int indexCourse) {
        commonView.displayObject(courseServiceIMPL.findAll().get(indexCourse));
        commonView.showMessage("Course+Type+Serial");
        commonView.showMessage("Type : " + CourseType.BEGINNER + "," + CourseType.FOUNDATION + "," + CourseType.PRACTICE);

        String courseName = commonView.inputString("The CourseName you want to change is :", scanner);
        while (true) {
            if (courseServiceIMPL.existedCourseName(courseName)) {
                courseName = commonView.inputString("The CourseName has already existed, try again please :", scanner);
            } else if (checkCourseName(courseName)) {
                courseName = commonView.inputString("The CourseName has failed , try again please : ", scanner);
            } else {
                break;
            }
        }
        return courseServiceIMPL.editCourseName(indexCourse, courseName);
    }

    private boolean checkCourseName(String courseName) {
        return Pattern.matches("^(Course)+((?:Beginner)|(?:Foundation)|(?:Practice))", courseName);
    }

    public Course editCoursePrice(CommonView commonView, Scanner scanner, int indexCourse) {
        commonView.displayObject(courseServiceIMPL.findAll().get(indexCourse));
        String coursePrice = commonView.inputString("The Course Price you want to change is : ", scanner);
        while (true) {
            if (!checkCoursePrice(coursePrice)) {
                coursePrice = commonView.inputString("The Course Price has failed , try again please :", scanner);
            } else {
                break;
            }
        }
        return courseServiceIMPL.editCoursePrice(indexCourse, Double.parseDouble(coursePrice));
    }

    private boolean checkCoursePrice(String coursePrice) {
        return Pattern.matches("^(\\d+)+(.)+(\\d+)$", coursePrice);
    }



    private boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);

        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }

    public Course editCourseType(CommonView commonView, Scanner scanner, int index) {
        commonView.displayObject(courseServiceIMPL.findAll().get(index));
        commonView.showMessage("Please enter the number you want to choose Type you want to change :");
        commonView.showMessage("1.Beginner");
        commonView.showMessage("2.Foundation");
        commonView.showMessage("3.Practice");
        String selection = commonView.inputString(scanner);
        while (true) {
            if (!checkInputNumber(1, 3, selection)) {
                selection = commonView.inputString("The number has failed ,try again please :", scanner);
            } else {
                break;
            }
        }
        CourseType type = null;
        switch (Integer.parseInt(selection)) {
            case 1:
                type = CourseType.BEGINNER;
                break;
            case 2:
                type = CourseType.FOUNDATION;
                break;
            case 3:
                type = CourseType.PRACTICE;
                break;
        }
        return courseServiceIMPL.editCourseType(index, type);
    }

    public int findCourse(CommonView commonView, Scanner scanner) {
        String courseId = commonView.inputString("Please enter the Course Id you want to edit : ", scanner);
        while (true) {
            if (!courseServiceIMPL.existedCourseId(courseId)) {
                courseId = commonView.inputString("The course id has failed , try again please :", scanner);
            } else {
                break;
            }
        }
        return courseServiceIMPL.findCourseById(courseId);
    }


    public void displayCourseList(CommonView commonView) {
        ArrayList<Course> courseArrayList = (ArrayList<Course>) courseServiceIMPL.findAll();
        for (Course element : courseArrayList) {
            commonView.displayObject(element);
        }
    }

    public void removeCourse(CommonView commonView, int index) {
        ArrayList<Course> courseArrayList = (ArrayList<Course>) courseServiceIMPL.findAll();
        commonView.displayObject(courseArrayList.remove(index));
        commonView.showMessage("Delete complete !");
    }
}
