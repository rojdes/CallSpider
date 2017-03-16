package org.d_m_n.callspider.callspider.model;

import org.d_m_n.callspider.callspider.model.enums.BlockedDataType;
import org.d_m_n.callspider.callspider.model.enums.ForbiddenDirection;

/**
 * Created by d1m11n on 3/17/17.
 */

public class HistoryData {

    public BlockedDataType type;

    public String number;

    public ForbiddenDirection direction;

    /**
     * Can contain for ex sms content
     */
    public String additional;


}
