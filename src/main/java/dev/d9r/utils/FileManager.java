package dev.d9r.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.IO.println;

public class FileManager {
    private File file;
    private final List<String> lines;

    public FileManager() {
        this.lines = new ArrayList<>();
    }

    public List<String> getInputLines(final Path path) {
        this.file = new File(path.toUri());
        if (lines.isEmpty()) {
            try (Scanner reader = new Scanner(getFile())) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    getLines().add(line);
                }

            } catch (FileNotFoundException _) {
                println("File not found");
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
