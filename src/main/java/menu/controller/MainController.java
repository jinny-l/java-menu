package menu.controller;

import java.util.List;
import menu.domain.Coach;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.repository.RecommendedCategoryRepository;
import menu.repository.RecommendedMenuRepository;
import menu.service.RecommendService;
import menu.view.InputView;
import menu.view.OutputView;

public class MainController {

    private final RecommendService recommendService;

    public MainController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    public void run() {
        OutputView.printStartMessage();

        Coaches coaches = readCoaches();
        readAndSetBannedMenus(coaches.getNames(), coaches);
        recommendMenu(coaches.getCoaches());

        OutputView.printResultMessage(
                RecommendedCategoryRepository.findAll(),
                RecommendedMenuRepository.findAll()
        );
    }


    private Coaches readCoaches() {
        try {
            List<String> names = InputView.readNames();
            return Coaches.from(names);

        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readCoaches();
        }
    }

    private void readAndSetBannedMenus(List<String> names, Coaches coaches) {
        try {
            for (String name : names) {
                List<Menu> bannedMenu = MenuRepository.findMenusByNames(
                        InputView.readBannedMenus(name)
                );
                coaches.setBannedFoodBy(name, bannedMenu);
            }

        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            readAndSetBannedMenus(names, coaches);
        }
    }

    private void recommendMenu(List<Coach> coaches) {
        for (int i = 0; i < 5; i++) {
            MenuCategory menuCategory = recommendService.recommendDailyCategory();

            for (Coach coach : coaches) {
                recommendService.recommendDailyMenu(coach, menuCategory);
            }
        }
    }

}
