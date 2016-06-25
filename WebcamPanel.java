package com.company;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_core.cvFlip;

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
    public WebcamPanel() {
        cv=new CanvasFrame("Webcam");
        cv.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
      //  this.add(cv);
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
      while(true){
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
