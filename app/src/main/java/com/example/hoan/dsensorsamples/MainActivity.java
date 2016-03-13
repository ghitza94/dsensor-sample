package com.example.hoan.dsensorsamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.hoan.dsensorsamples.utils.Logger;
import com.hoan.dsensor_master.DProcessedSensor;
import com.hoan.dsensor_master.DProcessedSensorEvent;
import com.hoan.dsensor_master.DSensorEvent;
import com.hoan.dsensor_master.DSensorManager;
import com.hoan.dsensor_master.interfaces.DProcessedEventListener;
import com.hoan.dsensor_master.interfaces.DSensorEventListener;


public class MainActivity extends BaseActivityWithFragment implements BaseFragment.FragmentEventListener {

    private BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.d(this, MainActivity.class.getSimpleName(), "onCreate");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            if (findViewById(R.id.fragment_container) != null) {
                showFragment(R.id.fragment_container, new FragmentSensorList());
            }
        }
    }

    @Override
    public void onBackPressed() {
        Logger.d(this, MainActivity.class.getSimpleName(), "onBackPressed");
        if (mCurrentFragment != null
                || getSupportFragmentManager().findFragmentByTag(FragmentSensorInfo.class.getSimpleName()) != null) {
            getSupportFragmentManager().popBackStackImmediate();
            mCurrentFragment = null;
        } else {
            finish();
        }
    }

    @Override
    public void onSensorSelected(String item) {
        Logger.d(this, MainActivity.class.getSimpleName(), "onSensorSelected(" + item + ")");
        int containerId = findViewById(R.id.fragment_container_large) == null ?
                R.id.fragment_container : R.id.fragment_container_large;
        if (FragmentSensorList.SENSORS_INFO.equals(item)) {
            FragmentSensorInfo sensorInfoFragment = new FragmentSensorInfo();
            showFragment(containerId, sensorInfoFragment, true);
        }else if (FragmentSensorList.COMPASS_3D.equals(item)) {
            startActivity(new Intent(this, CompassActivity.class));
        } else if (FragmentSensorList.COMPASS_FLAT_ONLY.equals(item)) {
            Intent intent = new Intent(this, CompassActivity.class);
            intent.putExtra(CompassActivity.DPROCESSEDSENSOR_TYPE,
                    DProcessedSensor.TYPE_COMPASS_FLAT_ONLY);
            startActivity(intent);
        }
        else if (FragmentSensorList.COMPASS_FLAT_ONLY_AND_DEPRECIATED_ORIENTATION.equals(item)) {
            Intent intent = new Intent(this, CompassActivity.class);
            intent.putExtra(CompassActivity.DPROCESSEDSENSOR_TYPE,
                    DProcessedSensor.TYPE_COMPASS_FLAT_ONLY_AND_DEPRECIATED_ORIENTATION);
            startActivity(intent);
        } else if (FragmentSensorList.COMPASS_3D_AND_DEPRECIATED_ORIENTATION.equals(item)) {
            Intent intent = new Intent(this, CompassActivity.class);
            intent.putExtra(CompassActivity.DPROCESSEDSENSOR_TYPE,
                    DProcessedSensor.TYPE_3D_COMPASS_AND_DEPRECIATED_ORIENTATION);
            startActivity(intent);
        }
    }


    @Override
    public void onButtonClick(int buttonResId) {
        DSensorManager.stopDSensor();
    }

}
