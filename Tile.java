import java.awt.Point;

public class Tile {

    public enum Type {
        EMPTY, BREAKABLEWALL, UNBREAKABLEWALL, BOMB, TREASURE, SPIN, PORTAL, MOUSETRAP, TNT
    }

    private int x;
    private int y;

    private boolean occupied;

    private Type type;
    
    public Tile(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.occupied = false;
    }


    
    public Tile.Type getType() {
        return type;
    }

    public void setteroccupation(boolean occupation){

        this.occupied = occupation;

    }
    public boolean getteroccupation(){

        return this.occupied;

    }

    public String getSymbol() {
        switch (type) {
            case BREAKABLEWALL:
                return "\u001B[33m" + "BWL" + "\u001B[0m";
            case UNBREAKABLEWALL:
                return "\u001B[33m" + "UWL" + "\u001B[0m";
            case BOMB:
                return "\u001B[31m" + "BMB" + "\u001B[0m";
            case TREASURE:
                return "\u001B[32m" + "TSR" + "\u001B[0m";
            case SPIN:
                return "\u001B[36m" + "SPN" + "\u001B[0m";
            case PORTAL:
                return "\u001B[36m" + "POR" + "\u001B[0m";
            case MOUSETRAP:
                return "\u001B[31m" + "MST" + "\u001B[0m";
            case TNT:
                return "\u001B[31m" + "TNT" + "\u001B[0m";
            default:
                return "\u001B[30m" + "   " + "\u001B[0m"; 
        }
    }


    public static String TileSymbol(Point point){

        char letter = (char) ('A' + point.x);
        int number = point.y + 1;
        System.out.println('\n');

        return String.valueOf(number) + letter;
    }


}