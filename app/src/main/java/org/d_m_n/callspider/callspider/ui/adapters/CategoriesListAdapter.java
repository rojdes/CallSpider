package org.d_m_n.callspider.callspider.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.MainApp;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;
import org.d_m_n.callspider.callspider.ui.adapters.holders.CategoryViewHolder;
import org.d_m_n.callspider.callspider.ui.adapters.holders.ContactViewHolder;
import org.d_m_n.callspider.callspider.ui.views.ItemContactView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d1m11n on 11/11/16.
 */
public class CategoriesListAdapter extends RecyclerView.Adapter<CategoryViewHolder>{

    private List<ContactCategory> categories;

    public CategoriesListAdapter(List<ContactCategory> categories){
        this.categories = categories;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itm_categories, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return categories == null? 0 : categories.size();
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
        final ContactCategory category = categories.get(position);
        holder.categoryName.setText(category.name());
        holder.categoryIcon.setBackground(category.getCategoryIcon(holder.categoryIcon.getContext()));
    }
}
