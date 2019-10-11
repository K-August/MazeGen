package com.wtv.main;

import java.awt.*;

public class Cell {

    public Point pos;

    public int w;

    public boolean visited;
    public boolean[] walls = {true, true, true, true};

    public Cell(Point pos, int width) {
        this.w = width;

        this.visited = false;
        this.pos = pos;
    }

}
