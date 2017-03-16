package org.d_m_n.callspider.callspider.model.enums;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import org.d_m_n.callspider.callspider.R;

/**
 * Created by d1m11n on 11/11/16.
 */

public enum ForbiddenDirection {

    INCOMING,
    OUTGOING,
    FULL,
    NOT_SET;

    public Drawable getDrawable(Context context){
        switch (this){
            case FULL:
                return ContextCompat.getDrawable(context, R.mipmap.ic_arrow_both);
            case OUTGOING:
                return ContextCompat.getDrawable(context, R.mipmap.ic_arrow_up);
            case INCOMING:
                return ContextCompat.getDrawable(context, R.mipmap.ic_arrow_down);
            case NOT_SET:
                return ContextCompat.getDrawable(context, R.mipmap.ic_arrow_unknown);
            default:
                return null;

        }
    }



}
