package com.company;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_objdetect;
import org.bytedeco.javacv.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;

import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_DO_ROUGH_SEARCH;
import static org.bytedeco.javacpp.opencv_objdetect.CV_HAAR_FIND_BIGGEST_OBJECT;
import static org.bytedeco.javacpp.opencv_objdetect.*;

/**
 * Created by leon on 24.06.16.
 */
public class OpenCVFaceRecognition {
    public static CvRect getFaceDetected(BufferedImage inputImg1, opencv_objdetect.CvHaarClassifierCascade cascade)
    {
        Loader.load(opencv_objdetect.class); //Bugmakers, lol

        IplImage inputImg=BufferedImageToIplImage(inputImg1);
        CvSize minFeatureSize=new CvSize(20,20);
        // How detailed should the search be.
        float search_scale_factor = 1.1f;
        IplImage detectImg;
        IplImage greyImg;
        CvMemStorage storage;
        CvRect rc;
        double t;
        CvSeq rects;
        CvSize size;
        int i, ms, nFaces;
        storage = cvCreateMemStorage(0);
        cvClearMemStorage( storage );
        detectImg=inputImg;
        size = cvSize(inputImg.width(), inputImg.height());
        greyImg = cvCreateImage(size, IPL_DEPTH_8U, 1 );
        cvCvtColor( inputImg, greyImg, CV_BGR2GRAY );
        detectImg = greyImg;
        t = (double)cvGetTickCount();
        rects = cvHaarDetectObjects( detectImg, cascade, storage,
                search_scale_factor, 3, CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH);
        t = (double)cvGetTickCount() - t;
        ms = cvRound( t / ((double)cvGetTickFrequency() * 1000.0) );
        nFaces = rects.total();

        // Get the first detected face (the biggest).
        if (nFaces > 0)
            rc = new CvRect(cvGetSeqElem( rects, 0 ));
        else
        rc = cvRect(-1,-1,-1,-1);	// Couldn't find the face.

        cvReleaseImage( greyImg );
        cvReleaseMemStorage( storage );
        //cvReleaseHaarClassifierCascade( &cascade );

        return rc;	// Return the biggest face found, or (-1,-1,-1,-1).
    }
    public static BufferedImage IplImageToBufferedImage(IplImage src) {
        OpenCVFrameConverter.ToIplImage grabberConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        org.bytedeco.javacv.Frame frame = grabberConverter.convert(src);
        return paintConverter.getBufferedImage(frame,1);
    }
    public static IplImage BufferedImageToIplImage(BufferedImage src)
    {
        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        IplImage iplImage = iplConverter.convert(java2dConverter.convert(src));
        return iplImage;
    }

}
