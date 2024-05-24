
public class Main{

    public static void main(String [] args){

        while (true){


        System.out.println();
        System.out.println("==============================");
        System.out.println("        GAME MAIN MENU");
        System.out.println("==============================\n");
        System.out.println("Welcome to the Game!\n");
        System.out.println("1. Start New Game 2 player mode");
        System.out.println("2. Start New Game 4 player mode");
        System.out.println("3. Load Game");
        System.out.println("4. Load latest Game log");
        System.out.println("0. Exit\n");
        System.out.print("Choose an option: ");

        int choice_main_menu = InputHandler.choice_number();

        switch (choice_main_menu) {

            case 1: 

                Game.start_newgame(Playermode.TWOPLAYER_MODE);
                
            break;

            case 2:

                Game.start_newgame(Playermode.FOURPLAYER_MODE);
                
            break;

            case 3:

            FileHandler.loadGameState("gameState.json");

            break;

            case 4:

                FileHandler.loadlog();

            break;


            case 0:

            System.out.println("You are exiting TreasureHunt. Thank you for playing");
            break;
        }
}}
}