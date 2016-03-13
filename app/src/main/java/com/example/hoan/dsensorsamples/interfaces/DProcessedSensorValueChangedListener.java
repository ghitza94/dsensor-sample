package com.example.hoan.dsensorsamples.interfaces;

import com.hoan.dsensor_master.DSensorEvent;

/**
 *
 * Created by Hoan on 3/11/2016.
 */
public interface DProcessedSensorValueChangedListener {
    void updateView(DSensorEvent dSensorEvent);
}
