package com.company;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;

import static org.bytedeco.javacpp.opencv_core.cvLoad;

/**
 * Created by leon on 24.06.16.
 */
public class PanelPainter extends Thread {
    WebcamPanel panel;
    Webcam webcam;
    public PanelPainter(WebcamPanel panel,Webcam webcam)
    {
        this.panel=panel;
        this.webcam=webcam;
    }
    @Override
    public void run() {
        super.run();

        while(true) {
            Graphics2D graph;
            graph=(Graphics2D) panel.getGraphics();
            graph.setColor(Color.green);
            graph.setStroke(new BasicStroke(10));
            BufferedImage photo=webcam.getImage();
            opencv_objdetect.CvHaarClassifierCascade cascade=new opencv_objdetect.CvHaarClassifierCascade(cvLoad("haarcascade_frontalface_alt.xml"));
            opencv_core.CvRect res=OpenCVFaceRecognition.getFaceDetected(photo,cascade);
            graph.drawRect(photo.getWidth()-res.x()-res.width(), res.y(), res.width(), res.height());
            graph.dispose();
            //panel.repaint();
        }
    }
}
