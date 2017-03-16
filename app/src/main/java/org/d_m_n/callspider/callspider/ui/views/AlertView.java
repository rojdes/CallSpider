package org.d_m_n.callspider.callspider.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/30/16.
 */

public class AlertView extends FrameLayout implements View.OnClickListener {

    private static final String TAG = AlertView.class.getSimpleName();

    @BindView(R.id.iv_incl_alert)
    protected ImageView mivBackground;

    @BindView(R.id.tv_incl_alert)
    protected TextView mtvCount;


    public AlertView(Context context) {
        super(context);
        init(context);
    }

    public AlertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AlertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlertView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        View v = inflate(context, R.layout.incl_alert_toolbar_icon, this);
        ButterKnife.bind(this,v);
        this.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Logger.e(TAG, "onClick " +v);
    }

    public void addAlert(int type, String description){
      Logger.e(TAG, "addAlert STUB!!!!!!!!!");
    }

    public void clearAlerts(){
        Logger.e(TAG, "clearAlerts STUB!!!!!!!!!");
    }


}
