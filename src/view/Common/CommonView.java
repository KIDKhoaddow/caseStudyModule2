package view.Common;

import model.User.Student;
import model.User.Teacher;
import model.User.User;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonView {
    public int inputInt(String string, Scanner scanner) {
        System.out.print(string);
        return scanner.nextInt();
    }

    public int inputInt(Scanner scanner) {
        return scanner.nextInt();
    }

    public String inputString(String string, Scanner scanner) {
        System.out.print(string);
        return scanner.next();
    }

    public String inputString(Scanner scanner) {
        return scanner.next();
    }

    public double inputDouble(String string, Scanner scanner) {
        System.out.println(string);
        return scanner.nextDouble();
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public boolean isInputString(String string) {
        return Pattern.matches("[a-z0-9_-]{6,}", string);
    }


    public void displayObject(Object user) {
        try {
            System.out.println(user);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void displayUserList(List<User> userList) {
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");
        System.out.format("| User ID |       Name      |    Username     |     Password    |  Role   | Age | Phone Number |      Address      |             Email         |%n");
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");

        for (User element : userList) {
            displayUser(element);
        }
    }

    public void displayUser(User user) {
        String leftAlignFormat = "| %-7s | %-15s | %-15s | %-15s | %-7s | %-3d | %-12s | %-17s | %-25s |%n";
        System.out.format(leftAlignFormat, user.getUserId(), user.getUserFullName(), user.getUserName(),
                user.getUserPassword(), user.getUserRole(), user.getUserAge(),
                user.getUserPhoneNumber(), user.getUserAddress(), user.getUserEmail());
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");
    }

    public void displayStudentList(List<Student> studentList) {
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+%n");
        System.out.format("| Student ID |       Name      |    Username     | Phone Number | Age |             Email         |    Course Name |    Class Name   |%n");
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+%n");
        for (Student element : studentList) {
            displayStudent(element);
        }

    }

    public void displayStudent(Student student) {
        String leftAlignFormat = "| %-10s | %-15s | %-15s | %-12s | %-3d | %-25s | %-14s | %-15s |%n";
        System.out.format(leftAlignFormat, student.getStudentId(), student.getUserFullName(), student.getUserName(),
                student.getUserPhoneNumber(), student.getUserAge(), student.getUserEmail(), student.getCourseName(), student.getClassName());
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+%n");

    }

    public void displayTeacherList(List<Teacher> teacherList) {
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+----------+%n");
        System.out.format("| Teacher ID |       Name      |    Username     | Phone Number | Age |             Email         |    Class 1     |    Class 2      |   Salary |%n");
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+----------+%n");
        for (Teacher element : teacherList) {
            displayTeacher(element);
        }

    }

    public void displayTeacher(Teacher teacher) {
        String leftAlignFormat = "| %-10s | %-15s | %-15s | %-12s | %-3d | %-25s | %-14s | %-15s | %-8f |-%n";
        System.out.format(leftAlignFormat, teacher.getTeacherId(), teacher.getUserFullName(), teacher.getUserName(),
                teacher.getUserPhoneNumber(),teacher.getUserAge(), teacher.getUserEmail(),
                teacher.getFirstClassName(), teacher.getSecondClassName(), teacher.getTeacherSalary());
        System.out.format("+------------+-----------------+-----------------+--------------+-----+---------------------------+----------------+-----------------+----------+%n");

    }
}
