package menu.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.repository.RecommendedCategoryRepository;

public class RecommendService {

    private static final RecommendService INSTANCE = new RecommendService();

    private RecommendService() {
    }

    public static RecommendService getInstance() {
        return INSTANCE;
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
        Randoms.shuffle(menus);

        return menus.get(0);
    }
}
