package dev.d9r.runner;

import dev.d9r.exercise.Exercise;
import dev.d9r.utils.FileManager;

import java.lang.reflect.Constructor;
import java.util.List;

import static java.lang.IO.println;

public class ExerciseRunner {
    private final FileManager fileManager;

    private static final String DAYS_PACKAGE = "dev.d9r.exercise";

    public ExerciseRunner() {
        this.fileManager = new FileManager();
    }

    private List<String> readInput(int dayNumber) {
        String resourceName = dayNumber + ".txt";
        var inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
        
        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resourceName + ". Make sure " + resourceName + " exists in src/main/resources/");
        }
        
        return fileManager.getInputLines(inputStream);
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
            System.out.printf("(Time: %.2f ms)%n", (System.nanoTime() - start1) / 1_000_000.0);

            long start2 = System.nanoTime();
            println("Part 2: " + exercise.part2(input));
            System.out.printf("(Time: %.2f ms)%n", (System.nanoTime() - start2) / 1_000_000.0);

        } catch (ClassNotFoundException e) {
            System.err.println("Solution for Day " + dayNumber + " not found!");
            System.err.println("Looking for class: " + DAYS_PACKAGE + "." + dayClassName);
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("Error running day " + dayNumber + ":");
            e.printStackTrace();
        }
    }
}
