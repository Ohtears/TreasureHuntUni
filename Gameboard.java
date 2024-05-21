import java.util.List;
public interface Gameboard {

    public void display(List<Player> players);
    
    void saveGame(String filePath);
    
    void loadGame(String filePath);
}