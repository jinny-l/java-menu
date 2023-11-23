package menu.repository;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;
import menu.util.CsvReader;

public class MenuRepository {

    private static final List<Menu> MENUS = new ArrayList<>();

    static {
        List<String> csvData = CsvReader.read("src/main/resources/menu.csv");
        MENUS.addAll(Menu.parseMenuFromCsv(csvData));
    }

    public List<Menu> findAllByName(List<String> menuNames) {
        return menuNames.stream()
                .map(this::findByName)
                .toList();
    }

    private Menu findByName(String menuNames) {
        return MENUS.stream()
                .filter(menu -> menu.isSameName(menuNames))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 이름입니다."));
    }
}
