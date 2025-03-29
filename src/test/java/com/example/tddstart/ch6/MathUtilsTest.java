package com.example.tddstart.ch6;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathUtilsTest {

    private static final String invalid_path = "src/test/resources/datafile.txt";
    private static final String valid_path = "src/test/resources/valid_datafile.txt";

    @Test
    void noDataFile_Then_ExceptionThrown() {
        givenNotFile(invalid_path);
        File file = new File(invalid_path);
        assertThrows(IllegalArgumentException.class,
                () -> MathUtils.sum(file));
    }

    private void givenNotFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean delete = file.delete();
            if (!delete) {
                throw new IllegalArgumentException("file givenNotFile : " + path);
            }
        }
    }

    @Test
    void dataFileSumTest() {
        givenDataFile(valid_path, "1", "-1", "0");
        File file = new File(valid_path);
        Long result = MathUtils.sum(file);
        assertEquals(result, 0L);
    }

    private void givenDataFile(String path, String... lines) {
        try {
            Path filePath = Path.of(path);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.write(filePath, Arrays.stream(lines).toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
