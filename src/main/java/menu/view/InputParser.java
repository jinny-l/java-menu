package menu.view;

import java.util.List;
import java.util.stream.Stream;

public class InputParser {

    private InputParser() {
    }

    public static List<String> stringToList(String delimiter, String input) {
        validateDelimiter(delimiter, input);

        return Stream.of(input.split(delimiter))
                .toList();
    }

    private static void validateDelimiter(String delimiter, String input) {
        if (input.split(delimiter).length != 1 && !input.contains(delimiter)) {
            throw new IllegalArgumentException("잘못된 형식입니다.");
        }
    }
}
