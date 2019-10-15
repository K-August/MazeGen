package com.wtv.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze extends JFrame implements Runnable {
    private static final int WIDTH = 600, HEIGHT = 630;

    private boolean running = false;
    private Thread thread;

    public static int w = WIDTH / 10;
    public static int rows = HEIGHT / w, cols = WIDTH / w;

    public boolean canMoveUp = true, canMoveRight = true, canMoveDown = true, canMoveLeft = true;

    public Cell current;

    public Stack<Cell> visited = new Stack<Cell>();
    public Cell[][] grid = new Cell[rows][cols];

    public Maze() {
        super("maze");

        setBounds(0, 0, WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) grid[i][j] = new Cell(j, i, w);
        }

        current = grid[0][0];

        setVisible(true);
        this.start();
    }
    private void start() {
        thread = new Thread(this);
        thread.start();

        running = true;
    }
    private void stop() {
        try {
            thread.join();
            running = false;
        } catch(Exception e) { e.printStackTrace(); }
    }

    private void tick() { }

    public void run() {
        long past = System.currentTimeMillis();
        while(running) {
            long now = System.currentTimeMillis();
            tick();
            if(now - past > 100)
                render();
        }
        stop();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0 , WIDTH, HEIGHT);
        current.visited = true;

        //TODO canMoveUp, down, left, right to check neighbors

        // region Maze Generation Algorithm
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++) {
                if(grid[i][j].visited) {
                    g.setColor(new Color(100, 0, 100));
                    g.fillRect(grid[i][j].x, grid[i][j].y, w, w);
                }

                g.setColor(Color.WHITE);

                if(grid[i][j].walls[0])
                    g.drawLine(grid[i][j].x, grid[i][j].y, grid[i][j].x + w, grid[i][j].y);
                if(grid[i][j].walls[1])
                    g.drawLine(grid[i][j].x + w, grid[i][j].y, grid[i][j].x + w, grid[i][j].y + w);
                if(grid[i][j].walls[2])
                    g.drawLine(grid[i][j].x + w, grid[i][j].y + w, grid[i][j].x, grid[i][j].y + w);
                if(grid[i][j].walls[3])
                    g.drawLine(grid[i][j].x, grid[i][j].y + w, grid[i][j].x, grid[i][j].y);

            }
        }

        g.dispose();
        bs.show();
        //endregion
    }

    public static int getRows() { return cols; }
    public static int getCols() { return rows; }
}
