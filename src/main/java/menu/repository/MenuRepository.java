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
}
