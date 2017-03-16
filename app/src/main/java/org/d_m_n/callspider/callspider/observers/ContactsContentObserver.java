package org.d_m_n.callspider.callspider.observers;

import android.database.ContentObserver;

import org.d_m_n.callspider.callspider.app.Logger;

/**
 * Created by dmytro.radchenko on 3/16/2017.
 */

public class ContactsContentObserver extends ContentObserver {

    private static final String TAG = ContactsContentObserver.class.getSimpleName();

    public ContactsContentObserver() {
        super(null);
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Logger.d(TAG, "STUB!!! onChange smth ");

    }
}
