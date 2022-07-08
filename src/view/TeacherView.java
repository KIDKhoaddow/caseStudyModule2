package view;

import controller.UserController;
import view.Common.CommonView;

import java.util.Scanner;

public class TeacherView {
    UserController userController=new UserController();
    public TeacherView(CommonView commonView, Scanner scanner) {
        int teacherIndex =userController.loginTeacherView(commonView,scanner);
        if(teacherIndex<0){
            return;
        }
        userController.chooseFunctionTeacherView(commonView,scanner,teacherIndex);

    }
}
