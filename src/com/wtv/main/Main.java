package com.wtv.main;

import javax.swing.*;

public class Main extends JFrame implements Runnable
{
    public static final int WIDTH = 600, HEIGHT = 800;
    private boolean running;

    public Main()
    {
        super("Maze Generator");

        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        new Main();
    }

    public void run() {

    }
}
