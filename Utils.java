package com.company;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by leon on 25.06.16.
 */
public class Utils {
    public static BufferedImage IplImageToBufferedImage(opencv_core.IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        org.bytedeco.javacv.Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame,1);
    }

    public static opencv_core.IplImage BufferedImageToIplImage(BufferedImage src)
    {
        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        return iplConverter.convert(java2dConverter.convert(src));
    }

    public void CreateFileForNN(String path){

    }
    public static void showImage(BufferedImage img)
    {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        JLabel label = new JLabel( new ImageIcon(img));
        dialog.add( label );
        dialog.pack();
        dialog.setVisible(true);
    }
    public static void showText(String text)
    {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        JLabel label = new JLabel(text);
        dialog.add( label );
        dialog.pack();
        dialog.setVisible(true);
    }
}
