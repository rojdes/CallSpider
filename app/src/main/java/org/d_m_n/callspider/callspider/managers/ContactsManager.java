package org.d_m_n.callspider.callspider.managers;

import android.content.Context;

import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.db.DatabaseHelper;
import org.d_m_n.callspider.callspider.db.model.ContactDb;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.Mapping;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;
import org.d_m_n.callspider.callspider.tools.ContactTools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d1m11n on 11/14/16.
 */

public class ContactsManager {

    private static final String TAG = ContactsManager.class.getSimpleName();
    private final Context mContext;
    private DatabaseHelper dbHelper;

    private static ContactsManager INSTANCE;

    private ContactsManager(Context context) {
        this.mContext = context;
        dbHelper = new DatabaseHelper(context);
    }

    public static ContactsManager with(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ContactsManager(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public ArrayList<CommonContact> getContacts() {
        if (!PreferencesManager.isContactsCopied(mContext)) {
            fillContactsWrapEception(false);
        }
        try {
            return Mapping.from(dbHelper.getContactDao().getAllContacts());
        } catch (SQLException e) {
            return null;
        }
    }

    public void resetContacts() {
        fillContactsWrapEception(true);
    }

    public void updateContacts(){
        fillContactsWrapEception(false);
    }

    private void fillContactsWrapEception(boolean keepOld){
        try {
            fillContacts(keepOld);
        } catch (SQLException e) {
            Logger.e(TAG, "fillcontacts exception " + e.toString());
        }
    }

    private void fillContacts(boolean keepOld) throws SQLException {
        if (PreferencesManager.isFirstLaunched(mContext)){
            dbHelper.getContactDao().create(convertFrom(ContactTools.getNativeContacts(mContext)));
            PreferencesManager.setContactsCopied(mContext);
            return;
        }
        if (!keepOld) {
            //dbHelper.clearContactsTable();
            for (ContactDb c : convertFrom(ContactTools.getNativeContacts(mContext)))
                dbHelper.getContactDao().createOrUpdate(c);
            PreferencesManager.setContactsCopied(mContext);
        } else {
            updateAppContactsFromNative();
        }
    }

    private void updateAppContactsFromNative() {
        Logger.e(TAG, "updateAppContacts STUBBBBB !!!!!");
    }


    private List<ContactDb>  convertFrom(List<ContactTools.NativeContact> list){
        if(list == null){
            return null;
        }
        ArrayList<ContactDb> cc = new ArrayList<>(list.size());
        for (ContactTools.NativeContact n:list) {
            ContactDb c = new ContactDb();
            c.setCategory(ContactCategory.NATIVE.toString());
            c.setContactId(Long.parseLong(n.id));
            c.setName(n.name);
            c.setNumber(n.number);
            c.setDirection(ContactCallForbidDirection.NOT_SET.toString());
            cc.add(c);
        }
        Logger.e(TAG, "cc" + cc.size());
        return cc;
    }
}
