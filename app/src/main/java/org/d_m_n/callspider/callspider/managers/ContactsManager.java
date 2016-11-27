package org.d_m_n.callspider.callspider.managers;

import android.content.Context;

import org.d_m_n.callspider.callspider.db.DatabaseHelper;
import org.d_m_n.callspider.callspider.model.AppContact;
import org.d_m_n.callspider.callspider.model.Mapping;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by d1m11n on 11/14/16.
 */

public class ContactsManager {

    private final Context context;
    private DatabaseHelper dbHelper;

    private static ContactsManager INSTANCE;

    private ContactsManager(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public static ContactsManager with(Context context){
        if(INSTANCE == null){
            INSTANCE = new ContactsManager(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public ArrayList<AppContact> getContacts(){
        //SharedPrefs first start or was not created any contact inside db

        if(true) {
            try {
                return Mapping.from(dbHelper.getContactDao().getAllContacts());
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
}
