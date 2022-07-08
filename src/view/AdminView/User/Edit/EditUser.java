package view.AdminView.User.Edit;

import controller.UserController;
import service.user.UserServiceIMPL;
import view.Common.CommonView;

import java.util.Scanner;

public class EditUser {
    UserController userController = new UserController();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    public EditUser(CommonView commonView, Scanner scanner) {
        int userIndex = userController.findUser(commonView, scanner);
        userController.chooseEditUserSelection(commonView, scanner, userIndex);
    }
}
