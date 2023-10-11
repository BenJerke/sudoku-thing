package ben.sudoku;

import java.lang.Math;

public class GameGrid {
    private int[][] grid;
    public GameGrid(int dimensions) {
        this.grid = new int[dimensions][dimensions];
    }
    public int[][] getGrid() {
        return grid;
    }
    public void setGrid(int[][] newGrid){
        this.grid = newGrid;
    }
    public void setGridSquare(int x, int y, int value){
        this.grid[y][x] = value;
    }
}
