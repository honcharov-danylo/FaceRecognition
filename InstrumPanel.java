package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by leon on 26.06.16.
 */
public class InstrumPanel extends JPanel {
    public InstrumPanel(){
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Memory mem=Memory.getInstance();
        if(mem.image!=null) {
            g.drawImage(Memory.getInstance().image.getScaledInstance(Memory.getInstance().image.getWidth() / 4, Memory.getInstance().image.getWidth() / 4, 1), 0, 0, null);
        }
    }
}
