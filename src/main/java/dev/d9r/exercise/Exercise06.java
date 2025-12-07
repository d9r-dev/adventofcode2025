package dev.d9r.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Exercise06 implements Exercise {
    @Override
    public Number part1(List<String> input) {
        long result = 0;
        List<Problem> problems = parseInput(input);
        for (Problem problem : problems) {
            long problemResult = problem.op == Operation.ADD ? 0 : 1;
            BinaryOperator<Long> operation = problem.op == Operation.ADD ? Long::sum : (a, b) -> a * b;
            for (var number : problem.numbers) {
                problemResult = operation.apply(problemResult, (long) number);
            }
            result += problemResult;
        }
        return result;
    }

    private List<Problem> parseInput(List<String> input) {
        var problems = new ArrayList<Problem>();
        int maxWidth = input.stream().mapToInt(String::length).max().orElse(0);
        String operatorLine = input.get(input.size() - 1);

        int col = 0;
        while (col < maxWidth) {
            while (col < maxWidth && isColumnAllSpaces(input, col)) {
                col++;
            }
            if (col >= maxWidth) break;

            int startCol = col;
            while (col < maxWidth && !isColumnAllSpaces(input, col)) {
                col++;
            }
            int endCol = col;

            var numbers = new ArrayList<Integer>();
            for (int row = 0; row < input.size() - 1; row++) {
                String line = input.get(row);
                String segment = safeSubstring(line, startCol, endCol).trim();
                if (!segment.isEmpty()) {
                    numbers.add(Integer.parseInt(segment));
                }
            }

            Operation op = null;
            String opSegment = safeSubstring(operatorLine, startCol, endCol).trim();
            if (opSegment.equals("+")) {
                op = Operation.ADD;
            } else if (opSegment.equals("*")) {
                op = Operation.MULTIPLY;
            }

            if (!numbers.isEmpty() && op != null) {
                problems.add(new Problem(numbers.stream().mapToInt(Integer::intValue).toArray(), op));
            }
        }
        return problems;
    }

    private boolean isColumnAllSpaces(List<String> lines, int col) {
        for (String line : lines) {
            if (col < line.length() && line.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }

    private String safeSubstring(String s, int start, int end) {
        if (start >= s.length()) return "";
        return s.substring(start, Math.min(end, s.length()));
    }

    @Override
    public Number part2(List<String> input) {
        return null;
    }

    private record Problem(int[] numbers, Operation op) {
    }

    private enum Operation {
        ADD, MULTIPLY
    }
}
