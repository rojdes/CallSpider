package org.d_m_n.callspider.callspider.model;

import org.d_m_n.callspider.callspider.db.model.ContactDb;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;

import java.util.ArrayList;
import java.util.List;

public final class Mapping {

    public static CommonContact from(ContactDb model) {
        if (model == null) {
            return null;
        }
        CommonContact cm = new CommonContact(ContactCategory.valueOf(model.getCategory()));
        cm.contactId = model.getContactId();
        cm.number = model.getNumber();
        cm.direction = ContactCallForbidDirection.valueOf(model.getDirection());
        cm.name = model.getName();
        return cm;
    }

    public static ContactDb from(CommonContact model){
        if (model == null) {
            return null;
        }
        ContactDb dbModel =new ContactDb();
        dbModel.setCategory(model.category.toString());
        dbModel.setContactId(model.contactId);
        dbModel.setDirection(model.direction.toString());
        dbModel.setNumber(model.number);
        dbModel.setName(model.name);
        return dbModel;
    }

    public static ArrayList<CommonContact> from(List<ContactDb> contacts){
        if(contacts == null){
            return null;
        }
        ArrayList<CommonContact> cc = new ArrayList<>();
        for(ContactDb c:contacts){
            cc.add(from(c));
        }
        return cc;
    }
}
