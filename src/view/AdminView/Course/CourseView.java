package view.AdminView.Course;

import controller.CourseController;
import view.Common.CommonView;

import java.util.Scanner;

public class CourseView {
    CourseController courseController = new CourseController();

    public CourseView(CommonView commonView, Scanner scanner) {
        courseController.chooseFunction(commonView,scanner);
    }
}
