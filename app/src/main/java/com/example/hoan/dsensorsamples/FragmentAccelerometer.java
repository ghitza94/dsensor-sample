package com.example.hoan.dsensorsamples;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoan.dsensorsamples.interfaces.DSensorValuesChangedListener;
import com.hoan.dsensor_master.DProcessedSensorEvent;
import com.hoan.dsensor_master.DSensor;
import com.hoan.dsensor_master.DSensorEvent;

public class FragmentAccelerometer extends BaseFragment implements DSensorValuesChangedListener {

    private TextView mAccelerometerTextView;
    private Button mPauseButton;


    public FragmentAccelerometer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        mAccelerometerTextView = (TextView) v.findViewById(R.id.textview_accelerometer);
        mPauseButton = (Button) v.findViewById(R.id.button_pause);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentEventListener.onButtonClick(R.id.button_pause);
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context, AppCompatActivity activity) {
        mFragmentEventListener = context == null ? (FragmentEventListener) activity : (FragmentEventListener) context;
    }

    @Override
    public void updateView(int changedDSensorTypes, DProcessedSensorEvent dProcessedSensorEvent) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n\nX\n");
        if ((changedDSensorTypes & DSensor.TYPE_DEVICE_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInDeviceBasis.values[0]));
            builder.append("    ");
        }
        if ((changedDSensorTypes & DSensor.TYPE_WORLD_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInWorldBasis.values[0]));
        }
        builder.append("\nY:\n");
        if ((changedDSensorTypes & DSensor.TYPE_DEVICE_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInDeviceBasis.values[1]));
            builder.append("    ");
        }
        if ((changedDSensorTypes & DSensor.TYPE_WORLD_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInWorldBasis.values[1]));
        }
        builder.append("\nZ:\n");
        if ((changedDSensorTypes & DSensor.TYPE_DEVICE_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInDeviceBasis.values[2]));
            builder.append("    ");
        }
        if ((changedDSensorTypes & DSensor.TYPE_WORLD_ACCELEROMETER) != 0) {
            builder.append(String.format("%f", dProcessedSensorEvent.accelerometerInWorldBasis.values[2]));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAccelerometerTextView.append(builder.toString());
            }
        });
    }
}
