package org.d_m_n.callspider.callspider.model;

import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;


//TODO: RENAME CLASSNAME
public class AppContact extends NativeContact{



    public AppContact(ContactCategory category){
        super();
        this.category = category;
    }

    public ContactCallForbidDirection direction;

    public long contactId;


}
