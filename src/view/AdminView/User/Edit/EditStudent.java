package view.AdminView.User.Edit;

import controller.UserController;
import model.User.Student;
import service.user.UserServiceIMPL;
import view.Common.CommonView;

import java.util.Scanner;

public class EditStudent {
    UserController userController = new UserController();

    public EditStudent(CommonView commonView, Scanner scanner) {
        int position = userController.findStudent(commonView, scanner);
        Student student = userController.findStudent(position);
        userController.displayStudentOption(commonView, scanner, position);
        commonView.displayObject(student);
    }

}
