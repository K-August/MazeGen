package com.wtv.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Cell {
    public boolean visited;
    public boolean[] walls = {true, true, true, true};

    public int x, y, row, col;

    private int totalRows = Maze.getRows();
    private int totalCols = Maze.getCols();

    public Cell(int row, int col, int w) {
        this.row = row;
        this.col = col;
        x = col * w;
        y = (row * w) + 30;
    }

    public Cell getNextCell(Cell[][] grid) {
        return null;
    }

}
