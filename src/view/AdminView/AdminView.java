package view.AdminView;

import controller.MainController;
import view.Common.CommonView;

import java.util.Scanner;

public class AdminView {
    MainController mainController=new MainController();
    public AdminView(CommonView commonView, Scanner scanner) {
        new LoginAdminView(commonView,scanner);
        mainController.chooseFunction(commonView,scanner);
    }




}
