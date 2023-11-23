package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String SPLIT_REGEX = ",";

    private InputView() {
    }

    public static List<String> readNames() {
        System.out.printf("%n코치의 이름을 입력해 주세요. (%s 로 구분)%n", SPLIT_REGEX);

        String input = Console.readLine();
//        validateHasInput(input);

        return Arrays.stream(input.split(",")).collect(Collectors.toList());
//        return stringToList(input);
    }

    public static List<String> readBannedMenu(String coachName) {
        System.out.printf("%n%s(이)가 못 먹는 메뉴를 입력해 주세요%n", coachName);

        String input = readLine();
        return Arrays.stream(input.split(",")).collect(Collectors.toList());
//        return stringToList(input);
    }

    private static String readLine() {
        return Console.readLine().strip();
    }

    private static boolean validateHasInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력 값이 비어있습니다.");
        }
        return true;
    }

    private static List<String> stringToList(String input) {
        if (input.equals("")) {
            return Collections.emptyList();
        }

        List<String> split = Stream.of(input.split(SPLIT_REGEX))
                .filter(InputView::validateHasInput)
                .map(String::strip)
               .collect(Collectors.toList());

        validateDelimiter(input, split);
        return split;
    }

    private static void validateDelimiter(String input, List<String> split) {
        if (split.size() > 1 && !input.contains(SPLIT_REGEX)) {
            throw new IllegalArgumentException(String.format("입력 값은 \"%s\"로 구분해주세요", SPLIT_REGEX));
        }
    }
}
