import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Conundrum02 {

    public static void main(String[] args) throws Exception {
        Pattern gamePattern = Pattern.compile("Game (\\d+): (.*)");
        List<String> lines = Files.readAllLines(Path.of("./input.txt"));
        int gamesSum = 0;
        for (String line : lines) {
            var matcher = gamePattern.matcher(line);
            matcher.find();
            String gamesString = matcher.group(2);
            List<List<String>> games = List.of(gamesString.split("; ")).stream().map(game -> List.of(game.split(", ")))
                    .toList();
            gamesSum += getPower(games);
            System.out.println("sum: " + gamesSum);
        }
        System.out.printf("Result: %d%n", gamesSum);
    }

    private static int getPower(List<List<String>> games) {
        Map<String, Integer> colors = new HashMap<>();
        for (List<String> game : games) {
            for (String draw : game) {
                String[] drawData = draw.split(" ");
                int amount = Integer.parseInt(drawData[0]);
                String color = drawData[1];
                if (!colors.containsKey(color) || colors.get(color) < amount) {
                    colors.put(color, amount);
                }
            }
        }
        return colors.values().stream().reduce((a, b) -> a * b).orElse(0);
    }

}
