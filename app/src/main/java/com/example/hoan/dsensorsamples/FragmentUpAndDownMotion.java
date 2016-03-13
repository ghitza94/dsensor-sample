package com.example.hoan.dsensorsamples;


import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoan.dsensorsamples.utils.Logger;
import com.hoan.dsensor_master.DProcessedSensorEvent;
import com.hoan.dsensor_master.DSensor;
import com.hoan.dsensor_master.DSensorManager;
import com.hoan.dsensor_master.interfaces.DSensorEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUpAndDownMotion extends Fragment implements DSensorEventListener {

    private TextView mTextView;

    public FragmentUpAndDownMotion() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(FragmentUpAndDownMotion.class.getSimpleName(), "onCreateView");
        View v = inflater.inflate(R.layout.fragment_up_and_down_motion, container, false);
        mTextView = (TextView) v.findViewById(R.id.textview_updown_motion);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d(FragmentUpAndDownMotion.class.getSimpleName(), "onStart");
        DSensorManager.startDSensor(getActivity(), DSensor.TYPE_WORLD_LINEAR_ACCELERATION,
                SensorManager.SENSOR_DELAY_NORMAL, this);
    }

    @Override
    public void onStop() {
        Logger.d(FragmentUpAndDownMotion.class.getSimpleName(), "onStop");
        DSensorManager.stopDSensor();
        super.onStop();
    }

    @Override
    public void onDSensorChanged(final int changedSensorTypes, final DProcessedSensorEvent dProcessedSensorEvent) {

    }
}
