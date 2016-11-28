package org.d_m_n.callspider.callspider.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.d_m_n.callspider.callspider.app.Logger;
import org.d_m_n.callspider.callspider.db.model.ContactDb;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by d1m11n on 11/11/16.
 */

public class ContactDao extends BaseDaoImpl<ContactDb, Long> {

    private static final String TAG = ContactDao.class.getSimpleName();

    public ContactDao(ConnectionSource connectionSource, Class<ContactDb> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<ContactDb> getAllContacts() {
        try {
            return this.queryForAll();
        } catch (SQLException e) {
            Logger.e(TAG, "getAllContacts e =" +e.toString());
            return null;
        }
    }
}
