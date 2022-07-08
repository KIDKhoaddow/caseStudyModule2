package service.Course;

import config.ConfigReadAndWriteFile;
import model.Course.Course;
import model.Course.CourseType;
import view.Common.CommonView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceIMPL implements ICourseService {
    public static String PATH_COURSE = ConfigReadAndWriteFile.PATH + "course.txt";
    public static List<Course> courseList = new ConfigReadAndWriteFile<Course>().readFromFile(PATH_COURSE);
    public CommonView commonView = new CommonView();


    @Override
    public void save(Course course) {
        courseList.add(course);
        saveCourseList();
    }

    @Override
    public void saveCourseList() {
        new ConfigReadAndWriteFile<Course>().writeToFile(PATH_COURSE, courseList);
    }

    @Override
    public void edit(int index, Course course) {
        courseList.set(index,course);
        saveCourseList();
    }


    @Override
    public Course editCourseName(int index, String courseName) {
        courseList.get(index).setCourseName(courseName);
        saveCourseList();
        return courseList.get(index);
    }

    @Override
    public Course editCoursePrice(int index, double coursePrice) {
        courseList.get(index).setCoursePrice(coursePrice);
        saveCourseList();
        return courseList.get(index);
    }

    @Override
    public Course editCourseType(int index, CourseType courseType) {
        courseList.get(index).setType(courseType);
        saveCourseList();
        return courseList.get(index);
    }

    @Override
    public boolean delete(String id, Course course) {
        return courseList.remove(findById(id));
    }

    @Override
    public List<Course> findAll() {
        return courseList;
    }

    @Override
    public Course findById(String id) {
        for (Course element:courseList) {
            if(element.getCourseId().equals(id)){
                return element;
            }
        }
        return  null;
    }
    public  Course findById(int index ){
        return  courseList.get(index);
    }

    @Override
    public int findCourseById(String id) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    public Course findCourseByName(String courseName){
        for (Course element:courseList) {
            if(element.getCourseName().equals(courseName)){
                return element;
            }
        }
        return null;
    }
    public Course findLastCourseByType(CourseType type){
        ArrayList<Course> typeCourseList =new ArrayList<>();

        switch(type){
            case PRACTICE: typeCourseList=findCoursePractice();break;
            case FOUNDATION:typeCourseList=findCourseFoundation();break;
            case BEGINNER:typeCourseList=findCourseBeginner();break;
        }
        return findLastCourse(typeCourseList);

    }

    @Override
    public void displayById(String id) {

    }
    public Course findLastCourse(ArrayList<Course> courseList){
        return courseList.get(courseList.size()-1);
    }


    public ArrayList<Course> findCourseBeginner(){
        return (ArrayList<Course>) courseList.stream().filter(course -> course.getType()!=CourseType.BEGINNER)
                .collect(Collectors.toList());
    }
    public  ArrayList<Course> findCourseFoundation(){
        return (ArrayList<Course>) courseList.stream().filter(course -> course.getType()!=CourseType.FOUNDATION)
                .collect(Collectors.toList());
    }
    public ArrayList<Course> findCoursePractice(){
        return (ArrayList<Course>) courseList.stream().filter(course -> course.getType()!=CourseType.PRACTICE)
                .collect(Collectors.toList());
    }


    @Override
    public boolean existedCourseName(String courseName) {
        for (Course element:courseList) {
           if( element.getCourseName().equals(courseName)){
               return true;
           }
        }
        return false;
    }

    @Override
    public boolean existedCourseId(String courseId) {
        for (Course element:courseList) {
            if( element.getCourseId().equals(courseId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getCourseName(int indexCourse) {
        return courseList.get(indexCourse).getCourseName();
    }

    @Override
    public double getCoursePrice(int index) {
        return courseList.get(index).getCoursePrice();
    }

    @Override
    public void displayALl() {
        for (Course element : courseList) {
            commonView.displayObject(element);
        }
    }

}
