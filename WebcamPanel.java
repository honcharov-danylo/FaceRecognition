package com.company;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_videoio;
import org.bytedeco.javacpp.presets.avutil;
import org.bytedeco.javacv.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_videoio.CV_FOURCC;
import static org.bytedeco.javacpp.opencv_videoio.cvWriteFrame;

/**
 * Created by leon on 25.06.16.
 */
public class WebcamPanel{
FrameGrabber grabber;
OpenCVFrameConverter.ToIplImage converter;
opencv_core.IplImage img;
BufferedImage temp;
CanvasFrame cv;
Rectangle face;
boolean condition=true;
    public WebcamPanel() {
        cv=new CanvasFrame("Webcam");
        cv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                condition=false;
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}});



        grabber=new OpenCVFrameGrabber("");
        face=new Rectangle(0,0,0,0);
        converter=new OpenCVFrameConverter.ToIplImage();
        try{
            grabber.start();
            cv.setSize(new Dimension(grabber.getImageWidth(), grabber.getImageHeight()));
        }
        catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getImage()
    {
        try {
            return Utils.IplImageToBufferedImage(converter.convert(grabber.grab()));
        }
        catch(FrameGrabber.Exception e){return null;}
    }
    public Dimension getPreferredSize() {
        return new Dimension(640,480);
    }
    public void run() {
        int counter=0;
      while(condition){
       try {
            img = converter.convert(grabber.grab());
            if (img != null) {
                //Flip image horizontally
                cvFlip(img, img, 1);
                temp=Utils.IplImageToBufferedImage(img);
                 if(face.width==0 || counter==10) {
                    face=FacePainter.findFace(img);
                     //temp=FacePainter.paintFace(temp,face);
                     counter=0;}
                temp=FacePainter.paintFace(temp,face);

                cv.showImage(temp);
                counter++;
             //   this.repaint();
            }
        }
        catch (FrameGrabber.Exception e)
        {e.printStackTrace();}
    }
    }
}
