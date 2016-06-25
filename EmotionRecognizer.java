package com.company;

import org.bytedeco.javacpp.opencv_core;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.bytedeco.javacpp.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.javacpp.opencv_core.cvCreateImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;

/**
 * Created by leon on 24.06.16.
 */
public class EmotionRecognizer {

public EmotionRecognizer(BufferedImage originalImage) {
    int width = 50;
    int height = 50;
    Image image = originalImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
    BufferedImage changedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = changedImage.createGraphics();
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();
    opencv_core.IplImage greyImg;
    opencv_core.IplImage IplOriginal= Utils.BufferedImageToIplImage(originalImage);
    greyImg = cvCreateImage(new opencv_core.CvSize(IplOriginal.width(),IplOriginal.height()), IPL_DEPTH_8U, 1 );
    cvCvtColor( IplOriginal, greyImg, CV_BGR2GRAY );
    IplOriginal=greyImg;

    ///

}
}
