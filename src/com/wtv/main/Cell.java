package com.wtv.main;

import java.awt.*;

public class Cell {
    public boolean visited = false;
    public boolean[] walls;

    public int x, y, row, col;

    public int w = Maze.WIDTH / 10;

    // i = rows j = columns

    public Cell(int row, int col, int w) {
        walls = new boolean[] {true, true, true, true};

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
    public void destroyWalls(Cell nxt) {
        if(this.col - nxt.col == -1) {
            this.walls[1] = false;
            nxt.walls[3] = false;
        } else if(this.col - nxt.col == 1) {
            this.walls[3] = false;
            nxt.walls[1] = false;
        } else if(this.row - nxt.row == -1) {
            this.walls[2] = false;
            nxt.walls[0] = false;
        } else if(this.row - nxt.row == 1) {
            this.walls[0] = false;
            nxt.walls[2] = false;
        }
    }
   
}

