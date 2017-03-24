package org.d_m_n.callspider.callspider.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.PermissionManager;
import org.d_m_n.callspider.callspider.managers.UserNotifyManager;

import java.util.ArrayList;

/**
 * Created by d1m11n on 11/11/16.
 */

public final class ContactTools {

    public static class NativeContact{

        public String id;
        public String name;
        public String number;
    }

    public static ArrayList<NativeContact> getNativeContacts(Context context) {
        if (context == null) {
            return null;
        }
        //TODO move verification upper
        if(!PermissionManager.isPhonePermissionGranted(context)){
            UserNotifyManager.showToast(context, R.string.verify_permissions_error, true);
            return null;

        }
        ArrayList<NativeContact> allContacts = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        NativeContact n = new NativeContact();
                        n.id = id;
                        n.number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace(" ", "");
                        n.name = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        allContacts.add(n);
                        break;
                    }
                    pCur.close();
                }

            } while (cursor.moveToNext());
        }
        return allContacts;
    }


}
