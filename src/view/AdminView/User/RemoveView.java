package view.AdminView.User;

import model.User.User;
import service.user.UserServiceIMPL;
import view.Common.CommonView;

import java.util.Scanner;

public class RemoveView {
    UserServiceIMPL userServiceIMPL=new UserServiceIMPL();
     CommonView commonView=new CommonView();


    RemoveView(CommonView commonView, Scanner scanner){
        userServiceIMPL.displayALl();
        commonView.showMessage("============ REMOVE FORM ============");
        commonView.showMessage("Step 1 : Choose User You Want To Remove :");
        String userId= commonView.inputString("Enter User Id",scanner);
        User user =userServiceIMPL.findById(userId);
        while(true){
            if(user==null){

            }
        }
    }
}
