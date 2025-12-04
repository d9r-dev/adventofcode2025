package dev.d9r.exercise;

import java.util.List;

public class Exercise01 implements Exercise {
    private int solution1;
    private int solution2;
    private int startPoint1;
    private int startPoint2;
    private char direction;
    private int amount;
    private boolean lastZero;

    public Exercise01() {
        this.solution1 = 0;
        this.startPoint1 = 50;
    }

    @Override
    public Number part1(List<String> input) {
        input.forEach(this::checkLine1);
        return this.solution1;
    }

    @Override
    public Number part2(List<String> input) {
        input.forEach(this::checkLine2);
        return this.solution2;
    }

    private void checkLine1(String line) {
        this.direction = line.charAt(0);
        this.amount = Integer.parseInt(line.substring(1));
        calculateNewStartPoint1();
    }

    private void calculateNewStartPoint1() {
        if(direction == 'L') {
            startPoint1 = (startPoint1 - amount + 100) % 100;
        } else {
            startPoint1 = (startPoint1 + amount) % 100;
        }
        if (startPoint1 == 0) {
            solution1 = solution1 + 1;
        }
    }

    private void checkLine2(String line) {
        this.direction = line.charAt(0);
        this.amount = Integer.parseInt(line.substring(1));
        calculateNewStartPoint2();
    }

    private void calculateNewStartPoint2() {
        int am = direction == 'L' ? amount * -1 : amount;

        startPoint2 = startPoint2 + am;
        if (startPoint2 == 0 && !lastZero) {
            solution2++;
        } else if (startPoint2 < 0) {
            solution2 -= Math.floorDiv(startPoint2 - 1, 100);
            if (lastZero) {
                solution2--;
            }
        } else if (startPoint2 > 99) {
            solution2 += Math.floorDiv(startPoint2, 100);
        }

        startPoint2 = (startPoint2 % 100 + 100) % 100;
        lastZero = startPoint2 == 0;
    }
}
