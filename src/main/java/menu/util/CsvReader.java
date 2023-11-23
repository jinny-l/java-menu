package menu.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {

    private CsvReader() {
    }

    public static List<String> read(String filePath) {
        try(var lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 입출력 관련 에러입니다.");
        }
    }
}
