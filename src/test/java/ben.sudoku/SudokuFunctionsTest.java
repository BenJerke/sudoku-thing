package ben.sudoku;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SudokuFunctionsTest {
    // set up test game grids
    private final int NINE_BY_NINE_GRID_LENGTH = 8;
    private int[][] valid9x9GameGrid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];
    private int[][] badRow9x9Grid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];
    private int[][] badColumn9x9Grid = new int[NINE_BY_NINE_GRID_LENGTH][NINE_BY_NINE_GRID_LENGTH];

    @Before
    private void setup_valid_grid(){
        valid9x9GameGrid [0][0] = 4;
        valid9x9GameGrid [0][1] = 3;
        valid9x9GameGrid [0][2] = 5;
        
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
    public void gridSquareInputValueIsValid_identifies_invalid_row_members(){

    }

}
