package com.example.hoan.dsensorsamples.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Hoan on 3/10/2016.
 */
public class FileUtil {
    private FileUtil() {

    }

    public static String createDir(Context context, String dirName)
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            String storagePath = context.getExternalFilesDir(null).getAbsolutePath();
            File dir = new File(storagePath + File.separator + dirName);
            if (dir.isDirectory() || dir.mkdirs())
            {
                return dir.getAbsolutePath();
            }
        }

        return null;
    }
}
