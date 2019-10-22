package com.wtv.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Settings extends JFrame
{
    public static boolean isEpic;
    public static Settings ins;
    public static JColorChooser a = new JColorChooser();
    
    private JButton button = new JButton("Epic Mode");
    
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
        button.setSize(150, 150);
        
        a.setLocation(100, 100);
        button.setLocation(Main.WIDTH / 2, Main.HEIGHT / 2);

        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLACK);

        add(a);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEpic = !isEpic;
            }
        });
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
