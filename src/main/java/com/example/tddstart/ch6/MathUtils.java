package com.example.tddstart.ch6;

import java.io.File;

public class MathUtils {

    public static Long sum(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("file is null");
        }
        return 0L;
    }
}
