package com.company.Garbage;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;

import javax.swing.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.cvCircle;
import static org.bytedeco.javacpp.opencv_imgproc.cvFindCornerSubPix;
import static org.bytedeco.javacpp.opencv_imgproc.cvGoodFeaturesToTrack;

/**
 * Created by leon on 26.06.16.
 */
public class test {

    static int corner_count = 200;
    static int [] corner_ct = {200};
    static opencv_core.IplImage dst_img;
    static opencv_core.IplImage dst_img2;
    static opencv_core.IplImage src_img_gray;
    static opencv_core.IplImage eig_img, tmp_img;

    static opencv_core.CvPoint2D32f corners;
    static String imgPath = "/home/leon/IdeaProjects/FaceRecognition/src.png";

    public static void test() {

        dst_img = cvLoadImage(imgPath, CV_LOAD_IMAGE_ANYCOLOR | CV_LOAD_IMAGE_ANYDEPTH);
        dst_img2 = cvCloneImage(dst_img);
        src_img_gray = cvLoadImage(imgPath, CV_LOAD_IMAGE_GRAYSCALE);

        eig_img = cvCreateImage(cvGetSize(src_img_gray), IPL_DEPTH_32F, 1);
        tmp_img = cvCreateImage(cvGetSize(src_img_gray), IPL_DEPTH_32F, 1);

        corners = new CvPoint2D32f(corner_count);

        IntPointer intPointer=new IntPointer(corner_ct);
        cvGoodFeaturesToTrack(src_img_gray, eig_img, tmp_img, corners, intPointer, 0.1d,1d, null, 3, 1, 0.1d);
        cvFindCornerSubPix (src_img_gray, corners, corner_count,
                cvSize (3, 3), cvSize (-1, -1), cvTermCriteria (CV_TERMCRIT_ITER | CV_TERMCRIT_EPS, 20, 0.03));

        System.out.print(corner_ct[0]);
        for (int i = 0; i < corner_ct[0]; i++){
            cvCircle(dst_img2, cvPointFrom32f(corners.position(i)), 3, CV_RGB(255, 0, 0), 1, 8, 0);
        }

        cvShowImage("aq", dst_img2);
        cvWaitKey();
		/*
		 		for (int i = 0; i < corner_ct[0]; i++){
				cvCircle(dst_img2, cvPointFrom32f(corners.position(i)), 3, CV_RGB(255, 0, 0), 1, 8, 0);
					System.out.print(cvPointFrom32f(corners).position(i).x() + " - " + cvPointFrom32f(corners).position(i).y());
 			}
		 */


    }
}
