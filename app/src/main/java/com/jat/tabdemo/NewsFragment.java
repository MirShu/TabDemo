package com.jat.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by  on 2016/6/15.
 */
public class NewsFragment extends Fragment {
    private String weburl;
    private String name;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment,null);
            TextView textView = (TextView) view.findViewById(R.id.content);
            textView.setText(name);
        }
        ViewGroup group = (ViewGroup) view.getParent();
        if (group!=null){
            group.removeView(view);
        }

        return view;
    }

    @Override
    public void setArguments(Bundle bundle) {
        name = bundle.getString("name");
    }
}
