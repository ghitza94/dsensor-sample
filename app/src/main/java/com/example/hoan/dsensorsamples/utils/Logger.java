package com.example.hoan.dsensorsamples.utils;

import android.content.Context;
import android.util.Log;

import com.example.hoan.dsensorsamples.CompassActivity;
import com.example.hoan.dsensorsamples.FragmentAccelerometer;
import com.example.hoan.dsensorsamples.FragmentAll;
import com.example.hoan.dsensorsamples.FragmentSensorInfo;
import com.example.hoan.dsensorsamples.FragmentSensorList;
import com.example.hoan.dsensorsamples.FragmentUpAndDownMotion;
import com.example.hoan.dsensorsamples.MainActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Convenient class for logging
 * Created by Hoan on 1/30/2016.
 */
public class Logger {
    private static final boolean WRITE_TO_FILE = true;
    private static String LOG_DIR;

	private static final List<String> DEBUG_CLASSES = Arrays.asList(
            CompassActivity.class.getSimpleName(),
			FragmentAccelerometer.class.getSimpleName(),
			FragmentAll.class.getSimpleName(),
			FragmentSensorInfo.class.getSimpleName(),
			FragmentSensorList.class.getSimpleName(),
			FragmentUpAndDownMotion.class.getSimpleName(),
			MainActivity.class.getSimpleName(),
            "SensorExpandableAdapter"
    );

	private Logger() {

	}

	public static synchronized void d(String tag, String msg)
	{
		if (DEBUG_CLASSES.contains(tag))
		{
			Log.e(tag, msg);
		}
	}

    public static synchronized void d(Context context, String tag, String msg)
    {
        if (DEBUG_CLASSES.contains(tag))
        {
            Log.e(tag, msg);
            if (WRITE_TO_FILE)
            {
                if (LOG_DIR == null) {
                    LOG_DIR = FileUtil.createDir(context, "dsensor_log");
                }

                if (LOG_DIR != null) {
                    try
                    {
                        FileUtils.writeStringToFile(new File(LOG_DIR + File.separator + "log.txt"),
                                tag + ": " + msg + "\n", "UTF-8", true);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
