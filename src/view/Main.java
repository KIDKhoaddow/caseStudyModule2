package view;

import view.AdminView.AdminView;
import view.Common.CommonView;

import java.util.Scanner;

public class            Main {
    public  Main(){
        Scanner scanner=new Scanner(System.in);
        CommonView commonView=new CommonView();

        commonView.showMessage("WELCOM TO SCHOOL MANAGEMENT");
        commonView.showMessage("SELECT 1 TO ADMIN VIEW ");
        commonView.showMessage("SELECT 2 TO STUDENT VIEW ");
        commonView.showMessage("SELECT 3 TO TEACHER VIEW ");
        int selection = commonView.inputInt(scanner);
        switch (selection){
            case 1:
                new AdminView(commonView,scanner);
                break;
            case 2:
                new StudentView(commonView,scanner);
                break;
            case 3:
                new TeacherView(commonView,scanner);
                break;
        }
        new Main();
    }
    public static void main(String[] args) {
        new Main();

    }
}
