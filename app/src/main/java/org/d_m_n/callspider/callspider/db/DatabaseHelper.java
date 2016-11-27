package org.d_m_n.callspider.callspider.db;


import android.content.Context;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.d_m_n.callspider.callspider.db.dao.ContactDao;
import org.d_m_n.callspider.callspider.db.model.ContactDb;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DB_NAME = "contacts.db";

    private static final int DB_VERSION = 1;
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private ContactDao mContactDao;

    public DatabaseHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable(connectionSource, ContactDao.class);
        }
        catch (SQLException e){
            Log.e(TAG, "error creating DB " + DB_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
      Log.e(TAG, "CALLED UPDATE TABLE");
    }

    public ContactDao getContactDao() throws SQLException {
        if(mContactDao == null){
            mContactDao = new ContactDao(getConnectionSource(), ContactDb.class);
        }
        return mContactDao;
    }

    /**
     * Wrap {@link #getContactDao()} with exception
     *
     * @return null if {@link Exception} or {@link ContactDao} instance
     */
    public ContactDao getContactDaoWrapEception(){
        try{
            return getContactDao();
        } catch (SQLException e){
            Log.e(TAG, "getContactDaoWrapException " + e);
            return null;
        }
    }
}
