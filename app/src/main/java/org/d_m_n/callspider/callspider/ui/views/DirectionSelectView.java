package org.d_m_n.callspider.callspider.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/30/16.
 */

public class DirectionSelectView extends LinearLayout implements View.OnClickListener {

    public interface DirectionClickListener{

        public void onDirectionClick(ContactCallForbidDirection d);
    }

    @BindView(R.id.iv_direction_select_arrow_up)
    protected ImageView mivUp;

    @BindView(R.id.iv_direction_select_arrow_down)
    protected ImageView mivDown;

    @BindView(R.id.iv_direction_select_arrow_both)
    protected ImageView mivBoth;

    @BindView(R.id.iv_direction_select_arrow_unknown)
    protected ImageView mivUnknown;

    private DirectionClickListener clickListener;


    public DirectionSelectView(Context context) {
        super(context);
        init(context);
    }

    public DirectionSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DirectionSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View v =inflate(context, R.layout.incl_direction_select, this);
        ButterKnife.bind(this,v);
        this.setLayoutDirection(LAYOUT_DIRECTION_RTL);
        this.setOrientation(HORIZONTAL);
        mivUp.setOnClickListener(this);
        mivDown.setOnClickListener(this);
        mivBoth.setOnClickListener(this);
        mivUnknown.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_direction_select_arrow_up:
                handleDirectionClick(ContactCallForbidDirection.OUTGOING);
                break;
            case R.id.iv_direction_select_arrow_down:
                handleDirectionClick(ContactCallForbidDirection.INCOMING);
                break;
            case R.id.iv_direction_select_arrow_both:
                handleDirectionClick(ContactCallForbidDirection.FULL);
                break;
            case R.id.iv_direction_select_arrow_unknown:
                handleDirectionClick(ContactCallForbidDirection.NOT_SET);
                break;
            default:
                break;

        }
    }

    private void handleDirectionClick(ContactCallForbidDirection d) {
        hideItem(d);
        if (clickListener != null) {
            clickListener.onDirectionClick(d);
        }
    }

    public void hideItem(ContactCallForbidDirection d){
        mivUp.setVisibility(VISIBLE);
        mivDown.setVisibility(VISIBLE);
        mivBoth.setVisibility(VISIBLE);
        mivUnknown.setVisibility(VISIBLE);
        switch (d){
            case FULL:
                mivBoth.setVisibility(GONE); break;
            case OUTGOING:
                mivUp.setVisibility(GONE); break;
            case INCOMING:
                mivDown.setVisibility(GONE); break;
            case NOT_SET:
                mivUnknown.setVisibility(GONE); break;
        }
    }

    public void toggleVisibility() {
        setVisibility(isVisible() ? GONE : VISIBLE);
    }

    public void hide(){
        setVisibility(GONE);
    }

    public void show(){
        setVisibility(VISIBLE);
    }

    public boolean isVisible(){
        return getVisibility() == VISIBLE;
    }

    public void setItemClickListener(DirectionClickListener onClickListener) {
        this.clickListener = onClickListener;
    }
}
