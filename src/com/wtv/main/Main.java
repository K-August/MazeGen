package com.wtv.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Main extends JFrame implements Runnable {
    public static final int WIDTH = 600, HEIGHT = 800;

    private Thread thread;
    private boolean running;

    JButton start = new JButton("Begin");
    JButton exit = new JButton("Quit");

    public Main() {
        super("Maze Gen Thing");
        running = false;

        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start.setSize(200, 50);
        start.setLocation(WIDTH / 2 - 100, HEIGHT - 200);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("yo! you clicked me!");
            }
        });

        exit.setSize(150, 50);
        exit.setLocation(WIDTH / 2 - 75, HEIGHT - 125);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("you trynna leave?!");
            }
        });

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

    private void renderComponent() {
        repaint();
    }

    public void run() {
        long past = System.currentTimeMillis();
        while(running) {
            long now = System.currentTimeMillis();
            tick();
            if((now - past) / 1000 == 60)
                renderComponent();
        }
        stop();
    }

    public static void main(String[] args) {
        new Main();
    }
}
