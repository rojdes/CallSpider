package org.d_m_n.callspider.callspider.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.managers.UserNotifyManager;
import org.d_m_n.callspider.callspider.observers.ContactsContentObserver;
import org.d_m_n.callspider.callspider.ui.MainActivity;

/**
 * Class for handling systemEvernts like updating native contact
 * Created by dmytro.radchenko on 3/16/2017.
 */

public final class SystemEventsService extends Service {


    private static final String TAG = SystemEventsService.class.getSimpleName();
    private ContactsContentObserver mContactsObserver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UserNotifyManager.showPersistantNotification(this, getString(R.string.app_name), getString(R.string.spider_is_active), MainActivity.class);
        Logger.e(TAG, "onCreate");
        registerReceivers(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e(TAG, "onDestroy");
        unregisterReceivers(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void registerReceivers(Context ctxt) {
        mContactsObserver = new ContactsContentObserver();
        ctxt.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, mContactsObserver);
    }

    private void unregisterReceivers(Context ctxt) {
        ctxt.getContentResolver().unregisterContentObserver(mContactsObserver);
    }
}
