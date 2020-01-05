package com.getbouncer.cardscan.base.ssd;

import java.util.Comparator;

public class DynamicComparator implements Comparator<DetectedOcrBox> {
    public float medianHeight = 0.0f;
    public float medianWidth = 0.0f;

    public DynamicComparator(float medianHeight, float medianWidth){
        this.medianHeight = medianHeight;
        this.medianWidth = medianWidth;
    }

    public int compare(DetectedOcrBox boxOne, DetectedOcrBox boxTwo){
        if (boxOne.YMin + 0.8 * this.medianHeight < boxTwo.YMin){
            return -1;
        }
        else if (boxOne.XMin < boxTwo.XMin){
            return -1;
        }
        else if (boxOne.YMin > 0.8 * this.medianHeight + boxTwo.YMin) {
            return 1;
        }
        else if (boxOne.XMin > boxTwo.XMin){
            return 1;
        }
        else return 0;
    }
}
