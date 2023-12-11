package menu.domain;

import java.util.List;

public class Menu {

    private static final int CATEGORY_INDEX = 0;
    private static final int NAME_INDEX = 1;

    private final MenuCategory category;
    private final String name;

    public Menu(MenuCategory category, String name) {
        this.category = category;
        this.name = name;
    }

    public static List<Menu> parseFromCsvFile(List<String> lines) {
        return lines.stream()
                .map(Menu::parseMenu)
                .toList();
    }

    private static Menu parseMenu(String line) {
        String[] values = line.split(",");

        return new Menu(
                MenuCategory.from(values[CATEGORY_INDEX]),
                values[NAME_INDEX]
        );
    }

    public MenuCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }
}
