package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;

import org.d_m_n.callspider.callspider.model.CommonContact;

/**
 * Created by dmytro.radchenko on 3/28/2017.
 */

class ContactFilterListCallback extends SortedList.Callback<CommonContact> {


    private final RecyclerView.Adapter mAdapter;

    ContactFilterListCallback(RecyclerView.Adapter adapter){
        mAdapter = adapter;
    }

    @Override
    public void onInserted(int position, int count) {
        mAdapter.notifyItemRangeInserted(position, count);
    }

    @Override
    public void onRemoved(int position, int count) {
        mAdapter.notifyItemRangeRemoved(position, count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        mAdapter.notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onChanged(int position, int count) {
        mAdapter.notifyItemRangeChanged(position, count);
    }

    @Override
    public int compare(CommonContact cc1, CommonContact cc2) {
        return cc1.name.compareTo(cc2.name);
    }

    @Override
    public boolean areContentsTheSame(CommonContact oldItem, CommonContact newItem) {
        return oldItem.name == newItem.name;
    }

    @Override
    public boolean areItemsTheSame(CommonContact item1, CommonContact item2) {
        return Long.compare(item1.contactId,item2.contactId) == 0L;
    }


}
