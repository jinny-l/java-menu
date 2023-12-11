package menu.controller;

import java.util.List;
import menu.domain.Coaches;
import menu.view.InputView;
import menu.view.OutputView;

public class MainController {

    public void run() {
        Coaches coaches = readCoaches();
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

}
