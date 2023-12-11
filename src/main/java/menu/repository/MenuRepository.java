package menu.repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import menu.domain.Menu;
import menu.util.FileReader;

public class MenuRepository {

    public static final List<Menu> MENUS = new ArrayList<>();

    static {
        List<String> lines = FileReader.read("src/main/resources/menu.csv");

        MENUS.addAll(
                Menu.parseFromCsvFile(lines)
        );
    }

    public static List<Menu> findMenusByNames(List<String> names) {
        if (names.size() == 1 && Objects.equals(names.get(0), "")) {
            return Collections.emptyList();
        }

        return names.stream()
                .map(MenuRepository::findByName)
                .toList();
    }

    private static Menu findByName(String name) {
        return MENUS.stream()
                .filter(menu -> Objects.equals(menu.getName(), name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 이름입니다."));
    }
}
