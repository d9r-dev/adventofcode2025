package dev.d9r;

import dev.d9r.exercise.ExerciseOneOne;
import dev.d9r.exercise.ExerciseOneTwo;
import dev.d9r.runner.ExerciseRunner;
import dev.d9r.utils.FileManager;

public class Main {
    public static void main(String[] args) {
        var fileManager = new FileManager("inputs/1/1.txt");

        var ex = new ExerciseRunner(new ExerciseOneOne(), fileManager.getInputLines());

        ex.start();

        var fileManager2 = new FileManager("inputs/1/2.txt");

        var ex2 = new ExerciseRunner(new ExerciseOneTwo(), fileManager2.getInputLines());
        ex2.start();
    }
}