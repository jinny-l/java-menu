package menu.domain;

import java.util.List;

public class Coaches {

    private static final int COACH_MIN_SIZE = 2;
    private static final int COACH_MAX_SIZE = 5;

    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        validateLength(coaches);
        this.coaches = coaches;
    }

    public static Coaches from(List<String> names) {
        return new Coaches(
                names.stream()
                        .map(Coach::new)
                        .toList()
        );
    }

    private void validateLength(List<Coach> coaches) {
        if (coaches.size() < COACH_MIN_SIZE || coaches.size() > COACH_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("코치는 최소 %d명, 최대 %d명 입력해주세요.", COACH_MIN_SIZE, COACH_MAX_SIZE)
            );
        }
    }
}
