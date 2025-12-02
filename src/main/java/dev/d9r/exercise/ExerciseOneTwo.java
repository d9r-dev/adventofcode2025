package dev.d9r.exercise;

import java.util.List;

public class ExerciseOneTwo implements Exercise {
    private int solution;
    private int startPoint;
    private char direction;
    private int amount;
    private boolean lastZero;

    public ExerciseOneTwo() {
        this.solution = 0;
        this.startPoint = 50;
        this.lastZero = false;
    }

    @Override
    public int run(List<String> input) {
        input.forEach(this::checkLine);
        return this.solution;
    }

    private void checkLine(String line) {
        this.direction = line.charAt(0);
        this.amount = Integer.parseInt(line.substring(1));
        calculateNewStartPoint();
    }

    private void calculateNewStartPoint() {
        int am = direction == 'L' ? amount * -1 : amount;

        startPoint = startPoint + am;
        if (startPoint == 0 && !lastZero) {
            solution++;
        } else if (startPoint < 0) {
            solution -= Math.floorDiv(startPoint - 1, 100);
            if (lastZero) {
                solution--;
            }
        } else if (startPoint > 99) {
            solution += Math.floorDiv(startPoint, 100);
        }

        startPoint = (startPoint % 100 + 100) % 100;
        lastZero = startPoint == 0;

    }
}
