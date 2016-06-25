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
public class FacePainter {
    public static Rectangle findFace(opencv_core.IplImage photo) {
            opencv_objdetect.CvHaarClassifierCascade cascade=new opencv_objdetect.CvHaarClassifierCascade(cvLoad("haarcascade_frontalface_alt.xml"));
            opencv_core.CvRect res=OpenCVFaceRecognition.getFaceDetected(photo,cascade);
            return new Rectangle(res.x(),res.y(),res.width(),res.height());
        }
    public static BufferedImage paintFace(BufferedImage photo,Rectangle face)
    {
        Graphics2D graphics2D=(Graphics2D)photo.getGraphics();
        graphics2D.setColor(Color.green);
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.drawRect(face.x, face.y, face.width, face.height);
        graphics2D.dispose();
        return photo;
    }
}
