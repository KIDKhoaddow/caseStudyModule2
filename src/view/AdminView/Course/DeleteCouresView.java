package view.AdminView.Course;

import controller.CourseController;
import view.Common.CommonView;

import java.util.Scanner;

public class DeleteCouresView {
    CourseController courseController=new CourseController();
    public DeleteCouresView(CommonView commonView, Scanner scanner){
        int courseIndex= courseController.findCourse(commonView,scanner);
        courseController.removeCourse(commonView,courseIndex);
    }
}
