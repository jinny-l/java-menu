package menu.controller;

import java.util.List;
import menu.domain.Coaches;
import menu.domain.Menu;
import menu.repository.MenuRepository;
import menu.view.InputView;
import menu.view.OutputView;

public class MainController {

    public void run() {
        Coaches coaches = readCoaches();
        readAndSetBannedMenus(coaches.getNames(), coaches);
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

}
