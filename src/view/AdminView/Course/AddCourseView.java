package view.AdminView.Course;

import controller.CourseController;
import model.Course.CourseType;
import view.Common.CommonView;

import java.util.Scanner;

public class AddCourseView {
    CourseController courseController=new CourseController();
    public AddCourseView(CommonView commonView, Scanner scanner){
        commonView.showMessage("============ ADD COURSE FORM ============");
        CourseType type= courseController.addCourseType(commonView,scanner);
        String name= courseController.addCourseName(commonView,scanner,type);
        double price=courseController.addCoursePrice(commonView,scanner);
        courseController.saveNewCourse(commonView,scanner,name,type,price);
    }
}
