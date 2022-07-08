package view.AdminView.Course;

import controller.CourseController;
import view.Common.CommonView;

import java.util.Scanner;

public class EditCourseView {
    CourseController courseController =new CourseController();
    public  EditCourseView(CommonView commonView, Scanner scanner){
        commonView.showMessage("============ EDIT COURSE FORM ============");
        int indexCourse= courseController.findCourse(commonView,scanner);
        courseController.chooseFunctionEdit(commonView,scanner,indexCourse);

    }
}
