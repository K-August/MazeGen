package com.wtv.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Cell {
    public boolean visited;
    public boolean[] walls = {true, true, true, true};

    public int x, y, row, col;

    // i = rows j = columns

    public Cell(int row, int col, int w) {
        this.row = row;
        this.col = col;
        x = col * w;
        y = (row * w) + 30;
    }

    public static int index(int i, int j, int cols) {
        if(i < 0 || i > cols - 1 || j < 0 || j > cols - 1)
            return 0;
        else
            return i + j * cols;
    }
}
