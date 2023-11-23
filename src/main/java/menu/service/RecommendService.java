package menu.service;

import java.util.Collections;
import java.util.List;
import menu.domain.Menu;
import menu.repository.MenuRepository;

public class RecommendService {

    private final MenuRepository menuRepository;

    public RecommendService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> findMenusByName(List<String> menuNames) {
        if (menuNames.isEmpty()) {
            return Collections.emptyList();
        }
        return menuRepository.findAllByName(menuNames);
    }
}
