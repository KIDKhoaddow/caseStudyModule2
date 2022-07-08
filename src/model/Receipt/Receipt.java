package model.Receipt;

import model.Course.Course;
import model.User.Student;
import model.User.User;

import java.io.Serializable;

public class Receipt implements Serializable {
   private  int receiptId;
   private User receiptUser;
   private  Course receiptCourse;

   public Receipt() {
   }

   public Receipt(int receiptId, User receiptUser, Course receiptCourse) {
      this.receiptId = receiptId;
      this.receiptUser = receiptUser;
      this.receiptCourse = receiptCourse;
   }

   public int getReceiptId() {
      return receiptId;
   }

   public void setReceiptId(int receiptId) {
      this.receiptId = receiptId;
   }

   public User getReceiptUser() {
      return receiptUser;
   }

   public void setReceiptUser(User receiptUser) {
      this.receiptUser = receiptUser;
   }

   public Course getReceiptCourse() {
      return receiptCourse;
   }
   public double getCoursePrice(){
      return receiptCourse.getCoursePrice();
   }
   public String getUserName(){
      return receiptUser.getUserName();
   }



   @Override
   public String toString() {
      return "Receipt{" +
              "receiptId=" + receiptId +
              ", student=" + receiptUser.getUserName() +
              ", receiptCourseName='" + receiptCourse.getCourseName() + '\'' +
              ", receiptCoursePrice=" + receiptCourse.getCoursePrice() +
              '}';
   }
}
