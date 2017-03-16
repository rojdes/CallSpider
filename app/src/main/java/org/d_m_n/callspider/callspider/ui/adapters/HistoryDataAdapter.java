package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.app.MainApp;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.HistoryData;
import org.d_m_n.callspider.callspider.model.enums.ForbiddenDirection;
import org.d_m_n.callspider.callspider.ui.adapters.holders.ContactViewHolder;
import org.d_m_n.callspider.callspider.ui.views.ItemContactView;

import java.util.ArrayList;

/**
 * Created by d1m11n on 11/11/16.
 */
public class HistoryDataAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private ArrayList<HistoryData> history;

    public HistoryDataAdapter(ArrayList<HistoryData> contacts){
        this.history = history;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContactView rootView = new ItemContactView(parent.getContext());
        return new ContactViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return history == null? 0 : history.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        final HistoryData contact = history.get(position);
//        holder.cstmItemContactView.setContact(contact);
//        holder.cstmItemContactView.setItemClickListener(new ItemContactView.ItemContactClickListener() {
//
//            @Override
//            public void onDirectionChanged(ForbiddenDirection d) {
//                //TODO verify if direction was changed
//                ContactsManager.with(MainApp.getAppContext()).updateContact(contact);
//            }
//        });
    }
}
