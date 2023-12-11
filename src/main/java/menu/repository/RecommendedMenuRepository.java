package menu.repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import menu.domain.Coach;

public class RecommendedMenuRepository {

    public static final Map<Coach, List<String>> RECOMMENDED_MENU = new LinkedHashMap<>();

    public static void add(Coach coach, String menu) {
        List<String> menus = RECOMMENDED_MENU.getOrDefault(coach, new ArrayList<>());
        menus.add(menu);
        RECOMMENDED_MENU.put(coach, menus);
    }

    public static int countBy(Coach coach, String menu) {
        List<String> menus = RECOMMENDED_MENU.getOrDefault(coach, new ArrayList<>());
        return Collections.frequency(menus, menu);
    }

    public static Map<Coach, List<String>> findAll() {
        return Collections.unmodifiableMap(RECOMMENDED_MENU);
    }
}
