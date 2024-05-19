

public class Main{

    public static void main(String [] args){

        System.out.println("Welcome to Treasure Hunt. Please proceed by choosing one of the following options");

        System.out.println("Press '1' for 2 player mode and '2' for 4 player mode");

        int choice_main_menu = InputHandler.choice_number();

        switch (choice_main_menu) {

            case 1: 

                Game.start_newgame();

            break;

            case 2:
            break;

            case 3:
            System.out.println("You are exiting Treasurehunt. Thank you for playing! ! ");
            System.exit(0);

        }



    }


}