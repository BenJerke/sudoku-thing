package ben.sudoku;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SudokuFunctionsTest {
    // set up test game grids
    private final int NINE_BY_NINE_GRID_LENGTH = 9;
    private int[][] valid9x9GameGrid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];
    private int[][] badRow9x9Grid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];
    private int[][] badColumn9x9Grid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];
    private int[] validRow1 = new int[] {4,3,5,2,6,9,7,8,1};
    private int[] validRow2 = new int[] {6,8,2,5,7,1,4,9,3};
    private int[] validRow3 = new int[] {1,9,7,8,3,4,5,6,2};
    private int[] validRow4 = new int[] {8,2,6,1,9,5,3,4,7};
    private int[] validRow5 = new int[] {3,7,4,6,8,2,9,1,5};
    private int[] validRow6 = new int[] {9,5,1,7,4,3,6,2,8};
    private int[] validRow7 = new int[] {5,1,9,3,2,6,8,7,4};
    private int[] validRow8 = new int[] {2,4,8,9,5,7,1,3,6};
    private int[] validRow9 = new int[] {7,6,3,4,1,8,2,5,9};

    @Before
    public void setup_valid_grid(){
        valid9x9GameGrid[0] = validRow1;
        valid9x9GameGrid[1] = validRow2;
        valid9x9GameGrid[2] = validRow3;
        valid9x9GameGrid[3] = validRow4;
        valid9x9GameGrid[4] = validRow5;
        valid9x9GameGrid[5] = validRow6;
        valid9x9GameGrid[6] = validRow7;
        valid9x9GameGrid[7] = validRow8;
        valid9x9GameGrid[8] = validRow9;

    }
    @After
    public void reset_valid_grid(){
        valid9x9GameGrid[0] = validRow1;
        valid9x9GameGrid[1] = validRow2;
        valid9x9GameGrid[2] = validRow3;
        valid9x9GameGrid[3] = validRow4;
        valid9x9GameGrid[4] = validRow5;
        valid9x9GameGrid[5] = validRow6;
        valid9x9GameGrid[6] = validRow7;
        valid9x9GameGrid[7] = validRow8;
        valid9x9GameGrid[8] = validRow9;

    }

    @Test
    public void gridDimensionsAreSquare_returns_true_for_square_dimensions(){
        int square = 9;
        int unSquare = 24;
        Assert.assertTrue("Square number returned false: " + (int)Math.sqrt(9),SudokuFunctions.gridDimensionsAreSquare(square));
        Assert.assertFalse("Non-square number returned true.", SudokuFunctions.gridDimensionsAreSquare(unSquare));
    }
    @Test
    public void setBoundaries_identifies_correct_intervals(){
        int[] valid9x9Boundaries = new int[] {0, 2, 3, 5, 6, 8};
        int sqrt9 = 3;
        Assert.assertArrayEquals("Incorrect 9x9 boundaries: " + SudokuFunctions.setBoundaries(sqrt9), valid9x9Boundaries, SudokuFunctions.setBoundaries(sqrt9));
    }

    @Test
    public void coordinateInputIsValid_respects_game_grid_length(){
        final int valid9x9GridLength = 8;
        final int validUpperCoordinate = 9;
        final int validLowerCoordinate = 1;
        Assert.assertTrue("Failed to recognize valid coordinate: " + validUpperCoordinate, SudokuFunctions.coordinateInputIsValid(valid9x9GridLength, validUpperCoordinate));
        Assert.assertTrue("Failed to recognize valid coordinate: " + validLowerCoordinate, SudokuFunctions.coordinateInputIsValid(valid9x9GridLength, validLowerCoordinate));
    }

    @Test
    public void gridSquareInputValueIsValid_identifies_invalid_row_members() {
        Assert.assertFalse("Invalid row entry missed.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 1, 1, 9, SudokuFunctions.setBoundaries(3)));
        valid9x9GameGrid[0][0] = 0;
        /*
        box members:
        4, 3, 5
        6, 8, 2
        1, 9, 7
         */
        Assert.assertTrue("Valid row entry flagged.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 1, 1, 4, SudokuFunctions.setBoundaries(3)));
    }

    @Test
    public void gridSquareInputValueIsValid_identifies_invalid_column_members(){
        valid9x9GameGrid[4][6] = 0;
        Assert.assertFalse("Invalid column entry missed.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 5, 5, 1, SudokuFunctions.setBoundaries(3)));
        valid9x9GameGrid[4][4] = 0;
        Assert.assertTrue("Valid column entry flagged.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 5, 5, 8, SudokuFunctions.setBoundaries(3)));
    }

    @Test
    public void gridSquareInputValueIsValid_identifies_invalid_box_members(){
        // make the row and column valid
        valid9x9GameGrid[4][8] = 0;
        valid9x9GameGrid[7][4] = 0;
        Assert.assertFalse("Invalid box entry missed.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 5,5, 5, SudokuFunctions.setBoundaries(3)));
        valid9x9GameGrid[3][5] = 0;
        Assert.assertTrue("Valid box entry flagged.", SudokuFunctions.gridSquareInputValueIsValid(valid9x9GameGrid, 5, 5, 5, SudokuFunctions.setBoundaries(3)));
    }



}
