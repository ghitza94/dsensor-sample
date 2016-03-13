package com.example.hoan.dsensorsamples;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Show all sensors in a device.
 */
public class FragmentSensorInfo extends Fragment {


    public FragmentSensorInfo() {
        // Required empty public constructor
    }

    private SparseArray<String> sensorTypes = new SparseArray<>();
    {
        sensorTypes.put(Sensor.TYPE_ACCELEROMETER, "TYPE_ACCELEROMETER");
        sensorTypes.put(Sensor.TYPE_GYROSCOPE, "TYPE_GYROSCOPE");
        sensorTypes.put(Sensor.TYPE_LIGHT, "TYPE_LIGHT");
        sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD, "TYPE_MAGNETIC_FIELD");
        sensorTypes.put(Sensor.TYPE_ORIENTATION, "TYPE_ORIENTATION");
        sensorTypes.put(Sensor.TYPE_PRESSURE, "TYPE_PRESSURE");
        sensorTypes.put(Sensor.TYPE_PROXIMITY, "TYPE_PROXIMITY");
        sensorTypes.put(Sensor.TYPE_TEMPERATURE, "TYPE_TEMPERATURE");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            sensorTypes.put(Sensor.TYPE_GRAVITY, "TYPE_GRAVITY");
            sensorTypes.put(Sensor.TYPE_LINEAR_ACCELERATION, "TYPE_LINEAR_ACCELERATION");
            sensorTypes.put(Sensor.TYPE_ROTATION_VECTOR, "TYPE_ROTATION_VECTOR");

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2) {
                sensorTypes.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "TYPE_AMBIENT_TEMPERATURE");
                sensorTypes.put(Sensor.TYPE_RELATIVE_HUMIDITY, "TYPE RELATIVE HUMIDITY");

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sensorTypes.put(Sensor.TYPE_GAME_ROTATION_VECTOR, "TYPE GAME ROTATION VECTOR");
                    sensorTypes.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "TYPE GYROSCOPE UNCALIBRATED");
                    sensorTypes.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, "TYPE MAGNETIC FIELD UNCALIBRATED");
                    sensorTypes.put(Sensor.TYPE_SIGNIFICANT_MOTION, "TYPE SIGNIFICANT MOTION");

                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        sensorTypes.put(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR, "TYPE GEOMAGNETIC ROTATION VECTOR");
                        sensorTypes.put(Sensor.TYPE_STEP_COUNTER, "TYPE STEP COUNTER");
                        sensorTypes.put(Sensor.TYPE_STEP_DETECTOR, "TYPE STEP DETECTOR");

                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                            sensorTypes.put(Sensor.TYPE_HEART_RATE, "TYPE HEART RATE");
                        }
                    }
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor_info, container, false);
        TextView sensorInfo = (TextView) v.findViewById(R.id.textview_sensors_info);

        sensorInfo.setText(getSensorInfo());

        return v;
    }

    private SpannableStringBuilder getSensorInfo() {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        SensorManager mgr = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = mgr.getSensorList(Sensor.TYPE_ALL);

        builder.append("The sensors on this device are\n\n");
        builder.setSpan(new TextAppearanceSpan(getActivity(), android.R.style.TextAppearance_Medium),
                0, "The sensors on this device are".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start;
        String type;
        for(Sensor sensor : sensors)
        {
            start = builder.length();
            builder.append("Name: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Name:".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(sensor.getName());
            start = builder.length();
            builder.append("\nType: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Type: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            type = sensorTypes.get(sensor.getType());
            if (type == null) {
                type = "TYPE_UNKNOWN";
            }
            builder.append(type);
            start = builder.length();
            builder.append("\nVendor: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Vendor: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(sensor.getVendor());
            start = builder.length();
            builder.append("\nVersion: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Version: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(String.valueOf(sensor.getVersion()));
            start = builder.length();
            builder.append("\nResolution: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Resolution: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(String.valueOf(sensor.getResolution()));
            start = builder.length();
            builder.append("\nMax Range: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Max Range: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(String.valueOf(sensor.getMaximumRange()));
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
                start = builder.length();
                builder.append("\nMin Delay: ");
                builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                        start + "Min Delay: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(String.valueOf(sensor.getMinDelay()));

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    start = builder.length();
                    builder.append("\nFifo Max Event Count: ");
                    builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                            start + "Fifo Max Event Count: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(String.valueOf(sensor.getFifoMaxEventCount()));
                    start = builder.length();
                    builder.append("\nFifo Reserved Event Count: ");
                    builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                            start + "Fifo Reserved Event Count: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.append(String.valueOf(sensor.getFifoReservedEventCount()));

                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                        start = builder.length();
                        builder.append("\nMax Delay: ");
                        builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                                start + "Max Delay: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        builder.append(String.valueOf(sensor.getMaxDelay()));
                        start = builder.length();
                        builder.append("\nIs Wake Up Sensor: ");
                        builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                                start + "Is Wake Up Sensor: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        builder.append(String.valueOf(sensor.isWakeUpSensor()));
                        String reportingMode = "UNKNOWN";
                        switch (sensor.getReportingMode()) {
                            case Sensor.REPORTING_MODE_CONTINUOUS:
                                reportingMode = "CONTINUOUS";
                                break;

                            case Sensor.REPORTING_MODE_ON_CHANGE:
                                reportingMode = "ON CHANGE";
                                break;

                            case Sensor.REPORTING_MODE_ONE_SHOT:
                                reportingMode = "ONE SHOT";
                                break;

                            case Sensor.REPORTING_MODE_SPECIAL_TRIGGER:
                                reportingMode = "SPECIAL TRIGGER";
                                break;
                        }
                        start = builder.length();
                        builder.append("\nReporting Mode: ");
                        builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                                start + "Reporting Mode: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        builder.append(reportingMode);
                    }
                }
            }
            start = builder.length();
            builder.append("\nPower: ");
            builder.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start,
                    start + "Power: ".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(String.valueOf(sensor.getPower()));
            builder.append(" mA\n\n");
        }

        return builder;
    }

}
