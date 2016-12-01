package org.d_m_n.callspider.callspider.ui.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.d_m_n.callspider.callspider.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 11/17/16.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_category_icon)
    public ImageView categoryIcon;

    @BindView(R.id.tv_item_category_name)
    public TextView categoryName;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
