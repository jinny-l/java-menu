package menu.repository;

import java.util.ArrayList;
import java.util.List;
import menu.domain.MenuCategory;

public class RecommendCategoryRepository {

    private static final List<MenuCategory> CATEGORIES = new ArrayList<>();

    public void add(MenuCategory menuCategory) {
        CATEGORIES.add(menuCategory);
    }

    public int countBy(MenuCategory menuCategory) {
        return (int) CATEGORIES.stream()
                .filter(category -> category == menuCategory)
                .count();
    }
}
