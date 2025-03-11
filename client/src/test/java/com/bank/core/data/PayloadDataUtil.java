package com.bank.core.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PayloadDataUtil {

    public static String getDataStringFromJsonFile(String path) {
        try {
            Path filePath = Paths.get(ClassLoader.getSystemResource(path).toURI());
            return Files.readString(filePath);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
