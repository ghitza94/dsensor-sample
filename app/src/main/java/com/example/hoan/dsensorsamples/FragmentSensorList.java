package com.example.hoan.dsensorsamples;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.hoan.dsensorsamples.utils.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class FragmentSensorList extends BaseFragment {

    public static final String SENSORS_INFO = "Sensors info";
    public static final String COMPASS_3D = "3D Compass";
    public static final String COMPASS_FLAT_ONLY = "Compass flat only";
    public static final String COMPASS_3D_AND_DEPRECIATED_ORIENTATION = "3D Compass & depreciated Orientation";
    public static final String COMPASS_FLAT_ONLY_AND_DEPRECIATED_ORIENTATION = "Compass flat only & depreciated Orientation";
    public static final String GYROSCOPE = "Gyroscope";

    //private static final String[] LIST_ITEMS = {"Sensors info", "Accelerometer", "Compass", "Compass flat only and Orientation",
    //        "Up and Down Motion", "All"};

    public FragmentSensorList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(getActivity(), FragmentSensorList.class.getSimpleName(), "onCreateView");
        View v = inflater.inflate(R.layout.fragment_sensor_list, container, false);
        ExpandableListView expandableListView = (ExpandableListView) v.findViewById(R.id.sensor_expandable_listview);
        expandableListView.setAdapter(new SensorExpandableAdapter());
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String sensor = ((SensorExpandableAdapter)parent.getExpandableListAdapter()).getChild(groupPosition, childPosition);
                mFragmentEventListener.onSensorSelected(sensor);
                parent.setSelectedChild(groupPosition, childPosition, true);
                return true;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 0) {
                    String sensor = ((SensorExpandableAdapter) parent.getExpandableListAdapter()).getGroup(0);
                    mFragmentEventListener.onSensorSelected(sensor);
                    parent.setSelectedGroup(0);
                } else if (parent.isGroupExpanded(groupPosition)){
                    parent.collapseGroup(groupPosition);
                } else {
                    parent.expandGroup(groupPosition);
                }
                return true;
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context, AppCompatActivity activity) {
        Logger.d(getActivity(), FragmentSensorList.class.getSimpleName(), "onAttach");
        mFragmentEventListener = context == null ? (FragmentEventListener) activity : (FragmentEventListener) context;
    }

    private class SensorExpandableAdapter extends BaseExpandableListAdapter {
        public final LinkedHashMap<String, List<String>> SENSOR_HASHMAP = new LinkedHashMap<>();
        {
            SENSOR_HASHMAP.put("Sensors info", null);
            SENSOR_HASHMAP.put("Compass", Arrays.asList(COMPASS_3D, COMPASS_FLAT_ONLY,
                    COMPASS_3D_AND_DEPRECIATED_ORIENTATION, COMPASS_FLAT_ONLY_AND_DEPRECIATED_ORIENTATION));
            SENSOR_HASHMAP.put(GYROSCOPE, Arrays.asList(GYROSCOPE));
        }

        @Override
        public int getGroupCount() {
            return SENSOR_HASHMAP.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            Iterator<String> iterator = SENSOR_HASHMAP.keySet().iterator();
            int index = 0;
            List<String> childList;
            while (iterator.hasNext()) {
                childList = SENSOR_HASHMAP.get(iterator.next());
                if (index++ == groupPosition) {
                    return childList == null ? 0 : childList.size();
                }
            }
            return 0;
        }

        @Override
        public String getGroup(int groupPosition) {
            Iterator<String> iterator = SENSOR_HASHMAP.keySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                if (index++ == groupPosition) {
                    return iterator.next();
                }
                iterator.next();
            }
            return null;
        }

        @Override
        public String getChild(int groupPosition, int childPosition) {
            Iterator<String> iterator = SENSOR_HASHMAP.keySet().iterator();
            int index = 0;
            List<String> childList;
            while (iterator.hasNext()) {
                childList = SENSOR_HASHMAP.get(iterator.next());
                if (index++ == groupPosition) {
                    return childList == null ? null : childList.get(childPosition);
                }
            }
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            }

            TextView nameTextView = ViewHolder.get(convertView, android.R.id.text1);
            nameTextView.setText(getGroup(groupPosition));

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.sensor_expandable_list_child_item, parent, false);
            }

            TextView nameTextView = ViewHolder.get(convertView, R.id.textview_child_item);
            nameTextView.setText(getChild(groupPosition, childPosition));

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
