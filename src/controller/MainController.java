package controller;

import view.AdminView.Course.CourseView;
import view.AdminView.EnglishClass.EnglishClassView;
import view.AdminView.Room.RoomView;
import view.AdminView.User.UserListView;
import view.Common.CommonView;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MainController {

   public void chooseFunction (CommonView commonView , Scanner scanner){
        commonView.showMessage("============ Function ============");
        commonView.showMessage("1. User list ");
        commonView.showMessage("2. Room list");
        commonView.showMessage("3. Course list");
        commonView.showMessage("4. Receipt list");
        commonView.showMessage("5. English Class List");
        commonView.showMessage("6. Back");
        String selection= commonView.inputString(scanner);
        while(true){
            if(checkInputNumber(1,6,selection)){
                break;
            }else {
                commonView.showMessage("Wrong number , chose again please ");
                selection= commonView.inputString(scanner);
            }
        }
        switch (Integer.parseInt(selection)){
            case 1: new UserListView(commonView,scanner); break;
            case 2: new RoomView(commonView,scanner);break;
            case 3: new CourseView(commonView,scanner);break;
            case 4:
            case 5: new EnglishClassView(commonView,scanner);break;
            case 6:return;
        }
       chooseFunction(commonView,scanner);
    }
    private boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);

        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }

    public void  loginAdmin(CommonView commonView, Scanner scanner){
        commonView.showMessage("============ LOGIN FORM ============");
        String name= commonView.inputString("Enter your Name :",scanner);
        String password= commonView.inputString("Enter your Password :",scanner);
        if(name.equals("Admin")&&password.equals("Admin")){
            commonView.showMessage("Login Success");
        }else {
            commonView.showMessage("Login failed ! Please check your Name or Pasword!");
           loginAdmin(commonView,scanner);
        }
    }
}
