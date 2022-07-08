package view.AdminView.EnglishClass;

import controller.EnglishClassController;
import model.Class.Shift;
import model.Course.Course;
import view.Common.CommonView;

import java.util.Scanner;

public class AddEnglishClassView {
    EnglishClassController englishClassController=new EnglishClassController();
    public AddEnglishClassView(CommonView commonView , Scanner scanner){
        Shift classShift=englishClassController.addEnglishClassShift(commonView,scanner);
        Course course =englishClassController.addCourse(commonView,scanner);
        String className= englishClassController.addEnglishClassName(commonView,scanner,classShift,course);
        int classNumberSlot= englishClassController.addNumberSlot(commonView,scanner);
        englishClassController.saveNewClass(commonView,scanner,className,classNumberSlot,classShift,course);
    }
}
