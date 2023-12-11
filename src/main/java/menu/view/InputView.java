package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {

    private static final String NAME_DELIMITER = ",";
    private static final String MENU_DELIMITER = ",";

    private InputView() {
    }

    public static List<String> readNames() {
        System.out.printf("코치의 이름을 입력해 주세요. (%s 로 구분)%n", NAME_DELIMITER);

        String input = readLine();
        validateBlank(input);

        return stripElement(
                InputParser.stringToList(NAME_DELIMITER, input)
        );
    }

    public static List<String> readBannedMenus(String coachName) {
        System.out.printf("%n%s(이)가 못 먹는 메뉴를 입력해 주세요.%n", coachName);

        String input = readLine();

        return stripElement(
                InputParser.stringToList(MENU_DELIMITER, input)
        );
    }

    private static String readLine() {
        return Console.readLine().strip();
    }

    private static void validateBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력 값이 없습니다.");
        }
    }

    private static List<String> stripElement(List<String> input) {
        return input.stream()
                .map(String::strip)
                .toList();
    }
}
