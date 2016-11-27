package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.model.AppContact;
import org.d_m_n.callspider.callspider.tools.ContactTools;
import org.d_m_n.callspider.callspider.ui.adapters.holders.ContactViewHolder;

import java.util.ArrayList;

/**
 * Created by d1m11n on 11/11/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private ArrayList<AppContact> contacts;

    public ContactListAdapter(ArrayList<AppContact> contacts){
        this.contacts =contacts;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itm_contact_list, parent,false);
        return new ContactViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return contacts == null? 0 : contacts.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        AppContact contact = contacts.get(position);
        holder.contactName.setText(contact.name);
        holder.contactNumber.setText(contact.number);
        holder.contactCategoryIcon.setImageDrawable(ContactTools.getContactDirectionDrawable(holder.contactCategoryIcon.getContext(), contact.direction));
    }



}
