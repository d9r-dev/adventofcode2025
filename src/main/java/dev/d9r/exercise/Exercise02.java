package dev.d9r.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise02 implements Exercise {

    @Override
    public Number part1(final List<String> input) {
        Map<String, String> map = getMapOfRanges(input.getFirst());
        long result = 0;

        for (Map.Entry<String, String> entry : map.entrySet()) {
            long start = Long.parseLong(entry.getKey());
            long end = Long.parseLong(entry.getValue());
            for (var i = start; i <= end; i++) {
                String value = String.valueOf(i);
                if (value.length() % 2 == 0) {
                    var firstHalf = value.substring(0, value.length() / 2);
                    if (value.split(firstHalf, -1).length - 1 == 2) {
                        result += i;
                    }
                }
            }
        }

        return result;
    }

    private Map<String, String> getMapOfRanges(String input) {
        Map<String, String> rangeMap = new HashMap<>();

        String[] ranges = input.split(",");
        for (String range : ranges) {
            String[] parts = range.split("-");
            String key = parts[0];
            String value = parts[1];
            rangeMap.put(key, value);
        }

        return rangeMap;
    }

    @Override
    public Number part2(final List<String> input) {
        Map<String, String> map = getMapOfRanges(input.getFirst());
        long result = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String start = entry.getKey();
            String end = entry.getValue();
            long begin = Long.parseLong(start);
            long ending = Long.parseLong(end);

            for(var num = begin; num <= ending; num++) {
                if(isInvalidId(num)) {
                    result += num;
                }
            }
        }
        return result;
    }

    private boolean isInvalidId(long num) {
        String str = String.valueOf(num);

        for (int patternLength = 1; patternLength <= str.length() / 2; patternLength++) {
            if (str.length() % patternLength == 0) {
                String pattern = str.substring(0, patternLength);
                if (isOnlyRepetition(str, pattern)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isOnlyRepetition(String str, String substring) {
        if (str.length() % substring.length() != 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i += substring.length()) {
            if (!str.startsWith(substring, i)) {
                return false;
            }
        }

        return true;
    }

}
