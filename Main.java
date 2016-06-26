package com.company;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.opencv_core.cvLoad;

public class Main {

    public static void main(String[] args) {
  //     OpenCVCamera openCVCamera=new OpenCVCamera();
        com.company.WebcamPanel panel=new com.company.WebcamPanel();
        panel.start();
   // try{ FlandmarkClass.main("src.png");} catch (Exception e){e.printStackTrace();}
        // test.test();
//        JFrame window = new JFrame("Test webcam panel");
//        window.add(new InstrumPanel());
//
//
//        window.setPreferredSize(new Dimension(300,300));
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.pack();
//        window.setVisible(true);
        //PanelPainter panelPainter=new PanelPainter(panel);
        //panelPainter.run();
    }
}
