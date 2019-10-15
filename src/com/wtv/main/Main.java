package com.wtv.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class Main extends JFrame implements Runnable {
    public static final int WIDTH = 600, HEIGHT = 800;

    public static Main ins;

    private Thread thread;
    private boolean running;

    JButton start = new JButton("Begin");
    JButton exit = new JButton("Quit");
    JButton settings = new JButton("Color Settings");

    public Main() {
        // region Window Settings
        super("Maze Generator v0.1");
        
        running = false;

        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //endregion
        
        // region Start Button
        start.setSize(150, 100);
        start.setLocation(WIDTH / 2 - 25, HEIGHT - 175);
        start.setBackground(Color.GREEN);
        start.setForeground(Color.BLACK);
        add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Maze();
                setVisible(false);
            }
        });
        //endregion
        
        // region Quit Button
        exit.setSize(150, 50);
        exit.setLocation(WIDTH / 3 - 75, HEIGHT - 125);
        exit.setBackground(Color.RED);
        exit.setForeground(Color.BLACK);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("you trynna leave?!");
                System.exit(0);
            }
        });
        // endregion

        // region Settings Button
        settings.setSize(150, 50);
        settings.setLocation(WIDTH / 3 - 75, HEIGHT - 175);
        settings.setBackground(Color.BLACK);
        settings.setForeground(Color.WHITE);
        add(settings);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Settings();
                setVisible(false);
            }
        });
        // endregion

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
        ins = new Main();
    }
}
