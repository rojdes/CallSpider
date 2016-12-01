package org.d_m_n.callspider.callspider.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.ContactsManager;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;
import org.d_m_n.callspider.callspider.ui.adapters.CategoriesListAdapter;
import org.d_m_n.callspider.callspider.ui.adapters.ContactListAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by d1m11n on 12/1/16.
 */

public class CategoriesFragment extends BaseFragment {

    @BindView(R.id.rv_categories_list)
    protected RecyclerView mrvCategoriesList;


    private LinearLayoutManager mLayoutManager;
    private CategoriesListAdapter mCategoriesListAdapter;


    public static CategoriesFragment newInstance(){
        CategoriesFragment f = new CategoriesFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_categories, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setContactList();

    }

    private void setContactList() {
        mrvCategoriesList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mrvCategoriesList.setLayoutManager(mLayoutManager);
        mCategoriesListAdapter = new CategoriesListAdapter(Arrays.asList(ContactCategory.values()));
        mrvCategoriesList.setAdapter(mCategoriesListAdapter);
    }
}
