package dev.d9r;

import dev.d9r.exercise.ExerciseTwoTwo;
import dev.d9r.runner.ExerciseRunner;
import dev.d9r.utils.FileManager;

public class Main {
    public static void main(String[] args) {
        var fileManager = new FileManager("inputs/2/input.txt");

        var ex = new ExerciseRunner(new ExerciseTwoTwo(), fileManager.getInputLines());
        ex.start();
    }
}