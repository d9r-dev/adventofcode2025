package dev.d9r.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private final File file;
    private final List<String> lines;

    public FileManager(final String path) {
        this.file = new File(path);
        this.lines = new ArrayList<>();
    }

    public List<String> getInputLines() {
        if (lines.isEmpty()) {
            try (Scanner reader = new Scanner(getFile())) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    getLines().add(line);
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
        return getLines();
    }

    public File getFile() {
        return file;
    }

    public List<String> getLines() {
        return lines;
    }
}
