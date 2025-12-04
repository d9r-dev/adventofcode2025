package dev.d9r.exercise;

import java.util.ArrayDeque;
import java.util.List;

public class Exercise03 implements Exercise {

    @Override
    public Number part1(List<String> input) {
        int result = 0;
        for (String line : input) {
            int first = 0;
            int second = 0;
            int firstIndex = 0;
            for (int i = 0; i < line.length() - 1; i++) {
                var num = Character.getNumericValue(line.charAt(i));
                if (num > first) {
                    first = num;
                    firstIndex = i;
                }
            }

            for (int i = firstIndex + 1; i < line.length(); i++) {
                var num = Character.getNumericValue(line.charAt(i));
                if (num > second) {
                    second = num;
                }
            }
            result += Integer.parseInt(String.format("%d%d", first, second));
        }
        return result;
    }

    @Override
    public Number part2(List<String> input) {
        long result = 0;
        for (String line : input) {
            var stack = new ArrayDeque<Integer>();
            var chars = line.toCharArray();
            int toRemove = chars.length - 12;

            for (int i = 0; i < chars.length; i++) {
                var num = Character.getNumericValue(chars[i]);

                while (!stack.isEmpty() && stack.peek() < num && toRemove > 0) {
                    stack.pop();
                    toRemove--;
                }

                stack.push(num);
            }

            while (toRemove > 0) {
                stack.pop();
                toRemove--;
            }

            StringBuilder resultstr = new StringBuilder();
            while (!stack.isEmpty()) {
                resultstr.append(stack.pop());
            }

            String res = resultstr.reverse().toString();
            result += Long.parseLong(res);
        }
        return result;
    }
}
