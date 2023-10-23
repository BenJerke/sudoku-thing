package ben.sudoku;


import java.util.*;
import java.util.List;
public final class SudokuFunctions {

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
        for(int rowMember : gameGrid[yCoordinate - 1]){
            if(rowMember == value){
                return false;
            }
        }
        for(int[] row : gameGrid){
            if(row[xCoordinate - 1] == value){
                return false;
            }
        }
        int boxSize = (int)Math.sqrt(gameGrid.length);
        int upperXBoundary = findUpperBoxBoundary(xCoordinate, boxSize, boundaries);
        int upperYBoundary = findUpperBoxBoundary(yCoordinate, boxSize, boundaries);
        int[] boxMembers = findBoxMembers(gameGrid, boxSize, upperXBoundary, upperYBoundary);

        for(int boxMember : boxMembers){
            if (boxMember == value){
                return false;
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
    public static int[] findRowMembers(int[][] gameGrid, int yCoordinate){
        int[] rowMembers = new int[gameGrid.length];
        int i = 0;
        for(int rowMember : gameGrid[yCoordinate - 1]){
            rowMembers[i] = rowMember;
            i++;
        }
        return rowMembers;
    }

    public static int[] findColumnMembers(int[][] gameGrid, int xCoordinate){

        int[] columnMembers = new int[gameGrid.length];
        int i = 0;
        for (int[] row : gameGrid){
            columnMembers[i] = row[xCoordinate - 1];
        }
        return columnMembers;
    }
    private static int[] findBoxMembers(int[][] gameGrid, int boxSize, int upperXBoundary, int upperYBoundary){
        int[] boxMembers = new int[gameGrid.length];
        int lowerXBoundary = upperXBoundary - (boxSize - 1);
        int lowerYBoundary = upperYBoundary - (boxSize - 1);
        int i = 0;
        for(int rowIndex = lowerYBoundary; rowIndex < upperYBoundary; rowIndex++){
            for(int columnIndex = lowerXBoundary; columnIndex < upperXBoundary; columnIndex++){
                int squareValue = gameGrid[rowIndex][columnIndex];
                boxMembers[i] = squareValue;
                i++;
            }
        }
        return boxMembers;
    }

    public static int[] listSquareCandidateValues(int[][] gameGrid, int[] boundaries, int xCoordinate, int yCoordinate){
        List<Integer> possibleValues = new ArrayList<>();
        if(gameGrid[yCoordinate - 1][xCoordinate - 1] != 0){
            return new int[] {};
        }
        int valueToCheck = 1;
        for(int i = 0; i < gameGrid.length; i++){
            if(gridSquareInputValueIsValid(gameGrid, xCoordinate, yCoordinate, valueToCheck, boundaries)){
                possibleValues.add(valueToCheck);
            }
            valueToCheck++;
        }
        if(possibleValues.isEmpty()){
            return new int[] {};
        }
        int[] candidates = new int[possibleValues.size()];
        int i = 0;
        for(int v : possibleValues) {
            candidates[i] = v;
            i++;
        }
        Arrays.sort(candidates);
        return candidates;
    }
    public static int[][][] listPossibleValuesPerSquareAcrossGrid(int[][] gameGrid, int[] boundaries){
        int[][][] gridPossibleValues = new int[gameGrid.length][gameGrid.length][0];
        for(int xCoordinate = 1; xCoordinate < gameGrid.length + 1; xCoordinate++){
            for (int yCoordinate = 1; yCoordinate < gameGrid.length + 1; yCoordinate++){
                gridPossibleValues[yCoordinate - 1][xCoordinate - 1] = listSquareCandidateValues(gameGrid, boundaries, xCoordinate, yCoordinate);
            }
        }
        return gridPossibleValues;
    }
    public int[][] generatePuzzle(int dimensions){
        int[][] gameGrid = new int[dimensions][dimensions];
        int sqrtDimensions = 0;
        if(SudokuFunctions.gridDimensionsAreSquare(dimensions)){
            sqrtDimensions = (int)Math.sqrt(dimensions);
        } else {
            return null;
        }

        int[] boundaries = setBoundaries(sqrtDimensions);
        Stack<int[][]> gridHistory = new Stack<>();
        // starting with 1, fill the grid.



        return gameGrid;
    }


}
