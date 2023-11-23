package menu.service;

import java.util.Collections;
import java.util.List;
import menu.config.RecommendConfig;
import menu.domain.Menu;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.util.NumberGenerator;

public class RecommendService {

    private final MenuRepository menuRepository;
    private final NumberGenerator numberGenerator;

    public RecommendService(MenuRepository menuRepository, NumberGenerator numberGenerator) {
        this.menuRepository = menuRepository;
        this.numberGenerator = numberGenerator;
    }

    public List<Menu> findMenusByName(List<String> menuNames) {
        if (menuNames.isEmpty()) {
            return Collections.emptyList();
        }
        return menuRepository.findAllByName(menuNames);
    }

    public List<String> recommendMenuCategory() {
        List<Integer> randomNumbers = numberGenerator.generate(
                RecommendConfig.CATEGORY_SIZE,
                RecommendConfig.CATEGORY_MIN_VALUE,
                RecommendConfig.CATEGORY_MAX_VALUE,
                RecommendConfig.CATEGORY_MAX_DUPLICATE_SIZE
        );

        // TODO 검증 로직

        List<MenuCategory> menuCategories = MenuCategory.from(randomNumbers);

        return menuCategories.stream()
                .map(MenuCategory::getName)
                .toList();
    }
}
