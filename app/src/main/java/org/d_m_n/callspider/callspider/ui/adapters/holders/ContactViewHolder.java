package org.d_m_n.callspider.callspider.ui.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.d_m_n.callspider.callspider.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/16/16.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.tv_item_contact_list_name)
    public TextView contactName;

    @BindView(R.id.tv_item_contact_list_number)
    public TextView contactNumber;


    @BindView(R.id.iv_item_contact_category_item_icon)
    public ImageView contactCategoryIcon;

    @BindView(R.id.iv_item_contact_category_item_direction)
    public ImageView contactDirection;

    public ContactViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
