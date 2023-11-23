package menu.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("점심 메뉴 추천을 시작합니다");
    }

    public static void printRecommendation(List<String> categories, Map<String, List<String>> coachAndMenuNames) {
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        printCategories(categories);
        printCoachesAndMenus(coachAndMenuNames);
    }

    private static void printCategories(List<String> categories) {
        String output = categories.stream()
                .collect(Collectors.joining(" | ", "[ 카테고리 ", " ]"));

        System.out.println(output);
    }

    private static void printCoachesAndMenus(Map<String, List<String>> coachAndMenuNames) {
        String output = coachAndMenuNames.entrySet().stream()
                .map(entry -> "[ "
                                + entry.getKey()
                                + " | "
                                + String.join(" | ", entry.getValue()) + " ]")
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(output);
    }

    public static void printEndMessage() {
        System.out.println("추천을 완료했습니다.");
    }

    public static void printError(Exception e) {
        System.out.println("[ERROR] " + e.getMessage());
    }
}
