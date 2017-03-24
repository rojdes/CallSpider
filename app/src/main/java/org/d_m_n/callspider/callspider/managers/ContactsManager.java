package org.d_m_n.callspider.callspider.managers;

import android.content.Context;
import android.text.TextUtils;

import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.db.DatabaseHelper;
import org.d_m_n.callspider.callspider.db.model.ContactDb;
import org.d_m_n.callspider.callspider.model.CommonContact;
import org.d_m_n.callspider.callspider.model.Mapping;
import org.d_m_n.callspider.callspider.model.enums.ForbiddenDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;
import org.d_m_n.callspider.callspider.tools.ContactTools;
import org.d_m_n.callspider.callspider.tools.RandomGenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        if (!PreferencesManager.isContactsCopied()) {
            fillContactsWrapEception(false);
        }
        try {
            return Mapping.from(dbHelper.getContactDao().getAllContacts());
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean updateContact(CommonContact contact){
        if(contact == null){
            Logger.e(TAG, " update contact; contact is null");
            return false;
        }
        try {
            dbHelper.getContactDao().update(Mapping.from(contact));
            return true;
        } catch (SQLException e) {
            Logger.e(TAG, "updateContact " +e.toString());
            return false;
        }
    }

    public CommonContact getContactBy(String number){
        if (TextUtils.isEmpty(number)){
            return null;
        }
        try {
            List<ContactDb> ll= dbHelper.getContactDao().queryForEq("number", number.replace(" ", ""));
            if(ll != null) {
                return Mapping.from(ll.get(0));
            }
        } catch (SQLException e) {
            Logger.e(TAG, "getContactBy " +e.toString());
            return null;
        }
        return null;
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

    //FIXME logic of first launch. Possibly use splash screen
    private void fillContacts(boolean keepOld) throws SQLException {
        if (PreferencesManager.isFirstLaunched()){
            dbHelper.getContactDao().create(convertFrom(ContactTools.getNativeContacts(mContext)));
        } else if (!keepOld) {
            //TODO Verify if needed clear here
            //dbHelper.clearContactsTable();
            for (ContactDb c : convertFrom(ContactTools.getNativeContacts(mContext)))
                dbHelper.getContactDao().createOrUpdate(c);

        } else {
            updateAppContactsFromNative();
        }
        PreferencesManager.setContactsCopied(true);
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
            c.setDirection(ForbiddenDirection.NOT_SET.toString());
            cc.add(c);
        }
        Logger.e(TAG, "cc" + cc.size());
        return cc;
    }

    public ArrayList<CommonContact> getFakeContacts() {
        ArrayList<CommonContact> list = new ArrayList<CommonContact>();
        Random random = new Random();
        for(int i=0; i<200; i++){
            CommonContact c = new CommonContact(ContactCategory.values()[random.nextInt(ContactCategory.values().length)]);
            c.direction = ForbiddenDirection.values()[random.nextInt(ForbiddenDirection.values().length)];
            c.contactId = random.nextLong();
            c.name = RandomGenerator.randomString(10);
            c.number = String.valueOf(random.nextLong());
            list.add(c);
        }
        return list;
    }
}
