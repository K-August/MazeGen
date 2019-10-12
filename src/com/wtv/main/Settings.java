package com.wtv.main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Settings extends JFrame
{
    private Color first, second; 
    
    JColorChooser a = new JColorChooser();
    JColorChooser b = new JColorChooser();
    
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
        
        a.getSelectionModel().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent changeEvent)
            {
                 first = a.getColor();
                 Main.ins.start.setBackground(first);
            }
        });
        b.getSelectionModel().addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent changeEvent)
            {
                second = a.getColor();
            }
        });
        // endregion
        
        setVisible(true);
    }
}
