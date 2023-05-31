package ben.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;

public class Game {
    // we have to keep track of where each value is located, and make sure there are no collisions.
    // the container set size is defined by the starting grid size.
    // we'll keep it at squares for now and then see about other shapes.
    private final static int GRID_SIZE_OFFSET = 1;
    private final static int ZERO = 0;
    private List<GridSquare> squares = new ArrayList<GridSquare>(); //sort this by row?
    private Map<String, GridSquare> squaresMap = new HashMap<String, GridSquare>();
    private int gridSize; // this must be a square, or something that we then square.

    public Game (int gridSize) {
        // when we make the game, we gotta make squares.
        // once we got the squares, we can put 'em in containers.
        // make rows
        this.gridSize = gridSize;
        for (int i = 1; i < this.squareGridSize() + GRID_SIZE_OFFSET; i++) {
            // make the columns
            for(int j = 1; j < this.squareGridSize() + GRID_SIZE_OFFSET; j++){
                squares.add(new GridSquare(j, i));
            }
        }
        for(GridSquare square : squares){
            String key = square.getYCoordinate() + "-" + square.getXCoordinate();
            squaresMap.put(key, square);
        }
    }
    public List<GridSquare> getSquares (){
        return squares;
    }
    public int squareGridSize(){
        return this.gridSize * this.gridSize;
    }

    public void printGameGrid() {
        // this will work until we have real graphics.
        int rowCount = 1;
        int colCount = 1;
        String asterisks = "";
        // for every column in the grid, we gain three spaces.
        // number of asterisks = (number of columns * 3) + square of grid size
        // number of columns = box size - 1? cuz box size is box count.
        for (int i = 0; i < this.squareGridSize() + ((this.gridSize - 1) * 3); i++) {
            asterisks += "-";
        }
        for (GridSquare square : squares){
            if (square.getYCoordinate() > rowCount) {
                System.out.print("\n");
                if (rowCount % this.gridSize == ZERO && rowCount != this.squareGridSize()){
                    System.out.print(asterisks + "\n");
                }
            }
            if ((colCount % this.gridSize == ZERO)
                    && (colCount != this.squareGridSize())) {
                System.out.print(" | ");
            }

            System.out.print(square.getValue());
            rowCount = square.getYCoordinate();
            colCount = square.getXCoordinate();
        }
    }
    public void updateSquareInList (String key, int proposedValue){
        // parse input
        int y = Integer.parseInt(key.charAt(0) + "");
        int x = Integer.parseInt(key.charAt(2) + "");
        // this is SUPER NOT IDEAL.
        // but it is SUPER GOOD ENOUGH.
        for (GridSquare square : squares){
            int squareX = square.getXCoordinate();
            int squareY = square.getYCoordinate();
            if(y == squareY && x == squareX){
                square.setValue(proposedValue); //change this to the update function once she's ready for game time.
            }
        }
    }

    public boolean updateIsValid (GridSquare square, int proposedValue){

        return false;
    }

}
