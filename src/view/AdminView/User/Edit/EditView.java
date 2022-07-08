package view.AdminView.User.Edit;

import controller.UserController;
import view.Common.CommonView;

import java.util.Scanner;

public class EditView {
    UserController userController = new UserController();

    public EditView(CommonView commonView, Scanner scanner) {
        userController.chooseFunctionEdit(commonView, scanner);
    }
}





