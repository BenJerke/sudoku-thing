package ben.sudoku;


public final class SudokuFunctions {
    public static boolean stub(){
        return true;
    }

    /**
     * If you want to play sudoku, you need a square grid that can be subdivided into smaller square grids of equal size.
     * This checks if our user-specified dimensions can construct a usable grid.
     * @param dimensions
     * @return
     */
    public static boolean gridDimensionsAreSquare(int dimensions)  {
        double dimCheck = dimensions;
        return Math.sqrt(dimCheck) % 1 == 0;
    }

    public static int[] setBoundaries(int squareRootDimensions){
        // boundary: the start or end of a box (grid subdivision/subsquare).
        // number of boundaries = two for each box
        // number of boxes = one for each square root unit.
        int[] boundaries = new int[squareRootDimensions * 2];
        // offset by one.
        int gridThreshold = squareRootDimensions * squareRootDimensions - 1;
        int boundary = 0;
        // set a boundary at the start and end of each box
        boolean settingUpper = true;
        for(int i = 0; boundary < gridThreshold + 1; i++){
            boundaries[i] = boundary;
            // lower and upper boundaries require different math
            // we alternate between lower and upper each iteration of this loop
            // if upper, add square root dimensions minus one.
            // if lower, add one.
            if(settingUpper){
                boundary += squareRootDimensions - 1;
            } else {
                boundary += 1;
            }
            settingUpper = !settingUpper;
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
    public static boolean coordinateInputIsValid(int gameGridLength, int coordinate){
        int coordinateIndexOffset = coordinate - 1;
        if (coordinateIndexOffset > gameGridLength || coordinateIndexOffset < 0){
            return false;
        } else {
            return true;
        }
    }
    public static boolean gridSquareInputValueIsValid(int [][] gameGrid, int xCoordinate, int yCoordinate, int value, int[] boundaries){
        // Given the current game grid, check if the user-proposed input is valid.
        // If we check row, column, and box sequentially, we can catch invalid updates without having to search each of them.
        // This saves a little time.

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
        // Check the box for the same value.
        int boxSize = (int)Math.sqrt(gameGrid.length + 1);
        int upperXBoundary = findUpperBoxBoundary(xCoordinate, boxSize, boundaries);
        int lowerXBoundary = findUpperBoxBoundary(xCoordinate, boxSize, boundaries) - (boxSize - 1);
        int upperYBoundary = findUpperBoxBoundary(yCoordinate, boxSize, boundaries);
        int lowerYBoundary = findUpperBoxBoundary(yCoordinate, boxSize, boundaries) - (boxSize - 1);
        // start at the lower Y boundary and lower X boundary.
        // check each row index for the same input value up to the upper X boundary
        // then move to the next row.
        // stop at the upper Y boundary.
        for(int rowIndex = lowerYBoundary; rowIndex < upperYBoundary + 1; rowIndex++){
            for(int columnIndex = lowerXBoundary; columnIndex < upperXBoundary + 1; columnIndex++){
                int squareValue = gameGrid[rowIndex][columnIndex];
                if (value == squareValue){
                    return false;
                }
            }
        }
        return true;
    }
    private static int findUpperBoxBoundary(int coordinate, int boxSize, int[] boundaries){
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
