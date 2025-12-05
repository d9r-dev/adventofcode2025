package dev.d9r.exercise;

import java.util.ArrayList;
import java.util.List;

public class Exercise05 implements Exercise {

    @Override
    public Number part1(List<String> input) {
        ExerciseInput exerciseInput = parseInput(input);

        int numberOfGoodIngredients = 0;
        for (long id : exerciseInput.ingredientIds()) {
            if (isGoodIngredien(id, exerciseInput.allGoodIngredients())) {
                numberOfGoodIngredients++;
            }
        }


        return numberOfGoodIngredients;
    }

    private boolean isGoodIngredien(long id, List<long[]> ranges) {
        for (long[] range : ranges) {
            if(id >= range[0] && id <= range[1]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Number part2(List<String> input) {
        return null;
    }

    private ExerciseInput parseInput(List<String> input) {
        List<String> ingredientIds = input.stream().filter(line -> !line.isEmpty() && !line.contains("-")).toList();
        List<String> ingredientRanges = input.stream().filter(line -> line.contains("-")).toList();

        long[] ingredientIdsArray = ingredientIds.stream().mapToLong(Long::parseLong).toArray();
        List<long[]> allGoodIngredients = getRangeOfGoodIngredients(ingredientRanges);

        return new ExerciseInput(ingredientIdsArray, allGoodIngredients);
    }

    private List<long[]> getRangeOfGoodIngredients(List<String> ingredientRanges) {
        List<long[]> allGoodIngredients = new ArrayList<>();
        for (String range : ingredientRanges) {
            String[] rangeParts = range.split("-");
            long start = Long.parseLong(rangeParts[0]);
            long end = Long.parseLong(rangeParts[1]);
            allGoodIngredients.add(new long[]{start, end});
        }
        return allGoodIngredients;
    }

    private record ExerciseInput(long[] ingredientIds, List<long[]> allGoodIngredients) {
    }
}
