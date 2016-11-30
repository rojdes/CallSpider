package org.d_m_n.callspider.callspider.model;

import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;

/**
 * Created by d1m11n on 11/17/16.
 */

public class CommonContact {



    public ContactCallForbidDirection direction;

    public long contactId;

    public ContactCategory category = ContactCategory.NATIVE;

    public String number;

    public String name;

    public CommonContact(ContactCategory category){
        this.category = category;
    }


    @Override
    public String toString() {
        return "CommonContact{" +
                "direction=" + direction +
                ", contactId=" + contactId +
                ", category=" + category +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
