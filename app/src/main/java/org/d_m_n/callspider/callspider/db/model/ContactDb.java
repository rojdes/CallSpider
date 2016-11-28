package org.d_m_n.callspider.callspider.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by d1m11n on 11/11/16.
 */


@DatabaseTable(tableName = "contacts")
public class ContactDb {

    @DatabaseField
    private String name;

    @DatabaseField(id = true)
    private long contactId;

    @DatabaseField
    private String number;

    @DatabaseField
    private String category;

    @DatabaseField
    private String direction;

    public ContactDb(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
