package ben.sudoku;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("What size of boxes do you want in your grid?");
        int gridSize = Integer.parseInt(userInput.nextLine());
        Game game = new Game(gridSize);

        boolean keepPlaying = true;
        while (keepPlaying){
            game.printGameGrid();
            System.out.println("\nWhat square do you want to update?");
            String key = userInput.nextLine();
            System.out.println("\nWhat value do you want to put in it?");
            int proposedValue = Integer.parseInt(userInput.nextLine());
            game.updateSquareInList(key, proposedValue);
            game.printGameGrid();
            System.out.println("\nWanna keep playing?");
            String input = userInput.nextLine();
            if (!(input.equals("y"))){
                System.out.println("\nSee ya!");
                keepPlaying = false;
            }
        }
    }
}
