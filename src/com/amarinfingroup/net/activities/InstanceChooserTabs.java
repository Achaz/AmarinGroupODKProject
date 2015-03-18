package com.amarinfingroup.net.activities;

import com.amarinfingroup.net.R;
import com.amarinfingroup.net.application.Collect;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * A host activity for {@link InstanceChooserList}.
 * 
 * @author Yaw Anokwa (yanokwa@gmail.com)
 */
public class InstanceChooserTabs extends TabActivity {

    private static final String SAVED_TAB = "saved_tab";
    private static final String COMPLETED_TAB = "completed_tab";

    // count for tab menu bar
    private int mSavedCount;
    private int mCompletedCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name) + " > " + getString(R.string.review_data));

        // create tab host and tweak color
        final TabHost tabHost = getTabHost();
        tabHost.setBackgroundColor(Color.WHITE);
        tabHost.getTabWidget().setBackgroundColor(Color.BLACK);

        // create intent for saved tab
        Intent saved = new Intent(this, InstanceChooserList.class);
        tabHost.addTab(tabHost.newTabSpec(SAVED_TAB)
                .setIndicator(getString(R.string.saved_data, mSavedCount)).setContent(saved));

        // create intent for completed tab
        Intent completed = new Intent(this, InstanceChooserList.class);
        tabHost.addTab(tabHost.newTabSpec(COMPLETED_TAB)
                .setIndicator(getString(R.string.completed_data, mCompletedCount))
                .setContent(completed));

        // hack to set font size and padding in tab headers
        // arrived at these paths by using hierarchy viewer
        LinearLayout ll = (LinearLayout) tabHost.getChildAt(0);
        TabWidget tw = (TabWidget) ll.getChildAt(0);

        int fontsize = Collect.getQuestionFontsize();

        RelativeLayout rls = (RelativeLayout) tw.getChildAt(0);
        TextView tvs = (TextView) rls.getChildAt(1);
        tvs.setTextSize(fontsize);
        tvs.setPadding(0, 0, 0, 6);

        RelativeLayout rlc = (RelativeLayout) tw.getChildAt(1);
        TextView tvc = (TextView) rlc.getChildAt(1);
        tvc.setTextSize(fontsize);
        tvc.setPadding(0, 0, 0, 6);

        if (mSavedCount >= mCompletedCount) {
            getTabHost().setCurrentTabByTag(SAVED_TAB);
        } else {
            getTabHost().setCurrentTabByTag(COMPLETED_TAB);
        }
    }

}
