package menu.domain;

import java.util.ArrayList;
import java.util.List;

public class Coach {

    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 4;
    private static final int BANNED_FOOD_MIN_SIZE = 0;
    private static final int BANNED_FOOD_MAX_SIZE = 2;

    private final String name;
    private List<Menu> bannedMenu;

    public Coach(String name) {
        validateName(name);
        this.name = name;
        bannedMenu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getBannedMenuNames() {
        return bannedMenu.stream()
                .map(Menu::getName)
                .toList();
    }

    public void setBannedMenu(List<Menu> bannedMenu) {
        this.bannedMenu = bannedMenu;
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름은 최소 %d글자, 최대 %d글자를 입력해주세요.", NAME_MIN_LENGTH, NAME_MAX_LENGTH)
            );
        }
    }

    private void validateBannedMenu(List<Menu> bannedMenu) {
        if (bannedMenu.size() > BANNED_FOOD_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("못 먹는 음식 최소 %d개, 최대 %d개를 입력해주세요.", BANNED_FOOD_MIN_SIZE, BANNED_FOOD_MAX_SIZE)
            );
        }
    }
}
