package view.AdminView;

import controller.MainController;
import view.Common.CommonView;

import java.util.Scanner;

public class LoginAdminView {
    MainController  mainController=new MainController();
    public LoginAdminView(CommonView commonView, Scanner scanner) {
        mainController.loginAdmin(commonView,scanner);
    }

}
