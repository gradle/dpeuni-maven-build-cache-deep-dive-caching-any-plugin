package org.example.training;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) {
        Arrays.asList(args).forEach(arg -> {
            try {
                Files.readAllLines(Paths.get(arg)).forEach(System.out::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
