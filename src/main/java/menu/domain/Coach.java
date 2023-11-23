package menu.domain;

import java.util.List;
import java.util.Objects;

public class Coach {

    private final int NAME_MIN_SIZE = 2;
    private final int NAME_MAX_SIZE = 4;
    private final int BANNED_MENU_MAX_SIZE = 2;

    private final String name;
    private List<Menu> bannedMenu;

    public Coach(String name) {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getBannedMenu() {
        return bannedMenu;
    }

    public void setBannedMenu(List<Menu> bannedMenu) {
        validateBannedMenuSize(bannedMenu);
        this.bannedMenu = bannedMenu;
    }

    public boolean isSameName(String name) {
        return Objects.equals(this.name, name);
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_SIZE || name.length() > NAME_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("이름은 최소 %d글자, 최대 %d글자 입력 가능합니다.", NAME_MIN_SIZE, NAME_MAX_SIZE)
            );
        }
    }

    private void validateBannedMenuSize(List<Menu> bannedMenu) {
        if (bannedMenu.size() > BANNED_MENU_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("못 먹는 메뉴는 최대 %d개 입력 가능합니다.", BANNED_MENU_MAX_SIZE)
            );
        }
    }
}
