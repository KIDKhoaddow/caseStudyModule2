package view.AdminView.EnglishClass;

import controller.EnglishClassController;
import view.Common.CommonView;

import java.util.Scanner;

public class DisplayEnglishClassView {
    EnglishClassController englishClassController=new EnglishClassController();
   public DisplayEnglishClassView(CommonView commonView , Scanner scanner){
        englishClassController.chooseFunctionViewEnglishClass(commonView,scanner);
    }
}
