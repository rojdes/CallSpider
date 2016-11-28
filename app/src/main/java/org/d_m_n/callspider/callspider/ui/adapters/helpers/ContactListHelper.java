package org.d_m_n.callspider.callspider.ui.adapters.helpers;

import org.d_m_n.callspider.callspider.model.CommonContact;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by d1m11n on 11/28/16.
 */

public class ContactListHelper {

    public static void sortContactListByCategory(List<CommonContact> list){
        if(list == null){
            return;
        }
        Collections.sort(list, new Comparator<CommonContact>() {
            @Override
            public int compare(CommonContact lhs, CommonContact rhs) {
                return Integer.compare(lhs.category.getPriority(), rhs.category.getPriority());
            }
        });
    }
}
