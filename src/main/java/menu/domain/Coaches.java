package menu.domain;

import java.util.List;

public class Coaches {

    private static final int COACHES_MIN_SIZE = 2;
    private static final int COACHES_MAX_SIZE = 4;

    private final List<Coach> coaches;

    private Coaches(List<Coach> coaches) {
        validateCoachesSize(coaches);
        this.coaches = coaches;
    }

    public static Coaches from(List<String> name) {
        validateDuplicatedName(name);
        return new Coaches(name.stream()
                .map(Coach::new)
                .toList()
        );
    }

    public void setBannedMenuByName(String name, List<Menu> menus) {
        Coach findCoach = coaches.stream()
                .filter(coach -> coach.isSameName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("내부 오류입니다."));

        findCoach.setBannedMenu(menus);
    }


    private void validateCoachesSize(List<Coach> coaches) {
        if (coaches.size() < COACHES_MIN_SIZE || coaches.size() > COACHES_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("코치는 최소 %d명, 최대 %d명까지 입력할 수 있습니다.", COACHES_MIN_SIZE, COACHES_MAX_SIZE)
            );
        }
    }

    private static void validateDuplicatedName(List<String> name) {
        if (name.size() != name.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름은 입력할 수 없습니다.");
        }
    }
}
