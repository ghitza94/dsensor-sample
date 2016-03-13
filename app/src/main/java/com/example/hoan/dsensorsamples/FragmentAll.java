package com.example.hoan.dsensorsamples;


import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class FragmentAll extends Fragment implements DSensorEventListener {

    private TextView mCompass;
    private TextView mFacing;
    private TextView mGravityX;
    private TextView mGravityY;
    private TextView mGravityZ;
    private TextView mGyroscopeX;
    private TextView mGyroscopeY;
    private TextView mGyroscopeZ;
    private TextView mRotationVectorX;
    private TextView mRotationVectorY;
    private TextView mRotationVectorZ;
    private TextView mRotationVectorCos;
    private TextView mInclination;
    private TextView mLinearAccelerationX;
    private TextView mLinearAccelerationY;
    private TextView mLinearAccelerationZ;
    private TextView mMagneticX;
    private TextView mMagneticY;
    private TextView mMagneticZ;
    private TextView mPitch;
    private TextView mAccelerometerX;
    private TextView mAccelerometerY;
    private TextView mAccelerometerZ;
    private TextView mRoll;
    private TextView mRotation;
    private TextView mAll;

    private Button mPause;

    public FragmentAll() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_all, container, false);
        mCompass = (TextView) v.findViewById(R.id.textview_compass_value);
        mFacing = (TextView) v.findViewById(R.id.textview_facing_value);
        mGravityX = (TextView) v.findViewById(R.id.textview_gravity_x_value);
        mGravityY = (TextView) v.findViewById(R.id.textview_gravity_y_value);
        mGravityZ = (TextView) v.findViewById(R.id.textview_gravity_z_value);
        mGyroscopeX = (TextView) v.findViewById(R.id.textview_gyroscope_x_value);
        mGyroscopeY = (TextView) v.findViewById(R.id.textview_gyroscope_y_value);
        mGyroscopeZ = (TextView) v.findViewById(R.id.textview_gyroscope_z_value);
        mInclination = (TextView) v.findViewById(R.id.textview_inclination_value);
        mLinearAccelerationX = (TextView) v.findViewById(R.id.textview_linear_acceleration_x_value);
        mLinearAccelerationY = (TextView) v.findViewById(R.id.textview_linear_acceleration_y_value);
        mLinearAccelerationZ = (TextView) v.findViewById(R.id.textview_linear_acceleration_z_value);
        mMagneticX = (TextView) v.findViewById(R.id.textview_magnetic_x_value);
        mMagneticY = (TextView) v.findViewById(R.id.textview_magnetic_y_value);
        mMagneticZ = (TextView) v.findViewById(R.id.textview_magnetic_z_value);
        mPitch = (TextView) v.findViewById(R.id.textview_pitch_value);
        mAccelerometerX = (TextView) v.findViewById(R.id.textview_accelerometer_x_value);
        mAccelerometerY = (TextView) v.findViewById(R.id.textview_accelerometer_y_value);
        mAccelerometerZ = (TextView) v.findViewById(R.id.textview_accelerometer_z_value);
        mRoll = (TextView) v.findViewById(R.id.textview_roll_value);
        mRotation = (TextView) v.findViewById(R.id.textview_rotation_value);
        mRotationVectorX = (TextView) v.findViewById(R.id.textview_rotation_vector_x_value);
        mRotationVectorY = (TextView) v.findViewById(R.id.textview_rotation_vector_y_value);
        mRotationVectorZ = (TextView) v.findViewById(R.id.textview_rotation_vector_z_value);
        mRotationVectorCos = (TextView) v.findViewById(R.id.textview_rotation_vector_cos_value);
        mAll = (TextView) v.findViewById(R.id.textview_all);
        mPause = (Button) v.findViewById(R.id.button_pause);
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DSensorManager.stopDSensor();
            }
        });

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        DSensorManager.startDSensor(getActivity(), DSensor.TYPE_ALL,
                SensorManager.SENSOR_DELAY_NORMAL, this);
    }

    @Override
    public void onStop()
    {
        DSensorManager.stopDSensor();
        super.onStop();
    }

    @Override
    public void onDSensorChanged(final int changedSensorTypes, final DProcessedSensorEvent dProcessedSensorEvent) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                /*mCompass.setText(String.valueOf(Math.round(Math.toDegrees(dProcessedSensorEvent.xAxisDirection)))
                        + "\t\t" + Math.round(dSensor.mOrientation.values[0]));
                mFacing.setText(String.valueOf(Math.round(Math.toDegrees(dSensor.mFacing))));
                mGravityX.setText(String.valueOf(dSensor.mGravityInDeviceCoord.values[0])
                        + "\t" + dSensor.mGravityWithAverageInDeviceCoord.values[0]);
                mGravityY.setText(String.valueOf(dSensor.mGravityInDeviceCoord.values[1])
                        + "\t" + dSensor.mGravityWithAverageInDeviceCoord.values[1]);
                mGravityZ.setText(String.valueOf(dSensor.mGravityInDeviceCoord.values[2])
                        + "\t" + dSensor.mGravityWithAverageInDeviceCoord.values[2]);
                mGyroscopeX.setText(String.valueOf(dSensor.mGyroscope.values[0]));
                mGyroscopeY.setText(String.valueOf(dSensor.mGyroscope.values[1]));
                mGyroscopeZ.setText(String.valueOf(dSensor.mGyroscope.values[2]));
                mInclination.setText(String.valueOf(Math.round(Math.toDegrees(dSensor.mInclination)))
                        + "\t" + Math.round(Math.toDegrees(dSensor.mInclinationUsingAverage)));
                mLinearAccelerationX.setText(String.valueOf(dSensor.mLinearAccelerationInDeviceCoord.values[0])
                        + "\t" + dSensor.mLinearAccelerationWithAverageInDeviceCoord.values[0]);
                mLinearAccelerationY.setText(String.valueOf(dSensor.mLinearAccelerationInDeviceCoord.values[1])
                        + "\t" + dSensor.mLinearAccelerationWithAverageInDeviceCoord.values[1]);
                mLinearAccelerationZ.setText(String.valueOf(dSensor.mLinearAccelerationInDeviceCoord.values[2])
                        + "\t" + dSensor.mLinearAccelerationWithAverageInDeviceCoord.values[2]);
                mMagneticX.setText(String.valueOf(dSensor.mMagneticFieldInDeviceCoord.values[0])
                        + "\t" + dSensor.mMagneticFieldInWorldCoord.values[0]);
                mMagneticY.setText(String.valueOf(dSensor.mMagneticFieldInDeviceCoord.values[1])
                        + "\t" + dSensor.mMagneticFieldInWorldCoord.values[1]);
                mMagneticZ.setText(String.valueOf(dSensor.mMagneticFieldInDeviceCoord.values[2])
                        + "\t" + dSensor.mMagneticFieldInWorldCoord.values[2]);
                mPitch.setText(String.valueOf(Math.round(Math.toDegrees(dSensor.mPitch)))
                        + "\t" + Math.round(dSensor.mOrientation.values[1]));
                mRoll.setText(String.valueOf(Math.round(Math.toDegrees(dSensor.mRoll)))
                        + "\t" + Math.round(dSensor.mOrientation.values[2]));
                mAccelerometerX.setText(String.valueOf(dSensor.mAccelerometerInDeviceCoord.values[0])
                        + "\t" + dSensor.mAccelerometerWithAverageInDeviceCoord.values[0]);
                mAccelerometerY.setText(String.valueOf(dSensor.mAccelerometerInDeviceCoord.values[1])
                        + "\t" + dSensor.mAccelerometerWithAverageInDeviceCoord.values[1]);
                mAccelerometerZ.setText(String.valueOf(dSensor.mAccelerometerInDeviceCoord.values[2])
                        + "\t" + dSensor.mAccelerometerWithAverageInDeviceCoord.values[2]);
                mRotation.setText(String.valueOf(Math.round(Math.toDegrees(dSensor.mRotation)))
                        + "\t" + Math.round(Math.toDegrees(dSensor.mRotationUsingAverage)));
                mRotationVectorX.setText(String.valueOf(dSensor.mRotationVector.values[0]));
                mRotationVectorY.setText(String.valueOf(dSensor.mRotationVector.values[1]));
                mRotationVectorZ.setText(String.valueOf(dSensor.mRotationVector.values[2]));
                if (dSensor.mRotationVector.values.length > 3)
                {
                    mRotationVectorCos.setText(String.valueOf(dSensor.mRotationVector.values[3]));
                }
                mAll.append("\n" + dSensor.toString());*/
            }
        });

    }


}
