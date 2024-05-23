import java.util.Scanner;

public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    InputHandler() {}

    public static int choice_number(){

        return Integer.parseInt(scanner.nextLine());

    }
}
