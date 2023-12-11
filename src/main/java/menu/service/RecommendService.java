package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.domain.Coach;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.repository.RecommendedCategoryRepository;
import menu.repository.RecommendedMenuRepository;

public class RecommendService {

    private static final RecommendService INSTANCE = new RecommendService();

    private RecommendService() {
    }

    public static RecommendService getInstance() {
        return INSTANCE;
    }

    public void recommendDailyMenu(Coach coach, MenuCategory menuCategory) {
        List<String> bannedFood = coach.getBannedMenuNames();
        String menu;

        do {
            menu = recommendMenuBy(menuCategory);
        } while (bannedFood.contains(menu) || RecommendedMenuRepository.countBy(coach, menu) == 2);

        RecommendedMenuRepository.add(coach, menu);
    }

    public MenuCategory recommendDailyCategory() {
        MenuCategory menuCategory;
        int categoryCount;

        do {
            menuCategory = recommendCategory();
            categoryCount = RecommendedCategoryRepository.countBy(menuCategory);
        } while (categoryCount == 2);

        RecommendedCategoryRepository.add(menuCategory);
        return menuCategory;
    }

    private MenuCategory recommendCategory() {
        int number = Randoms.pickNumberInRange(1, 5);
        return MenuCategory.from(number);
    }

    private String recommendMenuBy(MenuCategory category) {
        List<String> menus = MenuRepository.findMenuNamesBy(category);
        List<String> randomMenus = Randoms.shuffle(menus);

        return randomMenus.get(0);
    }
}
