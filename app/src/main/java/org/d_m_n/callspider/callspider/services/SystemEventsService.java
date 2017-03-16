package org.d_m_n.callspider.callspider.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import org.d_m_n.callspider.callspider.observers.ContactsContentObserver;

/**
 * Class for handling systemEvernts like updating native contact
 * Created by dmytro.radchenko on 3/16/2017.
 */

public final class SystemEventsService extends Service {


    private ContactsContentObserver mContactsObserver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        registerReceivers(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceivers(this);
    }

    private void registerReceivers(Context ctxt) {
        mContactsObserver = new ContactsContentObserver();
        ctxt.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, mContactsObserver);
    }

    private void unregisterReceivers(Context ctxt) {
        ctxt.getContentResolver().unregisterContentObserver(mContactsObserver);
    }
}
