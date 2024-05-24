import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.Point;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    public static final int SIZE = 10;

    public static void saveGameState(String filePath, Tile[][] tiles, List<Player> players, int turn, String player_turn) {
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
        gameState.put("turn", turn);
        gameState.put("player_turn", player_turn);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(gameState.toString(4));
        } catch (IOException e) {
            System.out.println("There was a problem loading the database for the game. Please try later");;
            Main.main(null);
        }
    }

    public static void loadGameState(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            JSONObject json = new JSONObject(new JSONTokener(reader));

            List<Player> players = loadPlayers(json);

            Tile[][] tiles = loadTiles(json);

            int turn = json.getInt("turn");
            String player_turn = json.getString("player_turn");

            Game.continueLoadedGame(players, tiles, turn, player_turn);
            

        } catch (IOException e) {
            System.out.println("Error in JSON structure or keys");;
            Main.main(null);
        } catch (org.json.JSONException e) {
            e.printStackTrace();
            System.out.println("Error in JSON structure or keys.");
            Main.main(null);
        }
    }

    public static List<Player> loadPlayers(JSONObject json) {
        List<Player> players = new ArrayList<>();
        if (json.has("players")) {
            JSONArray playersArray = json.getJSONArray("players");
            for (int i = 0; i < playersArray.length(); i++) {
                JSONObject playerObj = playersArray.getJSONObject(i);
                try{
                int hp = playerObj.getInt("hp");
                int score = playerObj.getInt("score");
                int abilityDestruction = playerObj.getInt("abilityDestruction");
                int abilityLongJump = playerObj.getInt("abilityLongJump");
                int abilitySpawnTrap = playerObj.getInt("abilitySpawnTrap");
                int id = playerObj.getInt("id");
                int x = playerObj.getJSONObject("position").getInt("x");
                int y = playerObj.getJSONObject("position").getInt("y");

                


                Player player = new Player(id, new Point(x, y));

                player.changehp(hp);
                player.changeScore(score);
                player.changeDestroy(abilityDestruction);
                player.changeLong_Jump(abilityLongJump);
                player.changeSpawn_Trap(abilitySpawnTrap);
                players.add(player);
                
            }
            catch (Exception e){
                System.out.println("No previous game found!");
                Main.main(null);
            }

            }
        } else {
            System.out.println("Key 'players' not found in JSON.");
            Main.main(null);
        }
        return players;
    }
    

    public static Tile[][] loadTiles(JSONObject json) {
        Tile[][] tiles = new Tile[SIZE][SIZE];
        for (String type : json.keySet()) {
            if (type.equals("players") || type.equals("turn") || type.equals("player_turn")) continue;
            JSONArray tileArray = json.getJSONArray(type);
            for (int i = 0; i < tileArray.length(); i++) {
                JSONObject tileObj = tileArray.getJSONObject(i);
                int x = tileObj.getInt("x");
                int y = tileObj.getInt("y");
                Tile tile = new Tile(x, y, Tile.Type.valueOf(type));
                tiles[y][x] = tile;
            }
        }
        return tiles;
    }


    public static void GameloggerSave(){

        String filePath = "GameLog.json";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("[]");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void appendlog(JSONObject logdata){



            try {
            String jsonData = new String(Files.readAllBytes(Paths.get("GameLog.json")));
            JSONArray jsonArray;
            if (jsonData.trim().isEmpty()) {
                jsonArray = new JSONArray();
            } else {
                jsonArray = new JSONArray(jsonData);
            }
            jsonArray.put(logdata);
            FileWriter fileWriter = new FileWriter("GameLog.json");
            fileWriter.write(jsonArray.toString(4)); 
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public static void loadlog(){


        try (FileReader reader = new FileReader("GameState.json")) {
            
            StringBuilder jsonBuilder = new StringBuilder();
            int character;
            
            while ((character = reader.read()) != -1) {
                jsonBuilder.append((char) character);
            }
            String jsonString = jsonBuilder.toString();

            JSONArray jsonArray = new JSONArray(jsonString);
            
            for (int i = 0; i < jsonArray.length(); i++) {
                
                JSONObject cardObject = jsonArray.getJSONObject(i);
                
                try{

                int card_num = cardObject.getInt("cart_number");
                

                if (card_num == card_numberr){
                
                    return cardObject;    

                }}
                catch(JSONException f) //to json ye seria cart_number ndrn
                {
                    continue;
                }


            }
        }
        catch (Exception e){
            System.out.println(e);
        }


    }


}
