package menu.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generate(int size, int min, int max, int maxDuplicates) {
        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < size) {
            int number = Randoms.pickNumberInRange(min, max);
            int count = Collections.frequency(numbers, number);

            if (count < maxDuplicates) {
                numbers.add(number);
            }
        }
        return numbers;
    }
}
