package dev.d9r.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseTwoTwo implements Exercise {
    @Override
    public Number run(final List<String> input) {
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
