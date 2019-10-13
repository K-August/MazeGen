package com.wtv.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Cell {
    public boolean visited;
    public boolean[] walls = {true, true, true, true};

    public int x, y;

    public Cell(int row, int col, int w) {
        x = col * w;
        y = (row * w) + 30;
    }
}
