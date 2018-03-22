package com.lgcode.popularshows.views;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import glb.lg.popularshows.R;

public abstract class ActivityView {

    @Nullable @BindView(R.id.tvshow_detail_toolbar) Toolbar toolbar;
    private transient WeakReference<AppCompatActivity> activityRef;

    ActivityView(AppCompatActivity activity) {
        activityRef = new WeakReference<>(activity);
        ButterKnife.bind(this, activity);
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Nullable
    public AppCompatActivity getActivity() {
        return activityRef.get();
    }

    @Nullable
    private ActionBar getActionBar() {
        AppCompatActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getSupportActionBar();
    }

    public void setHomeAsUpEnabled(boolean showHomeAsUp) {
        ActionBar actionBar = getActionBar();
        if (actionBar == null || toolbar == null) {
            return;
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = getActivity();
                if (activity == null) {
                    return;
                }
                activity.onBackPressed();
            }
        });
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
//        Drawable upArrow = ContextCompat.getDrawable(activity, R.drawable.home_as_up);
//        upArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
//        actionBar.setHomeAsUpIndicator(upArrow);
    }
}
