package view.AdminView.EnglishClass;

import controller.EnglishClassController;
import view.Common.CommonView;

import java.util.Scanner;

public class EditEnglishClassView {
 EnglishClassController englishClassController=new EnglishClassController();
   public EditEnglishClassView(CommonView commonView , Scanner scanner){
       commonView.showMessage("-----------EDIT ENGLISH CLASS FORM -----------");
       int indexClass=englishClassController.findClass(commonView, scanner);
       englishClassController.chooseFunctionEdit(commonView,scanner,indexClass);

    }
}
