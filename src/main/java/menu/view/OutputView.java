package menu.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import menu.domain.Coach;
import menu.domain.MenuCategory;

public class OutputView {

    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printError(Exception e) {
        System.out.printf("%s%s%n", ERROR_MESSAGE_PREFIX, e.getMessage());
    }

    public static void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public static void printResultMessage(List<MenuCategory> menuCategories,
                                          Map<Coach, List<String>> recommendedMenus) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");

        System.out.println(formatCategory(menuCategories));
        System.out.println(formatRecommendedMenus(recommendedMenus));

        System.out.println();
        System.out.println("추천을 완료했습니다.");
    }

    private static String formatCategory(List<MenuCategory> menuCategories) {
        return menuCategories.stream()
                .map(MenuCategory::getName)
                .collect(Collectors.joining(" | ", "[ 카테고리 | ", " ]"));
    }

    private static String formatRecommendedMenus(Map<Coach, List<String>> recommendedMenus) {
        return recommendedMenus.entrySet().stream()
                .map(entry ->
                        "[ "
                                + entry.getKey().getName()
                                + " | "
                                + String.join(" | ", entry.getValue())
                                + " ]"
                )
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
