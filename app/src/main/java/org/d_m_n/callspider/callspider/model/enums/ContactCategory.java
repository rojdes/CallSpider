package org.d_m_n.callspider.callspider.model.enums;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import org.d_m_n.callspider.callspider.R;

/**
 * Created by d1m11n on 11/11/16.
 */

public enum ContactCategory {

    //ADD POSSIBILITY CHANGE ORDER AND NAME

    //for android native contacts
    NATIVE(0),


    //girlfriend, friends
    HIDDEN(1),
    //family
    PRIVATE(2),
    WORK(3),
    SEX(4),
    PUBLIC(5),
    //if you have minimum suspicious to not call - for ex. neighbour
    BLACKLIST(6),
    //drinking friend
    FULL_ACCESS(7);

    private int priority =-1;

    private ContactCategory(int priority){
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }

    public Drawable getCategoryIcon(Context context){
        switch(this){
            case NATIVE:
                return ContextCompat.getDrawable(context, R.mipmap.ic_native);
            case SEX:
               return ContextCompat.getDrawable(context, R.mipmap.ic_sex);
            case PRIVATE:
                return ContextCompat.getDrawable(context, R.mipmap.ic_private);
            case WORK:
                return ContextCompat.getDrawable(context, R.mipmap.ic_work);
            case PUBLIC:
                return ContextCompat.getDrawable(context, R.mipmap.ic_public);
            default:
                return ContextCompat.getDrawable(context, R.mipmap.ic_default);
        }
    }


}
