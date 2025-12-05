package dev.d9r.exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise04 implements Exercise {
    @Override
    public Number part1(List<String> input) {
        int[][] grid = prepareGrid(input);
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@' && hasLessThanFourNeighbors(grid, i, j)) {
                        result++;
                    }

            }
        }
        return result;
    }

    private boolean hasLessThanFourNeighbors(final int[][] grid, final int i, final int j) {
        int neihbors = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        if(i > 0 && grid[i-1][j] == '@') {
            neihbors++;
        }
        if(i < rows - 1 && grid[i+1][j] == '@') {
            neihbors++;
        }
        if(j > 0 && grid[i][j-1] == '@') {
            neihbors++;
        }
        if(j < cols - 1 && grid[i][j+1] == '@') {
            neihbors++;
        }
        if(i > 0 && j > 0 && grid[i-1][j-1] == '@') {
            neihbors++;
        }
        if(i > 0 && j < cols -1 && grid[i-1][j+1] == '@') {
            neihbors++;
        }
        if(i < rows - 1 && j > 0 && grid[i+1][j-1] == '@') {
            neihbors++;
        }
        if(i < rows - 1 && j < cols - 1 && grid[i+1][j+1] == '@') {
            neihbors++;
        }
        return neihbors < 4;
    }

    @Override
    public Number part2(List<String> input) {
        int[][] grid = prepareGrid(input);
        int result = 0;
        Map<Integer, Integer> rollsToRemove = new HashMap<>();
        do {
            getRollsToRemove(grid, rollsToRemove);
            result += rollsToRemove.size();
            removeRolls(grid, rollsToRemove);
        } while (!rollsToRemove.isEmpty());

        return result;
    }

    private void removeRolls(int[][] grid, Map<Integer, Integer> rollsToRemove) {
        for (Map.Entry<Integer, Integer> entry : rollsToRemove.entrySet()) {
            grid[entry.getKey()][entry.getValue()] = '.';
        }
    }

    private Map<Integer, Integer> getRollsToRemove(int[][] grid, Map<Integer, Integer> rollsToRemove) {
        rollsToRemove.clear();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@' && hasLessThanFourNeighbors(grid, i, j)) {
                        rollsToRemove.put(i, j);
                    }

            }
        }
        return rollsToRemove;
    }

    private int[][] prepareGrid(List<String> input) {
        int[][] grid = new int[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = new int[input.get(i).length()];
            for (int j = 0; j < input.get(i).length(); j++) {
                grid[i][j] = input.get(i).charAt(j);
            }
        }
        return grid;
    }
}
