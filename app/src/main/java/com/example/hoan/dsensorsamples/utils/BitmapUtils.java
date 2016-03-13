package com.example.hoan.dsensorsamples.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils
{
	private BitmapUtils() {

    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) 
		{
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) 
			{
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}
	
	public static Bitmap decodeBitmapFromByteArray(byte[] byteArray, int length, int reqWidth, int reqHeight)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeByteArray(byteArray, 0, length, options);

	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeByteArray(byteArray, 0, length, options);
	}
	
	public static Bitmap decodeBitmapFromFile(String filename, int reqWidth, int reqHeight)
	{
		try
		{
			File file = new File(filename);
			return decodeBitmapFromByteArray(FileUtils.readFileToByteArray(file), 
					(int) file.length(), reqWidth, reqHeight);
		}
		catch (IOException e)
		{
			
		}
		return null;
	}
	
	public static Bitmap decodeBitmapFromResource(Context context, int resId, int reqWidth, int reqHeight)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(context.getResources(), resId, options);

	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(context.getResources(), resId, options);
	}
	
	public static void saveBitmapToFile(Bitmap bitmap, String pathName)
	{
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(pathName);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
		}
		catch (FileNotFoundException e)
		{
			
		}
        catch (IOException e)
        {

        }
		finally
		{
			try
			{
				out.close();
			}
			catch (IOException e)
			{
				
			}
		}
	}
}
