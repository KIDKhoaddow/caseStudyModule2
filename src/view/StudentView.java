package view;

import controller.UserController;
import view.Common.CommonView;

import java.util.Scanner;

public class StudentView {
    UserController userController=new UserController();

    public StudentView(CommonView commonView, Scanner scanner) {
      int studentIndex =userController.loginStudentView(commonView,scanner);
      if(studentIndex<0){
          return;
      }
      userController.chooseFunctionStudentView(commonView,scanner,studentIndex);
    }
}
