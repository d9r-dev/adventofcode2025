package dev.d9r.exercise;

import java.util.List;

public class ExerciseOneOne implements Exercise {
    private int solution;
    private int startPoint;
    private char direction;
    private int amount;

    public ExerciseOneOne() {
        this.solution = 0;
        this.startPoint = 50;
    }

    @Override
    public Number run(List<String> input) {
        input.forEach(this::checkLine);
        return this.solution;
    }

    private void checkLine(String line) {
        this.direction = line.charAt(0);
        this.amount = Integer.parseInt(line.substring(1));
        calculateNewStartPoint();
    }

    private void calculateNewStartPoint() {
        if(direction == 'L') {
            startPoint = (startPoint - amount + 100) % 100;
        } else {
            startPoint = (startPoint + amount) % 100;
        }
        if (startPoint == 0) {
            solution = solution + 1;
        }
    }
}
