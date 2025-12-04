package dev.d9r.runner;

import dev.d9r.exercise.Exercise;
import dev.d9r.utils.FileManager;

import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.lang.IO.println;

public class ExerciseRunner {
    private final FileManager fileManager;

    private static final String DAYS_PACKAGE = "dev.d9r.exercise";

    public ExerciseRunner() {
        this.fileManager = new FileManager();
    }

    private List<String> readInput(int dayNumber) {
        Path path = null;
        try {
            path = Path.of(ClassLoader.getSystemResource(dayNumber + ".txt").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return fileManager.getInputLines(Objects.requireNonNull(path));
    }

    public void runDay(int dayNumber) {
        List<String> input;
        input = readInput(dayNumber);
        String dayClassName = String.format("Exercise%02d", dayNumber);

        try {
            Class<?> clazz = Class.forName(DAYS_PACKAGE + "." + dayClassName);
            Constructor<?> ctor = clazz.getDeclaredConstructor();
            Exercise exercise = (Exercise) ctor.newInstance();

            long start1 = System.nanoTime();
            println("Part 1: " + exercise.part1(input));
            System.out.printf("(Time: %.2f ms)%n", (System.nanoTime() - start1) * 1e-9);

            long start2 = System.nanoTime();
            println("Part 2: " + exercise.part2(input));
            System.out.printf("(Time: %.2f ms)%n", (System.nanoTime() - start2) * 1e-9);

        } catch (ClassNotFoundException _) {
            System.err.println("Solution for Day " + dayNumber + " not found!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
