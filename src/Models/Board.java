package Models;

public class Board {

    private char[][] grid;

    public Board(int width, int height) {
        this.grid = new char[width][height];
    }

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }
}
