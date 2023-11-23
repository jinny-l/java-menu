package menu.controller;

import java.util.List;
import java.util.Map;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.MenuCategory;
import menu.service.RecommendService;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommenderController {

    private final RecommendService recommendService;

    public RecommenderController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    public void run() {
        OutputView.printStartMessage();

        List<String> names = readNames();


        Coaches coaches = toCoaches(names);

        readAndSetBannedMenu(names, coaches);

        recommend(coaches);
        printRecommendation();

        OutputView.printEndMessage();
    }

    private List<String> readNames() {
//        try {
            return InputView.readNames();
//        } catch (IllegalArgumentException e) {
//            OutputView.printError(e);
//            return readNames();
//        }
    }

    private Coaches toCoaches(List<String> names) {
//        try {
            return Coaches.from(names);
//        } catch (IllegalArgumentException e) {
//            OutputView.printError(e);
//            return toCoaches(readNames());
//        }
    }

    private void readAndSetBannedMenu(List<String> names, Coaches coaches) {
//        try {
//            for (String coachName : names) {
//
//                List<String> menuNames = InputView.readBannedMenu(coachName);
//                List<Menu> menus = recommendService.findMenusByName(menuNames);
//
//                coaches.setBannedMenuByName(coachName, menus);
//            }
//        } catch (IllegalArgumentException e) {
//            OutputView.printError(e);
//            readAndSetBannedMenu(names, coaches);
//        }
    }

    private void recommend(Coaches coaches) {
        List<Coach> coachList = coaches.getCoaches();

        for (int i = 0; i < 5; i++) {
            MenuCategory menuCategory = recommendService.recommendMenuCategory();

            for (int j = 0; j < coachList.size(); j++) {
                recommendService.recommendMenuByCategory(coachList.get(j), menuCategory);
            }
        }
    }

    private void printRecommendation() {
        List<String> categories = recommendService.getCategories();
        Map<String, List<String>> menus = recommendService.getCoachesAndMenus();

        OutputView.printRecommendation(categories, menus);
    }
}
