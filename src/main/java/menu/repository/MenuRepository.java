package menu.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import menu.domain.Menu;
import menu.domain.MenuCategory;
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
               .collect(Collectors.toList());
    }

    private Menu findByName(String menuNames) {
        return MENUS.stream()
                .filter(menu -> menu.isSameName(menuNames))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("메뉴에 없는 이름입니다."));
    }

    public List<Menu> findAllByCategory(MenuCategory menuCategory) {
        return MENUS.stream()
                .filter(menu -> menu.getMenuCategory() == menuCategory)
               .collect(Collectors.toList());
    }
}
