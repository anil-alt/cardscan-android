package com.getbouncer.cardscan.base;

import android.graphics.Bitmap;

import com.getbouncer.cardscan.base.ssd.DetectedSSDBox;

import java.util.List;

public interface OnMultiListener {

    void onMultiModelPrediction(final Bitmap bitmap, List<DetectedSSDBox> boxes,
                                final String number, final boolean isNumberValidPan, final Expiry expiry,
                                final List<DetectedBox> digitBoxes, final DetectedBox expiryBox,
                                int imageWidth, int imageHeight, final Bitmap fullScreenBitmap);

    void onObjectFatalError();
}
