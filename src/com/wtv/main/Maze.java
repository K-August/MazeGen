package com.wtv.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Maze extends JFrame implements Runnable {
    private static final int WIDTH = 600, HEIGHT = 630;

    private boolean running = false;
    private Thread thread;

    public int w = WIDTH / 10;
    public int row = HEIGHT / w, col = WIDTH / w;

    ArrayList<Cell> cells = new ArrayList<Cell>();

    public Maze() {
        super("maze");

        setBounds(0, 0, WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                cells.add(new Cell(i, j, w));
            }
        }

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

    private void renderComponent() {
        render();
    }

    public void run() {
        while(running) {
            tick();
            renderComponent();
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

        g.setColor(Color.WHITE);

        for(Cell c : cells){
            g.drawLine(c.x, c.y, c.x + w, c.y);
            g.drawLine(c.x + w, c.y, c.x + w, c.y + w);
            g.drawLine(c.x + w, c.y + w, c.x, c.y + w);
            g.drawLine(c.x, c.y + w, c.x, c.y);
        }

        g.dispose();
        bs.show();
    }

}
