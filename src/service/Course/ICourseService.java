package service.Course;

import model.Course.Course;
import model.Course.CourseType;
import service.IGenericService;

import java.util.ArrayList;

public interface ICourseService extends IGenericService<Course> {
    boolean existedCourseName(String courseName);
    boolean existedCourseId(String courseId);
    int findCourseById(String id);
    Course findCourseByName(String courseName);

    Course editCourseName(int index, String courseName);

    Course editCoursePrice(int index, double coursePrice);
    Course editCourseType(int index , CourseType courseType);

    void saveCourseList();

    String getCourseName(int indexCourse);

    double getCoursePrice(int index);


}
