package ben.sudoku;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int[][] valid9x9GameGrid = new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };

//        valid9x9GameGrid[0][0] = 0;
//        valid9x9GameGrid[8][8] = 0;
//        valid9x9GameGrid[4][4] = 0;
//        valid9x9GameGrid[0][1] = 0;
        int[][][] values = SudokuFunctions.listPossibleValuesPerSquareAcrossGrid(valid9x9GameGrid, SudokuFunctions.setBoundaries(3));
        for(int yCoordinate = 1; yCoordinate < valid9x9GameGrid.length + 1; yCoordinate++){
            for (int xCoordinate = 1; xCoordinate < valid9x9GameGrid.length + 1; xCoordinate++){
                int[] possibleValues = values[yCoordinate - 1][xCoordinate - 1];
                String possibleValueString = "";
                for (int i : possibleValues){
                    possibleValueString += i + " ";
                }
                System.out.println(xCoordinate + ", " + yCoordinate + ": " + possibleValueString );
            }
        }

//        Scanner userInput = new Scanner(System.in);
//        System.out.println("What size of boxes do you want in your grid?");
//        int gridSize = Integer.parseInt(userInput.nextLine());
//        Game game = new Game(gridSize);
//
//        boolean keepPlaying = true;
//        int turnCount = 1;
//        while (keepPlaying){
//            System.out.println("Turn " + turnCount);
//            game.printGameGrid();
//            System.out.println("\nPick a square and a value. (row, column, number)");
//            String proposedValue = userInput.nextLine();
//            if (proposedValue.equals("q") ){
//                System.out.println("See ya!");
//                break;
//            }
//            //game.updateIsValid()
//            game.updateSquareInList(proposedValue);
//            turnCount += 1;
//            System.out.println("\n");
            //game.printGameGrid();

            //System.out.println("\nKeep playing?");
//            String input = userInput.nextLine();
//            if (!(input.equals("y"))){
//                System.out.println("\nSee ya!");
//                keepPlaying = false;
////            }
//        }
    }
}
