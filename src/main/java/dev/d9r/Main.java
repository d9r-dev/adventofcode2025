package dev.d9r;

import dev.d9r.runner.ExerciseRunner;

import static java.lang.IO.println;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            println("Please provide a day to run");
            return;
        }

        int dayNumber = Integer.parseInt(args[0]);
        var runner = new ExerciseRunner();
        runner.runDay(dayNumber);
    }
}