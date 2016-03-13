package com.example.hoan.dsensorsamples.interfaces;

import com.hoan.dsensor_master.DProcessedSensorEvent;

/**
 *
 * Created by Hoan on 3/11/2016.
 */
public interface DSensorValuesChangedListener {
    void updateView(int changedDSensorTypes, DProcessedSensorEvent dProcessedSensorEvent);
}
