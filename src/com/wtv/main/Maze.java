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

    public int w = WIDTH / 10;
    public int rows = HEIGHT / w, cols = WIDTH / w;


    public Cell current;

    public Stack<Cell> visited = new Stack<Cell>();
    public ArrayList<Cell> cells = new ArrayList<Cell>();

    public Maze() {
        super("maze");

        setBounds(0, 0, WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                cells.add(new Cell(i, j, w));
            }
        }

        current = cells.get(0);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                new Main();
            }
        });

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
        while(running) {
            tick();
            render();
        }
        stop();
    }

    //j = row i = col

    public Cell checkNeighbors(Cell curr) {
        ArrayList<Cell> neighbors = new ArrayList<Cell>();

        Cell top = (Cell.index(curr.col, curr.row - 1, cols) > -1) ? cells.get(Cell.index(curr.col, curr.row - 1, cols)) : null;
        Cell right = (Cell.index(curr.col + 1, curr.row, cols) > -1) ? cells.get(Cell.index(curr.col + 1, curr.row, cols)) : null;
        Cell bottom = (Cell.index(curr.col, curr.row + 1, cols) > -1) ? cells.get(Cell.index(curr.col, curr.row + 1, cols)) : null;
        Cell left = (Cell.index(curr.col - 1, curr.row, cols) > -1) ? cells.get((curr.col + curr.row - 1) * cols) : null;

        if(top != null && !top.visited) { neighbors.add(top); }
        if(right != null && !right.visited) { neighbors.add(right); }
        if(bottom != null && !bottom.visited) { neighbors.add(bottom); }
        if(left != null && !left.visited) { neighbors.add(left); }

        if(neighbors.size() > 0) {
            Random r = new Random();
            return neighbors.get(r.nextInt(neighbors.size()));
        }
        return null;
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

        Cell next = checkNeighbors(current);
        if(next != null)
            current = next;

        g.setColor(new Color(100, 0, 100));
        for(Cell c : cells)
            if(c.visited) g.fillRect(c.x, c.y, w, w);

        g.setColor(Color.WHITE);
        for(Cell c : cells){
            if(c.walls[0])
                g.drawLine(c.x, c.y, c.x + w, c.y);
            if(c.walls[1])
                g.drawLine(c.x + w, c.y, c.x + w, c.y + w);
            if(c.walls[2])
                g.drawLine(c.x + w, c.y + w, c.x, c.y + w);
            if(c.walls[3])
                g.drawLine(c.x, c.y + w, c.x, c.y);
        }

        g.dispose();
        bs.show();
    }

}
