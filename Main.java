package com.company;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
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
	Webcam webcam=Webcam.getDefault();
   // webcam.open();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        JFrame window = new JFrame("Test webcam panel");

        WebcamPanel panel = new WebcamPanel(webcam);


        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BufferedImage photo=webcam.getImage();
                opencv_objdetect.CvHaarClassifierCascade cascade=new opencv_objdetect.CvHaarClassifierCascade(cvLoad("haarcascade_frontalface_alt.xml"));
                opencv_core.CvRect res= OpenCVFaceRecognition.getFaceDetected(photo,cascade);
                Graphics2D graph = photo.createGraphics();
                graph.setColor(Color.green);
                graph.draw3DRect(res.x(), res.y(), res.width(), res.height(),false);
                graph.dispose();
                try{
                ImageIO.write(photo,"png",new File("temp.png"));}
                catch (IOException e1)
                {
                 e1.printStackTrace();
                }

              //  Memory.getInstance().setRectangle(new Rectangle(res.x(),res.y(),res.width(),res.height()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        window.add(panel);


        window.setResizable(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        PanelPainter panelPainter=new PanelPainter(panel,webcam);
        panelPainter.run();
    }
}
