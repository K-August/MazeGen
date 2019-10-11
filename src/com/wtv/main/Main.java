package com.wtv.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends JFrame implements Runnable {
    public static final int WIDTH = 600, HEIGHT = 800;

    private Thread thread;
    private boolean running;

    public Main() {
        super("Maze Gen Thing");
        running = false;

        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.start();
    }

    private synchronized void start() {
        thread = new Thread(this);
        thread.start();

        running = true;
    }
    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch(Exception e) { e.printStackTrace(); }
    }

    private void tick() { }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public void run() {
        while(running) {
            tick();
            render();
        }
        stop();
    }

    public static void main(String[] args) {
        new Main();
    }
}
