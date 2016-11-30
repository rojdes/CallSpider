package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.tools.ContactTools;
import org.d_m_n.callspider.callspider.ui.adapters.holders.ContactViewHolder;
import org.d_m_n.callspider.callspider.ui.views.DirectionSelectView;

import java.util.ArrayList;

/**
 * Created by d1m11n on 11/11/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private ArrayList<CommonContact> contacts;

    public ContactListAdapter(ArrayList<CommonContact> contacts){
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
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        CommonContact contact = contacts.get(position);
        holder.tvContactName.setText(String.valueOf(contact.name));
        holder.tvContactNumber.setText(String.valueOf(contact.number));
        holder.ivContactDirection.setImageDrawable(contact.direction.getDrawable(holder.ivContactDirection.getContext()));
        holder.lltDirectionSelect.hide(contact.direction);
        holder.lltDirectionSelect.setVisibility(View.GONE);
        holder.ivContactDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.lltDirectionSelect.setVisibility(holder.lltDirectionSelect.getVisibility() == View.GONE ? View.VISIBLE :View.GONE);
            }
        });
        holder.lltDirectionSelect.setClickListener(new DirectionSelectView.DirectionClickListener() {
            @Override
            public void onDirectionClick(ContactCallForbidDirection d) {
                holder.ivContactDirection.setImageDrawable(d.getDrawable(holder.ivContactDirection.getContext()));
                holder.lltDirectionSelect.setVisibility(View.GONE);
            }
        });
    }
}
