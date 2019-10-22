package com.wtv.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Settings extends JFrame
{
    
    public static JColorChooser a = new JColorChooser();
    public static JColorChooser b = new JColorChooser();
    
    public Settings()
    {
        super("Maze Gen Color Settings");

        setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // region Color Choosers
        a.setSize(400, 200);
        b.setSize(400, 200);
        
        a.setLocation(100, 100);
        b.setLocation(100, 300);
        
        add(a);
        add(b);
        // endregion

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Main.ins.setVisible(true);
            }
        });

        setVisible(true);
    }
}
