package ben.sudoku;


public class SudokuFunctions {

    /**
     * If you want to play sudoku, you need a square grid that can be subdivided into smaller square grids of equal size.
     * This checks if our user-specified dimensions can construct a usable grid.
     * @param dimensions
     * @return
     */
    public static boolean checkDimensions(int dimensions)  {
        double dimCheck = dimensions;
        if (Math.sqrt(dimCheck) % 1 != 0 ){
            return true;
        } else {
            return false;
        }
    }

    public static int[] setBoundaries(int squareRootDimensions, int[] gameGridSide){
        // Since our grid is square, X and Y boundaries will occur at the same indices.
        int[] boundaries = new int[squareRootDimensions];
        int gridThreshold = squareRootDimensions * squareRootDimensions;
        int boundary = 0;
        for(int i = 0; i < gridThreshold; i ++){
            boundaries[i] = boundary;
            boundary += squareRootDimensions;
        }
        return boundaries;
    }


    /**
     * Check if a proposed coordinate is actually on our grid.
     * User-defined coordinates need to be offset by one to account for zero-based index enumeration.
     * We only need to check one dimension of the game grid to determine if the input is valid, 'cuz all grids are squares.
     * @param gameGridLength
     * @param coordinate
     * @return
     */
    public static boolean validateCoordinateInput(int gameGridLength, int coordinate){
        int coordinateIndexOffset = coordinate - 1;
        if (coordinateIndexOffset > gameGridLength|| coordinateIndexOffset < gameGridLength){
            return false;
        } else {
            return true;
        }
    }
    public static boolean validateGridSquareInput(int [][] gameGrid, int xCoordinate, int yCoordinate, int value, int[] boundaries){
        // Given the current game grid, check if the user-proposed input is valid.

        // Find the row: the array at our y-coordinate.
        // Check the row for the same value.
        for(int rowMember : gameGrid[yCoordinate - 1]){
            if(rowMember == value){
                return false;
            }
        }
        // Find the column: all values at position x within each array in the grid
        // Check the column for the same value.
        for(int[] row : gameGrid){
            if(row[xCoordinate - 1] == value){
                return false;
            }
        }
        // Find the box: all values for the rows starting at the lower box boundaries on the x and y axes, and ending at the upper box boundaries for the x and y axes.
        // Get the lower box boundary on the Y axis: the nearest multiple of the square root of gameGrid.length plus one.
        int boxSize = (int)Math.sqrt(gameGrid.length + 1);
        int upperXBoundary = findUpperBoundary(xCoordinate, boxSize, boundaries);
        int lowerXBoundary = findUpperBoundary(xCoordinate, boxSize, boundaries) - (boxSize - 1);
        int upperYBoundary = findUpperBoundary(yCoordinate, boxSize, boundaries);
        int lowerYBoundary = findUpperBoundary(yCoordinate, boxSize, boundaries) - (boxSize - 1);

        // Check the box for the same value.
        return false;
    }
    private static int findUpperBoundary(int coordinate, int boxSize, int[] boundaries){
        int upperBoundary = 0;
        if(coordinate % boxSize == 0){
            upperBoundary = coordinate;
        } else {
            for(int boundary : boundaries){
                if(coordinate <= boundary){
                    upperBoundary = boundary;
                    return upperBoundary;
                }
            }
        }
        return upperBoundary;
    }
}
