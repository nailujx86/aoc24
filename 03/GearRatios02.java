import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class GearRatios02 {
    private static final Pattern digitPattern = Pattern.compile("\\d+");

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./input.txt"));
        long sumOfGearRatios = 0;
        for (int i = 0; i < lines.size(); i++) {
            int lastIndex = -1;
            String line = lines.get(i);
            do {
                lastIndex = line.indexOf('*', lastIndex + 1);
                if (lastIndex > 0) {
                    List<Integer> adjacentNumbers = findAdjacentNumbers(lines, lastIndex, i);
                    if (adjacentNumbers.size() > 1) {
                        sumOfGearRatios += adjacentNumbers.stream().reduce((a, b) -> a * b).orElse(0);
                    }
                }
            } while (lastIndex > 0);
        }
        System.out.println(sumOfGearRatios);
    }

    private static List<Integer> findAdjacentNumbers(List<String> allData, int column, int row) {
        final List<Integer> numbers = new ArrayList<>();
        final int maxRow = allData.size() - 1;
        final int maxCol = allData.get(0).length() - 1;
        for (int rowIndex = -1; rowIndex <= 1; rowIndex++) { // column
            if (((row + rowIndex) < 0) || ((row + rowIndex) > maxRow)) {
                continue; // above or beyond field
            }
            Set<Integer> hitColumns = new HashSet<>();
            String relevantLine = allData.get(row + rowIndex);
            for (int columnIndex = -1; columnIndex <= 1; columnIndex++) { // row
                if (columnIndex == 0 && rowIndex == 0) {
                    continue; // this is the field we're trying to check
                }
                int curColumn = column + columnIndex;
                if (hitColumns.contains(curColumn) || (curColumn < 0) || (curColumn > maxCol)) {
                    continue; // left or right outside of field or already visited
                }

                char fieldToCheck = relevantLine.charAt(curColumn);
                
                if (Character.isDigit(fieldToCheck)) {
                    Matcher digitMatcher = digitPattern.matcher(relevantLine);
                    while (digitMatcher.find()) {
                        if (digitMatcher.end() < curColumn || digitMatcher.start() > curColumn) {
                            // Do nothing
                        } else {
                            IntStream.range(digitMatcher.start(), digitMatcher.end()).forEach(hitColumns::add);
                            numbers.add(Integer.parseInt(digitMatcher.group()));
                            break;
                        }
                    }
                }
            }
        }
        return numbers;
    }

}
