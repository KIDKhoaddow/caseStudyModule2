package service.Class;

import model.Class.EnglishClass;
import model.Class.EnglishClassStatus;
import model.Class.Shift;
import model.Course.Course;
import model.Room.Room;
import model.User.Teacher;
import service.IGenericService;

import java.util.ArrayList;
import java.util.List;

public interface IEnglishClassService extends IGenericService<EnglishClass> {
    void identifiedTeacher(String id, String teacherName);
    boolean existedClassById(String classId);
    boolean existedClassByName(String className);
    String getClassName(int index);

    String getTeacherName(int index);

    String getCourseName(int index);

    String getRoomName(int index);
    Shift getClassShift(int index);
    ArrayList<EnglishClass> getEnglishClassAvailable(Course course);
    int getNumberSlot(int index);
    int getNumberStudent(int index);

    EnglishClass editTeacher(int index , Teacher teacher);
    EnglishClass editRoom(int index , Room room);
    EnglishClass editCourse(int index , Course course);
    EnglishClass editClassName(int index, String className);

    EnglishClass editTeacherName(int index, Teacher teacherName);

    EnglishClass editCourseName(int index, Course courseName);

    EnglishClass editRoomName(int index, Room roomName);
    EnglishClass editNumberSlot(int index , int numberSlot);
    EnglishClass editStatus(int index , EnglishClassStatus status);

    void saveClassList();

    boolean containsStudentFromClass(int indexClass, int indexStudent);


}
