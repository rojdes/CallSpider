package org.d_m_n.callspider.callspider.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toolbar;

import org.d_m_n.callspider.callspider.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 12/2/16.
 */

public class ImplToolbar extends Toolbar {

    //android:elevation="4dp"

    @BindView(R.id.cstm_alert_view)
    protected AlertView mAlertView;

    public ImplToolbar(Context context) {
        super(context);
        init(context);
    }

    public ImplToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImplToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ImplToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        //disable offsets
//        setContentInsetsAbsolute(0,0);
//        setContentInsetsRelative(0,0);
//        setClipToPadding(false);
        View rootView = LayoutInflater.from(context).inflate(R.layout.incl_toolbar, this);
        //set theme @style/ThemeOverlay.AppCompat.ActionBar"
        ButterKnife.bind(rootView, this);

    }
}
