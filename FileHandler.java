import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Point;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler{

    public static void saveGameState(String filePath, Tile[][] tiles, List<Player> players) {
        Map<String, JSONArray> tilePointsMap = new HashMap<>();
        for (Tile.Type type : Tile.Type.values()) {
            tilePointsMap.put(type.name(), new JSONArray());
        }

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile.Type type = tiles[i][j].getType();
                JSONObject point = new JSONObject();
                point.put("x", j);
                point.put("y", i);
                tilePointsMap.get(type.name()).put(point);
            }
        }

        JSONArray playerStates = new JSONArray();
        for (Player player : players) {
            JSONObject playerState = new JSONObject();
            playerState.put("id", player.getID());
            playerState.put("hp", player.getHp());
            playerState.put("position", new JSONObject()
                    .put("x", player.getPosition().x)
                    .put("y", player.getPosition().y));
            playerState.put("score", player.getScore());
            playerState.put("abilityDestruction", player.getAbilities("Destroy"));
            playerState.put("abilityLongJump", player.getAbilities("Long_Jump"));
            playerState.put("abilitySpawnTrap", player.getAbilities("Spawn_Trap"));

            playerStates.put(playerState);
        }

        JSONObject gameState = new JSONObject();
        for (Map.Entry<String, JSONArray> entry : tilePointsMap.entrySet()) {
            gameState.put(entry.getKey(), entry.getValue());
        }
        gameState.put("players", playerStates);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(gameState.toString(4)); // Pretty print with an indentation of 4 spaces
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
