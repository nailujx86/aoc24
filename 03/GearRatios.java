import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./input.txt"));

        Pattern pattern = Pattern.compile("\\d+");

        long sum = 0;

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            Matcher matcher = pattern.matcher(line);
            int searchedIdx = 0;
            while (matcher.find()) {
                String match = matcher.group();
                int location = line.indexOf(match, searchedIdx);
                searchedIdx = location + match.length();
                boolean isAdjacentToSymbol = false;
                
                for (int column = location; column < (match.length() + location); column++) {
                    if (isAdjacentToSymbol(lines, column, row)) {
                        isAdjacentToSymbol = true;
                        break;
                    }
                }
                if (isAdjacentToSymbol) {
                    //System.out.println("Okay, this is apparently a valid piece: " + match);
                    sum += Integer.parseInt(match);
                } else {
                    System.out.println("Not a valid piece: " + match + " row=" + (row + 1));
                }
            }
        }

        System.out.println(sum);
    }

    private static boolean isAdjacentToSymbol(List<String> allData, int column, int row) {
        final int maxRow = allData.size() - 1;
        final int maxCol = allData.get(0).length() - 1;
        for (int columnIndex = -1; columnIndex <= 1; columnIndex++) { // column
            if (((column + columnIndex) < 0) || ((column + columnIndex) > maxCol)) {
                continue; // left or right outside of field
            }
            for (int rowIndex = -1; rowIndex <= 1; rowIndex++) { // row
                if (columnIndex == 0 && rowIndex == 0) {
                    continue; // this is the field we're trying to check
                }
                if (((row + rowIndex) < 0) || ((row + rowIndex) > maxRow)) {
                    continue; // above or beyond field
                }
                char fieldToCheck = allData.get(row + rowIndex).charAt(column + columnIndex);
                //System.out.println("checking field: " + fieldToCheck + " at " + (column + columnIndex) + " : " + (row + rowIndex));
                if (fieldToCheck != '.' && !Character.isDigit(fieldToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }
}
