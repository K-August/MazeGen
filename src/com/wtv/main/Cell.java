package com.wtv.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Cell {
    public boolean visited = false;
    public boolean[] walls = {true, true, true, true};

    public int x, y, row, col;

    public int w = Maze.WIDTH / 10;

    // i = rows j = columns

    public Cell(int row, int col, int w) {
        this.row = row;
        this.col = col;
        x = col * w;
        y = (row * w) + 30;
    }

    public void drawWalls(Graphics g) {
        g.setColor(Color.WHITE);
        if(this.walls[0]){
            g.drawLine(x, y, x + w, y);
        }
        if(this.walls[1]){
            g.drawLine(x + w, y, x + w, y + w);
        }
        if(this.walls[2]){
            g.drawLine(x + w, y + w, x, y + w);
        }
        if(this.walls[3]){
            g.drawLine(x, y + w, x, y);
        }
    }

    public static int index(int i, int j, int cols) {
        if(i < 0 || i > cols - 1 || j < 0 || j > cols - 1)
            return 0;
        else
            return i + j * cols;
    }
}
