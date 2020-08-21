package demo_zpl.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Read files in UTF-8 encoding and return its content in String.
     *
     * @param fileName The name of the file to be search in the resources of
     * test.
     * @return A string with the content of the file.
     * @throws IOException In case of error.
     */
    public static String castFileToStringUTF8(String fileName) throws IOException {
        Path path = Paths.get("classpath:resources/" + fileName);
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}
