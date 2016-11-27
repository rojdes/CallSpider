package org.d_m_n.callspider.callspider.model;

import org.d_m_n.callspider.callspider.db.model.ContactDb;
import org.d_m_n.callspider.callspider.model.enums.ContactCallForbidDirection;
import org.d_m_n.callspider.callspider.model.enums.ContactCategory;

import java.util.ArrayList;
import java.util.List;

public final class Mapping {

    public static AppContact from(ContactDb model) {
        if (model == null) {
            return null;
        }
        AppContact cm = new AppContact(ContactCategory.valueOf(model.getCategory()));
        cm.contactId = model.getContactId();
        cm.number = model.getNumber();
        cm.direction = ContactCallForbidDirection.valueOf(model.getDirection());
        cm.name = model.getName();
        return cm;
    }

    public static ContactDb from(AppContact model){
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

    public static ArrayList<AppContact> from(List<ContactDb> contacts){
        if(contacts == null){
            return null;
        }
        ArrayList<AppContact> cc = new ArrayList<>();
        for(ContactDb c:contacts){
            cc.add(from(c));
        }
        return cc;
    }
}
