package menu.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Menu {

    private static final int MENU_CATEGORY_INDEX = 0;
    private static final int MENU_INDEX = 1;

    private final MenuCategory menuCategory;
    private final String name;

    private Menu(MenuCategory menuCategory, String name) {
        this.menuCategory = menuCategory;
        this.name = name;
    }

    public static List<Menu> parseMenuFromCsv(List<String> data) {
        return data.stream()
                .map(Menu::parseMenu)
               .collect(Collectors.toList());
    }

    private static Menu parseMenu(String line) {
        String[] values = line.split(",");
        return new Menu(
                MenuCategory.from(values[MENU_CATEGORY_INDEX]),
                values[MENU_INDEX]
        );
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public String getName() {
        return name;
    }

    public boolean isSameName(String name) {
        return Objects.equals(this.name, name);
    }
}
