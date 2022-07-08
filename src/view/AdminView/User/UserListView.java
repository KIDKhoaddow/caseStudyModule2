package view.AdminView.User;

import controller.UserController;
import view.Common.CommonView;

import java.util.Scanner;

public class UserListView {
    UserController userController = new UserController();

    public UserListView(CommonView commonView, Scanner scanner) {
        userController.chooseFunction(commonView, scanner);
    }


}
