import java.util.List;
public interface Gameboard {

    public void display(List<Player> players);

    void setTrap(int playerId, int x, int y);

    boolean destroyTrap(int playerId, int x, int y);

    boolean longJump(int playerId, int x, int y);

    void saveGame(String filePath);
    
    void loadGame(String filePath);
}