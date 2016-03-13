package com.example.hoan.dsensorsamples;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.example.hoan.dsensorsamples.utils.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    public interface FragmentEventListener {
        void onButtonClick(int buttonResId);
        void onSensorSelected(String item);
    }

    protected FragmentEventListener mFragmentEventListener;

    public BaseFragment() {
        // Required empty public constructor
    }

    public abstract void onAttach(Context context, AppCompatActivity activity);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Logger.d(BaseFragment.class.getSimpleName(), "onAttach(activity)");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttach(null, (AppCompatActivity) activity);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.d(BaseFragment.class.getSimpleName(), "onAttach(context)");
        onAttach(context, null);
    }
}
