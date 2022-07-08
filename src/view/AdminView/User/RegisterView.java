package view.AdminView.User;

import controller.UserController;
import model.User.Gender;
import view.Common.CommonView;
import java.util.Scanner;


public class RegisterView {
    UserController userController = new UserController();

    public RegisterView(CommonView commonView, Scanner scanner) {
        commonView.showMessage("============ REGISTER FORM ============");
        String name = commonView.inputString("Enter your full name : ", scanner);
        String userName = userController.addUserName(commonView, scanner);
        String userPassword = userController.addPassword(commonView, scanner);
        String userEmail = userController.addUserEmail(commonView, scanner);
        int userAge = userController.addUserAge(commonView, scanner);
        String userPhoneNumber =userController.addUserPhone(commonView,scanner);
        String userAddress = commonView.inputString("Enter Your Address : ", scanner);
        Gender userGender = userController.addUserGender(commonView, scanner);
        userController.selectRole(commonView, scanner, name, userName, userPassword,
                userEmail, userAge, userPhoneNumber, userAddress, userGender);

    }


}

