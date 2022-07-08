package model.Course;


import service.Course.CourseServiceIMPL;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String courseId;
    private String courseName ;
    private double coursePrice;
    private CourseType type;

    public Course() {
    }

    public Course(String courseName, double coursePrice, CourseType type) {
        try {
            id = Integer.parseInt(CourseServiceIMPL.courseList.get(CourseServiceIMPL.courseList.size() - 1)
                    .getCourseId().substring(4));
        } catch (Exception e) {
            id = 0;
        }
        this.courseId = "CRSE" + String.valueOf(++id);
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.type = type;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
       return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;

    }

    public double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(double coursePrice) {
        this.coursePrice = coursePrice;
    }


    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Course{" +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                ", type=" + type +
                '}';
    }
}
