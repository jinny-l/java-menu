package menu.util;

import java.util.List;

public interface NumberGenerator {

    List<Integer> generate(int size, int min, int max, int maxDuplicates);
}
