package menu.domain;

public class Coach {

    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 4;

    private final String name;

    public Coach(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("이름은 최소 %d글자, 최대 %d글자를 입력해주세요.", NAME_MIN_LENGTH, NAME_MAX_LENGTH)
            );
        }
    }
}
