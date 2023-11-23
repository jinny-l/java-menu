package menu.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecommendMenuRepository {

    private static final Map<String, List<String>> MENUS = new LinkedHashMap<>();

    public void put(String coachName, String menu) {
        List<String> existingList = MENUS.computeIfAbsent(coachName, k -> new ArrayList<>());
        existingList.add(menu);
    }

    public boolean existsBy(String coachName, String menu) {
        return MENUS.get(coachName)
                .contains(menu);
    }

    public Map<String, List<String>> getMenus() {
        return Collections.unmodifiableMap(MENUS);
    }
}
