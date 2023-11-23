package menu;

import menu.controller.RecommenderController;
import menu.repository.MenuRepository;
import menu.repository.RecommendCategoryRepository;
import menu.repository.RecommendMenuRepository;
import menu.service.RecommendService;
import menu.util.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {



        RecommenderController controller = new RecommenderController(
                new RecommendService(
                        new MenuRepository(),
                        new RecommendCategoryRepository(),
                        new RecommendMenuRepository(),
                        new RandomNumberGenerator())
        );
        controller.run();
    }
}
