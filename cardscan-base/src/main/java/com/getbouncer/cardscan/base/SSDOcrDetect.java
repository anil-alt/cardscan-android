
package com.getbouncer.cardscan.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import com.getbouncer.cardscan.base.ssd.ArrUtils;
import com.getbouncer.cardscan.base.ssd.DetectedOcrBox;
import com.getbouncer.cardscan.base.ssd.OcrPriorsGen;
import com.getbouncer.cardscan.base.ssd.PredictionAPI;
import com.getbouncer.cardscan.base.ssd.Result;

import java.util.Collections;
import java.util.List;

import java.util.ArrayList;


public class SSDOcrDetect {
    private static SSDOcrModel ssdOcrModel = null;
    private static float[][] priors = null;

    /** to store YMin values of all boxes */
    private List<Float> yMinArray = new ArrayList<Float>();

    /** to store the YMax values of all the boxes */
    private List<Float> yMaxArray = new ArrayList<Float>();


    public List<DetectedOcrBox> objectBoxes = new ArrayList<>();
    boolean hadUnrecoverableException = false;

    /** We don't use the following two for now */
    public static boolean USE_GPU = false;

    static boolean isInit() {
        return ssdOcrModel != null;
    }

    private String ssdOutputToPredictions(Bitmap image){
        ArrUtils arrUtils = new ArrUtils();

        float[][] k_boxes = arrUtils.rearrangeOCRArray(ssdOcrModel.outputLocations, ssdOcrModel.featureMapSizes,
                ssdOcrModel.NUM_OF_PRIORS_PER_ACTIVATION, ssdOcrModel.NUM_OF_CORDINATES);
        k_boxes = arrUtils.reshape(k_boxes, ssdOcrModel.NUM_OF_PRIORS, ssdOcrModel.NUM_OF_CORDINATES);
        k_boxes = arrUtils.convertLocationsToBoxes(k_boxes, priors,
                ssdOcrModel.CENTER_VARIANCE, ssdOcrModel.SIZE_VARIANCE);
        k_boxes = arrUtils.centerFormToCornerForm(k_boxes);
        float[][] k_scores = arrUtils.rearrangeOCRArray(ssdOcrModel.outputClasses, ssdOcrModel.featureMapSizes,
                ssdOcrModel.NUM_OF_PRIORS_PER_ACTIVATION, ssdOcrModel.NUM_OF_CLASSES);
        k_scores = arrUtils.reshape(k_scores, ssdOcrModel.NUM_OF_PRIORS, ssdOcrModel.NUM_OF_CLASSES);
        k_scores = arrUtils.softmax2D(k_scores);

        PredictionAPI predAPI = new PredictionAPI();
        Result result = predAPI.predictionAPI(k_scores, k_boxes, ssdOcrModel.PROB_THRESHOLD,
                ssdOcrModel.IOU_THRESHOLD, ssdOcrModel.CANDIDATE_SIZE, ssdOcrModel.TOP_K);
        if (result.pickedBoxProbs.size() != 0 && result.pickedLabels.size() != 0)
        {
            for (int i = 0; i < result.pickedBoxProbs.size(); ++i){
                DetectedOcrBox ocrBox = new DetectedOcrBox(
                        result.pickedBoxes.get(i)[0], result.pickedBoxes.get(i)[1],
                        result.pickedBoxes.get(i)[2], result.pickedBoxes.get(i)[3],result.pickedBoxProbs.get(i),
                        image.getWidth(), image.getHeight(),result.pickedLabels.get(i));

                objectBoxes.add(ocrBox);

                /** add the YMin value of the current box */
                yMinArray.add(result.pickedBoxes.get(i)[1]*image.getHeight());
                /** add the YMax value of the current box */
                yMaxArray.add(result.pickedBoxes.get(i)[3]*image.getHeight());
            }
        }
        String numberOCR = "";
        Collections.sort(objectBoxes);
        Collections.sort(yMinArray);
        Collections.sort(yMaxArray);
        float medianYMin = 0;
        float medianYMax = 0;
        float medianHeight = 0;
        float medianYCenter = 0;

        if (!yMaxArray.isEmpty() && !yMinArray.isEmpty()) {
            medianYMin = yMinArray.get(yMinArray.size() / 2);
            medianYMax = yMaxArray.get(yMaxArray.size() / 2);
            medianHeight = Math.abs(medianYMax - medianYMin);
            medianYCenter = (medianYMax + medianYMin) / 2;
        }

        StringBuilder num = new StringBuilder();
        for (DetectedOcrBox box : objectBoxes){
            if (box.label == 10){
                box.label = 0;
            }
            float boxYCenter = (box.YMax +  box.YMin) / 2;

            if (Math.abs(boxYCenter - medianYCenter) > medianHeight)
            {
                Log.e("Don't add this box",
                        String.valueOf(box.YMin) + String.valueOf(box.YMax));
            }
            else
            {
                num.append(String.valueOf(box.label));
            }


        }
        if (CreditCardUtils.isValidCardNumber(num.toString())){
            numberOCR = num.toString();
            Log.d("OCR Number passed", numberOCR);
        } else {
            Log.d("OCR Number failed", num.toString());
            numberOCR = null;
        }

        return numberOCR;


    }

    private String runModel(Bitmap image) {
        final long startTime = SystemClock.uptimeMillis();

        /**Run SSD Model and use the prediction API to post process
         * the model output */

        ssdOcrModel.classifyFrame(image);
        Log.d("Before SSD Post Process", String.valueOf(SystemClock.uptimeMillis() - startTime));
        String number = ssdOutputToPredictions(image);
        Log.d("After SSD Post Process", String.valueOf(SystemClock.uptimeMillis() - startTime));

        return number;
    }

    public synchronized String predict(Bitmap image, Context context) {
        final int NUM_THREADS = 4;
        try {
            boolean createdNewModel = false;

            try{
                if (ssdOcrModel == null){
                    ssdOcrModel = new SSDOcrModel(context);
                    /** Since all the frames use the same set of priors
                     * We generate these once and use for all the frame
                     */
                    if ( priors == null){
                        priors = OcrPriorsGen.combinePriors();
                    }

                }
            } catch (Error | Exception e){
                Log.e("SSD", "Couldn't load ssd", e);
            }


            try {
                return runModel(image);
            } catch (Error | Exception e) {
                Log.i("ObjectDetect", "runModel exception, retry object detection", e);
                ssdOcrModel = new SSDOcrModel(context);
                return runModel(image);
            }
        } catch (Error | Exception e) {
            Log.e("ObjectDetect", "unrecoverable exception on ObjectDetect", e);
            hadUnrecoverableException = true;
            return null;
        }
    }

}