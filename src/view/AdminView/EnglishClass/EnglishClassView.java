package view.AdminView.EnglishClass;

import controller.EnglishClassController;
import view.Common.CommonView;

import java.util.Scanner;

public class EnglishClassView {
    EnglishClassController englishClassController=new EnglishClassController();
   public EnglishClassView(CommonView commonView, Scanner scanner){
        englishClassController.chooseFunction(commonView,scanner);
    }
}
