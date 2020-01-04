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
        if (boxOne.XMin + 0.5 * this.medianWidth  < boxTwo.XMin || boxOne.YMin + 0.5 * this.medianHeight < boxTwo.YMin ){
            return -1;
        }
        else return 1;
    }
}
