package view.AdminView.User.Edit;

import controller.UserController;
import model.User.Teacher;
import view.Common.CommonView;

import java.util.Scanner;

public class EditTeacher {
    UserController userController=new UserController();
    public  EditTeacher(CommonView commonView, Scanner scanner){
        int position= userController.findTeacher(commonView,scanner);
        Teacher teacher=userController.findTeacher(position);
        userController.displayTeacherOption(commonView,scanner,position);
        commonView.displayObject(teacher);
    }
}
