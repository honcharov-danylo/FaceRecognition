package com.company;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;

import javax.swing.*;

import static com.company.Utils.IplImageToBufferedImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;

/**
 * Created by leon on 25.06.16.
 */
public class OpenCVCamera {
    public OpenCVCamera() {
        //Create canvas frame for displaying webcam.
        CanvasFrame canvas = new CanvasFrame("Webcam");
        //Set Canvas frame to close on exit
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Declare FrameGrabber to import output from webcam
        FrameGrabber grabber = new OpenCVFrameGrabber("");
        try {
            //Start grabber to capture video
            grabber.start();
            //Declare img as IplImage
            opencv_core.IplImage img;
            while (true) {
                //inser grabed video fram to IplImage img
                //img = grabber.grab();
                OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
                img = converter.convert(grabber.grab());
                //Set canvas size as per dimentions of video frame.
                canvas.setCanvasSize(grabber.getImageWidth(), grabber.getImageHeight());
                if (img != null) {
                    //Flip image horizontally
                    cvFlip(img, img, 1);
                    //Show video frame in canvas
                    canvas.showImage(IplImageToBufferedImage(img));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

