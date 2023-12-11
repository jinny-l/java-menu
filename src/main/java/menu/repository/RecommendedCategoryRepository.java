package menu.repository;


import java.util.ArrayList;
import java.util.List;
import menu.domain.MenuCategory;

public class RecommendedCategoryRepository {

    public static final List<MenuCategory> CATEGORIES = new ArrayList<>();

    public static void add(MenuCategory menuCategory) {
        CATEGORIES.add(menuCategory);
    }

    public static int countBy(MenuCategory menuCategory) {
        return (int) CATEGORIES.stream()
                .filter(category -> category == menuCategory)
                .count();
    }
}
