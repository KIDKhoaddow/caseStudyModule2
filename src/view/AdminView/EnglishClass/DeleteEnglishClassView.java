package view.AdminView.EnglishClass;

import controller.EnglishClassController;
import view.Common.CommonView;

import java.util.Scanner;

public class DeleteEnglishClassView {
    EnglishClassController classController = new EnglishClassController();

    public DeleteEnglishClassView(CommonView commonView, Scanner scanner) {
        int englishClassIndex = classController.findEnglishClassForDelete(commonView,scanner);
        classController.removeClass(commonView,englishClassIndex);
    }
}
