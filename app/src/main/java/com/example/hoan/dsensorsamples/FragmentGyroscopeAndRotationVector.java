package com.example.hoan.dsensorsamples;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hoan.dsensor_master.DProcessedSensorEvent;
import com.hoan.dsensor_master.DSensor;
import com.hoan.dsensor_master.DSensorManager;
import com.hoan.dsensor_master.interfaces.DSensorEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGyroscopeAndRotationVector extends BaseFragment
        implements View.OnClickListener, DSensorEventListener {
    private TextView mGyroscopeXValueTextView;
    private TextView mGyroscopeYValueTextView;
    private TextView mGyroscopeZValueTextView;
    private TextView mRotationVectorXValueTextView;
    private TextView mRotationVectorYValueTextView;
    private TextView mRotationVectorZValueTextView;

    private Button mResumePauseButton;

    public FragmentGyroscopeAndRotationVector() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gyroscope_and_rotation_vector, container, false);
        mGyroscopeXValueTextView = (TextView) v.findViewById(R.id.textview_gyroscope_x_value);
        mGyroscopeYValueTextView = (TextView) v.findViewById(R.id.textview_gyroscope_y_value);
        mGyroscopeZValueTextView = (TextView) v.findViewById(R.id.textview_gyroscope_z_value);
        mRotationVectorXValueTextView = (TextView) v.findViewById(R.id.textview_rotation_vector_x_value);
        mRotationVectorYValueTextView = (TextView) v.findViewById(R.id.textview_rotation_vector_y_value);
        mRotationVectorZValueTextView = (TextView) v.findViewById(R.id.textview_rotation_vector_z_value);
        mResumePauseButton = (Button) v.findViewById(R.id.button_resume_pause);

        mResumePauseButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onPause() {
        DSensorManager.stopDSensor();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        DSensorManager.startDSensor(getActivity(), DSensor.TYPE_GYROSCOPE | DSensor.TYPE_ROTATION_VECTOR, this);
    }

    @Override
    public void onClick(View v) {
        if (mResumePauseButton.getText().toString().equals(getString(R.string.button_pause))) {
            DSensorManager.stopDSensor();
            mResumePauseButton.setText(R.string.button_resume);
        } else {
            DSensorManager.startDSensor(getActivity(), DSensor.TYPE_GYROSCOPE | DSensor.TYPE_ROTATION_VECTOR, this);
            mResumePauseButton.setText(R.string.button_pause);
        }
    }

    @Override
    public void onDSensorChanged(int changedDSensorTypes, DProcessedSensorEvent processedSensorEvent) {
        if ((changedDSensorTypes & DSensor.TYPE_ROTATION_VECTOR) != 0) {
            mRotationVectorXValueTextView.setText(String.valueOf(processedSensorEvent.rotationVector.values[0]));
            mRotationVectorYValueTextView.setText(String.valueOf(processedSensorEvent.rotationVector.values[1]));
            mRotationVectorZValueTextView.setText(String.valueOf(processedSensorEvent.rotationVector.values[2]));
        } else {
            mGyroscopeXValueTextView.setText(String.valueOf(processedSensorEvent.gyroscope.values[0]));
            mGyroscopeYValueTextView.setText(String.valueOf(processedSensorEvent.gyroscope.values[1]));
            mGyroscopeZValueTextView.setText(String.valueOf(processedSensorEvent.gyroscope.values[2]));
        }
    }

    @Override
    public void onAttach(Context context, AppCompatActivity activity) {

    }
}
