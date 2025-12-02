package dev.d9r.runner;

import dev.d9r.exercise.Exercise;

import java.util.List;

public class ExerciseRunner {
    private final Exercise currentExercise;
    private List<String> input;

    public ExerciseRunner(final Exercise currentExercise, List<String> input) {
        this.currentExercise = currentExercise;
        this.input = input;
    }

    public void start() {
        var result = getCurrentExercise().run(getInput());
        System.out.println("Result: " + result);
    }

    public Exercise getCurrentExercise() {
        return currentExercise;
    }

    public List<String> getInput() {
        return input;
    }
}
