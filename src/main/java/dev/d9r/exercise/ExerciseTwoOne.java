package dev.d9r.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class ExerciseTwoOne implements Exercise{

    @Override
    public Number run(final List<String> input) {
        Map<String, String> map = getMapOfRanges(input.getFirst());
        long result = 0;

        //Go through the pairs
        for(Map.Entry<String, String> entry : map.entrySet()) {
            long start = Long.parseLong(entry.getKey());
            long end = Long.parseLong(entry.getValue());
            for(var i = start; i <= end; i++) {
                String value = String.valueOf(i);
                if (value.length() % 2 == 0) {
                    var firstHalf = value.substring(0, value.length() / 2);
                    if (value.split(firstHalf, -1).length -1 == 2) {
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
}
