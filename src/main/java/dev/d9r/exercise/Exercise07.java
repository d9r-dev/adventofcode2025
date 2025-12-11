package dev.d9r.exercise;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise07 implements Exercise {
    @Override
    public Number part1(List<String> input) {
        char[][] grid = input.stream().map(String::toCharArray).toArray(char[][]::new);
        int result = 0;
        var start = getStart(grid);

        Set<Point> active = new HashSet<>();
        active.add(start);

        for (int i = start.y; i < grid.length - 1; i++) {
            var newPoints = new HashSet<Point>();
            for (var point : active) {
                if(grid[point.y + 1][point.x] != '.') {
                    newPoints.add(new Point(point.x + 1, point.y + 1));
                    newPoints.add(new Point(point.x - 1, point.y + 1));
                    result++;
                } else {
                    newPoints.add(new Point(point.x, point.y + 1));
                }
            }
            active = newPoints;
        }

        return result;
    }

    private static Point getStart(char[][] grid) {
        var start = new Point(0, 0);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'S') {
                    start = new Point(x, y);
                }
            }
        }
        return start;
    }

    @Override
    public Number part2(List<String> input) {
        var grid = input.stream().map(String::toCharArray).toArray(char[][]::new);
        long result = 0;
        var start = getStart(grid);
        var cache = new HashMap<Point, Long>();

        result = calculate(grid, start, cache);

        return result;
    }

    private long calculate(char[][] grid, Point point, HashMap<Point, Long> cache) {
        if (point.y + 1 >= grid.length) {
            return 1;
        } else if (cache.containsKey(point)) {
            return cache.get(point);
        } else if (grid[point.y + 1][point.x] != '.') {
            var res = calculate(grid, new Point(point.x + 1, point.y + 1), cache) + calculate(grid, new Point(point.x - 1, point.y + 1), cache);
            cache.put(point, res);
            return res;
        } else {
            var res = calculate(grid, new Point(point.x, point.y + 1), cache);
            cache.put(point, res);
            return res;
        }
    }
}
