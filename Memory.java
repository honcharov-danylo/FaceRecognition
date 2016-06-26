package com.company;

import org.bytedeco.javacpp.opencv_core;

import java.awt.image.BufferedImage;

/**
 * Created by leon on 26.06.16.
 */
public class Memory {
    private static Memory ourInstance = new Memory();
    public volatile opencv_core.CvPoint2D32f corners;
    public volatile BufferedImage image;
    public static Memory getInstance() {
        return ourInstance;
    }

    private Memory() {
    }
}
