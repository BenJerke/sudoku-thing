package ben.sudoku;


import java.util.*;
import java.util.List;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

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
    private static int[] findRowMembers(int[][] gameGrid, int yCoordinate){
        int[] rowMembers = new int[gameGrid.length];
        int i = 0;
        for(int rowMember : gameGrid[yCoordinate - 1]){
            rowMembers[i] = rowMember;
            i++;
        }
        return rowMembers;
    }

    private static int[] findColumnMembers(int[][] gameGrid, int xCoordinate){

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
        int sqrtDimensions;
        if(SudokuFunctions.gridDimensionsAreSquare(dimensions)){
            sqrtDimensions = (int)Math.sqrt(dimensions);
        } else {
            return null;
        }

        int[] boundaries = setBoundaries(sqrtDimensions);
        Stack<int[][]> gridHistory = new Stack<>();


        return gameGrid;
    }

    private int generateNumberBetween1AndGridLength(int dimensions){
        final int FLOOR = 1;
        Random rando = new Random();
        int r = rando.nextInt(dimensions - FLOOR) + FLOOR;
        return r;
    }

    public static int[] findSquareWithFewestCandidateValues(int [][][]gridPossibleValues){
        // start at the highest possible count.
        int [] squareCoordinates = new int[2];
        int lowestCandidateCount = gridPossibleValues.length;
        int currentSquareCandidateCount;
        for (int y = 0; y < gridPossibleValues.length; y++){
            if(lowestCandidateCount == 1){
                break;
            }
            for (int x = 0; x < gridPossibleValues.length; x++){
                if(gridPossibleValues[y][x].length == 0){
                    continue;
                }
                currentSquareCandidateCount = gridPossibleValues[y][x].length;
                if(currentSquareCandidateCount < lowestCandidateCount){
                    lowestCandidateCount = currentSquareCandidateCount;
                    squareCoordinates[0] = y;
                    squareCoordinates[1] = x;
                }
                if(lowestCandidateCount == 1){
                    // lowest possible outcome - break out of both loops and return.
                    break;
                }
            }
        }
        return squareCoordinates;
    }

    public int findBestValueToSet(int[] coordinateIndices, int[][][] gridPossibleValues){
        // Assuming that the right approach is to go random > least possible values
        // of the values our square could accommodate, which one occurs the least in our grid right now?
        // we should never ever ever get an empty square as input.
        int yCoordinate = coordinateIndices[0], xCoordinate = coordinateIndices[1];
        int[] squarePossibleValues = gridPossibleValues[yCoordinate][xCoordinate];

        Map<Integer, Integer> digitCounts = new HashMap<>();
        int valueToSet = squarePossibleValues[0];
        if(squarePossibleValues.length == 1){
            return valueToSet;
        }

        for (int y = 0; y < gridPossibleValues.length; y++){
            for (int x = 0; x < gridPossibleValues.length; x++){
                for(int i = 0; i < gridPossibleValues[y][x].length; i++){
                    if(gridPossibleValues[y][x].length == 0){
                        continue;
                    }
                    if(digitCounts.get(gridPossibleValues[y][x][i]) == null){
                        digitCounts.put(gridPossibleValues[y][x][i], 1);
                    } else {
                        digitCounts.put(gridPossibleValues[y][x][i], digitCounts.get(gridPossibleValues[y][x][i]) + 1);
                    }
                }
            }
        }
        // starting with the maximum possible count of values, find the least-used value.
        int fewestValueCount = gridPossibleValues.length * gridPossibleValues.length;
        for (Map.Entry<Integer, Integer> digitCount : digitCounts.entrySet()){
            if (digitCount.getValue() < fewestValueCount){
                fewestValueCount = digitCount.getValue();
                valueToSet = digitCount.getKey();
            }
        }
        // get a count of all the possible values in each
        return valueToSet;
    }

}
