package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.app.MainApp;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.enums.ForbiddenDirection;
import org.d_m_n.callspider.callspider.ui.adapters.holders.ContactViewHolder;
import org.d_m_n.callspider.callspider.ui.views.ItemContactView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d1m11n on 11/11/16.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private ArrayList<CommonContact> allContacts;
    private final SortedList<CommonContact> mSortedList;
   //http://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
    public ContactListAdapter(ArrayList<CommonContact> contacts){
       this.allContacts =contacts;
       mSortedList  = new SortedList<>(CommonContact.class, new ContactFilterListCallback(this));
       mSortedList.addAll(contacts);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemContactView rootView = new ItemContactView(parent.getContext());
        return new ContactViewHolder(rootView);
    }

    @Override
    public int getItemCount() {
        return mSortedList == null? 0 : mSortedList.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {
        final CommonContact contact = mSortedList.get(position);
        holder.cstmItemContactView.setContact(contact);
        holder.cstmItemContactView.setItemClickListener(new ItemContactView.ItemContactClickListener() {

            @Override
            public void onDirectionChanged(ForbiddenDirection d) {
                //TODO verify if direction was changed
                ContactsManager.with(MainApp.getAppContext()).updateContact(contact);
            }
        });
    }

    /**
     *
     * @return is empty
     */
    public boolean filterWith(CharSequence query, RecyclerView recyclerView) {
        if (allContacts == null || allContacts.size() == 0){
            return true;
        }
        if (TextUtils.isEmpty(query)){
            replaceAll(allContacts);
            return  false;
        }
        final List<CommonContact> filteredModelList = filter(allContacts, query);
        replaceAll(filteredModelList);
        recyclerView.scrollToPosition(0);
        return filteredModelList.isEmpty();
    }

    private static List<CommonContact> filter(List<CommonContact> models, CharSequence query) {
        final String lowerCaseQuery = query.toString().toLowerCase();

        final List<CommonContact> filteredModelList = new ArrayList<>();
        for (CommonContact model : models) {
            if (model.name.toLowerCase().contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public void replaceAll(List<CommonContact> models) {
        mSortedList.beginBatchedUpdates();
        mSortedList.clear();
        mSortedList.addAll(allContacts);
        for (int i = mSortedList.size() - 1; i >= 0; i--) {
            final CommonContact model = mSortedList.get(i);
            if (!models.contains(model)) {
                mSortedList.remove(model);
            }
        }
        mSortedList.endBatchedUpdates();
    }


}
