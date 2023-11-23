package menu.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.config.RecommendConfig;
import menu.domain.Coach;
import menu.domain.Menu;
import menu.domain.MenuCategory;
import menu.repository.MenuRepository;
import menu.repository.RecommendCategoryRepository;
import menu.repository.RecommendMenuRepository;
import menu.util.NumberGenerator;

public class RecommendService {

    private final MenuRepository menuRepository;
    private final RecommendCategoryRepository recommendCategoryRepository;
    private final RecommendMenuRepository recommendMenuRepository;
    private final NumberGenerator numberGenerator;

    public RecommendService(MenuRepository menuRepository, RecommendCategoryRepository recommendCategoryRepository,
                            RecommendMenuRepository recommendMenuRepository, NumberGenerator numberGenerator) {
        this.menuRepository = menuRepository;
        this.recommendCategoryRepository = recommendCategoryRepository;
        this.recommendMenuRepository = recommendMenuRepository;
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

    public String recommendMenuByCategory(Coach coach, MenuCategory menuCategory) {
        String coachName = coach.getName();
        List<Menu> bannedMenu = coach.getBannedMenu();
        String menu;

        do {
            menu = getRandomMenuBy(menuCategory);
        } while (!bannedMenu.contains(menu) && !recommendMenuRepository.existsBy(coachName, menu));

        recommendMenuRepository.put(coachName, menu);
        return menu;
    }

    public String getRandomMenuBy(MenuCategory menuCategory) {
        List<String> menus = menuRepository.findAllByCategory(menuCategory).stream()
                .map(Menu::getName)
                .collect(Collectors.toList());



        Collections.shuffle(menus);
        return menus.get(0);
    }

    public List<String> getCategories() {
        return recommendCategoryRepository.getCategories().stream()
                .map(MenuCategory::getName)
               .collect(Collectors.toList());
    }

    public Map<String, List<String>> getCoachesAndMenus() {
        return recommendMenuRepository.getMenus();
    }

}
