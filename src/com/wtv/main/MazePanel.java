package com.wtv.main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MazePanel extends JPanel {

    private static final int WIDTH = Main.WIDTH - 100, HEIGHT = WIDTH;

    public MazePanel() {
        setSize(WIDTH, HEIGHT);
        setLocation((Main.WIDTH / 2)  - (this.WIDTH / 2), 50);

        setLayout(null);
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(Color.BLACK);
    }

}
