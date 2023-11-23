package menu.service;

import java.util.Collections;
import java.util.List;
import menu.config.RecommendConfig;
import menu.domain.Menu;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.repository.RecommendCategoryRepository;
import menu.util.NumberGenerator;

public class RecommendService {

    private final MenuRepository menuRepository;
    private final RecommendCategoryRepository recommendCategoryRepository;
    private final NumberGenerator numberGenerator;

    public RecommendService(MenuRepository menuRepository, RecommendCategoryRepository recommendCategoryRepository,
                            NumberGenerator numberGenerator) {
        this.menuRepository = menuRepository;
        this.recommendCategoryRepository = recommendCategoryRepository;
        this.numberGenerator = numberGenerator;
    }

    public List<Menu> findMenusByName(List<String> menuNames) {
        if (menuNames.isEmpty()) {
            return Collections.emptyList();
        }
        return menuRepository.findAllByName(menuNames);
    }

    public MenuCategory recommendMenuCategory() {
        MenuCategory menuCategory;
        int count;

        do {
            menuCategory = getRandomCategory();
            count = recommendCategoryRepository.countBy(menuCategory);
        } while (count <= RecommendConfig.CATEGORY_MAX_DUPLICATE_SIZE);

        recommendCategoryRepository.add(menuCategory);
        return menuCategory;
    }

    private MenuCategory getRandomCategory() {
        int randomNumber = numberGenerator.generate(
                RecommendConfig.CATEGORY_MIN_VALUE,
                RecommendConfig.CATEGORY_MAX_VALUE
        );

        return MenuCategory.from(randomNumber);
    }
}
