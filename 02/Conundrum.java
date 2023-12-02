import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Conundrum {

    private static final Map<String, Integer> COLOR_MAP = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14);

    public static void main(String[] args) throws Exception {
        Pattern gamePattern = Pattern.compile("Game (\\d+): (.*)");
        List<String> lines = Files.readAllLines(Path.of("./input.txt"));
        int gamesSum = 0;
        for (String line : lines) {
            var matcher = gamePattern.matcher(line);
            matcher.find();
            String id = matcher.group(1);
            String gamesString = matcher.group(2);
            List<List<String>> games = List.of(gamesString.split("; ")).stream().map(game -> List.of(game.split(", ")))
                    .toList();
            if (games.stream().allMatch(Conundrum::isLegalGame)) {
                gamesSum += Integer.parseInt(id);
            }
        }
        System.out.printf("Result: %d%n", gamesSum);
    }

    private static boolean isLegalGame(List<String> game) {
        for (String draw : game) {
            String[] drawData = draw.split(" ");
            int count = Integer.parseInt(drawData[0]);
            String color = drawData[1];
            if (COLOR_MAP.get(color) < count) {
                return false;
            }
        }
        return true;
    }

}
