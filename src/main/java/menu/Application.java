package menu;

import menu.controller.MainController;
import menu.service.RecommendService;

public class Application {
    public static void main(String[] args) {
        MainController controller = new MainController(RecommendService.getInstance());
        controller.run();
    }
}
