package com.wtv.main;

import javax.swing.*;

public class SettingsFrame extends JFrame implements Runnable {

    private Thread thread;
    private boolean running = false;


    public SettingsFrame() {
        super("Settings");
        setBounds(0, 0, Main.WIDTH - 100, Main.HEIGHT - 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

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
            new Main();
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
}
