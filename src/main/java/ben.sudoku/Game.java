package ben.sudoku;

import java.util.*;
import java.lang.Math;

public class Game {

    private int[][] gameGrid;
    private Stack<int[][]> gameGridHistory;

    private int[] boundaries;

    public Game(int dimensions){
        this.gameGrid = new int[dimensions][dimensions];
        this.gameGridHistory = new Stack<>();
        this.gameGridHistory.add(this.gameGrid);
    }

    public Game(int[][] gameGrid, Stack<int[][]> gameGridHistory) {
        this.gameGrid = gameGrid;
        this.gameGridHistory = gameGridHistory;
    }
    public Game(int[][] gameGrid, Stack<int[][]> gameGridHistory, int[] boundaries) {
        this.gameGrid = gameGrid;
        this.boundaries = boundaries;
        this.gameGridHistory = gameGridHistory;
    }
    public Game() {
    }

    public void makeMove(int xCoordinate, int yCoordinate, int validatedValue){
        int[][] newGrid = this.gameGrid.clone();
        newGrid[yCoordinate][xCoordinate] = validatedValue;
        updateGameGrid(newGrid);
        addMoveToHistory(newGrid);
    }

    public void undoMove(){
        this.gameGrid = removeMoveFromHistory();
    }

    private void addMoveToHistory(int[][] newGrid){
        this.gameGridHistory.add(newGrid);
    }

    private int[][] removeMoveFromHistory(){
        return this.gameGridHistory.pop();
    }

    private void updateGameGrid(int[][] newGrid){
        this.gameGrid = newGrid;
    }

    public int[][] getGameGrid() {
        return gameGrid;
    }

    public Stack<int[][]> getGameGridHistory() {
        return gameGridHistory;
    }

    public void setGameGrid(int[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void setGameGridHistory(Stack<int[][]> gameGridHistory) {
        this.gameGridHistory = gameGridHistory;
    }
}
