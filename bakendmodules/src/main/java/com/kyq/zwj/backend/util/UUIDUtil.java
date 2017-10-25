package com.kyq.zwj.backend.util;

import java.util.UUID;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 22:02
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
