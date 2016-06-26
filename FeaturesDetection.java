package com.company;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.flandmark;
import org.bytedeco.javacpp.opencv_core;

import java.util.ArrayList;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvShowImage;
import static org.bytedeco.javacpp.opencv_highgui.cvWaitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * Created by leon on 26.06.16.
 */
public class FeaturesDetection {
    public static CvPoint2D32f getHarrisCorners(opencv_core.IplImage dst_img) {
      int corners_count=50;
      opencv_core.IplImage src_img_gray=new opencv_core.IplImage();
      cvCvtColor(dst_img,src_img_gray,COLOR_BGR2GRAY);
      opencv_core.CvPoint2D32f corners = new opencv_core.CvPoint2D32f(corners_count);
      int [] corner_ct = {corners_count};
      opencv_core.IplImage dst_img2;
      opencv_core.IplImage eig_img, tmp_img;
      dst_img2 = cvCloneImage(dst_img);

        eig_img = cvCreateImage(cvGetSize(src_img_gray), IPL_DEPTH_32F, 1);
        tmp_img = cvCreateImage(cvGetSize(src_img_gray), IPL_DEPTH_32F, 1);

        IntPointer intPointer=new IntPointer(corner_ct);
        cvGoodFeaturesToTrack(src_img_gray, eig_img, tmp_img, corners, intPointer, 0.1d,1d, null, 3, 1, 0.1d);
        cvFindCornerSubPix (src_img_gray, corners, corners_count,
                cvSize (3, 3), cvSize (-1, -1), cvTermCriteria (CV_TERMCRIT_ITER | CV_TERMCRIT_EPS, 20, 0.03));

       // for (int i = 0; i < corner_ct[0]; i++){
       //     cvCircle(dst_img2, cvPointFrom32f(corners.position(i)), 3, CV_RGB(255, 0, 0), 1, 8, 0);
       // }

       // cvShowImage("aq", dst_img2);
       // cvWaitKey();
        return corners;
    }
}
