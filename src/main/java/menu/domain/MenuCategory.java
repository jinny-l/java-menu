package menu.domain;

import java.util.Arrays;
import java.util.List;

public enum MenuCategory {

    JAPANESE(1, "일식"),
    KOREAN(2, "한식"),
    CHINESE(3, "중식"),
    ASIAN(4, "아시안"),
    WESTERN(5, "양식");

    private final int number;
    private final String name;

    MenuCategory(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static MenuCategory from(String name) {
        return Arrays.stream(MenuCategory.values())
                .filter(menuCategory -> menuCategory.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 메뉴 이름이 없습니다."));
    }

    private static MenuCategory from(int number) {
        return Arrays.stream(MenuCategory.values())
                .filter(menuCategory -> menuCategory.number == number)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 메뉴 숫자가 없습니다."));
    }

    public static List<MenuCategory> from(List<Integer> numbers) {
        return numbers.stream()
                .map(MenuCategory::from)
                .toList();
    }

    public String getName() {
        return name;
    }
}
