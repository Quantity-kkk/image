package com.kyq.zwj.backend.helper;

import com.kyq.zwj.backend.util.StringUtil;
import java.util.Properties;

/**
 * Description:  SysconfigHelper
 * Copyright: © 2014 CSNT. All rights reserved.
 * Company:CSNT
 *
 * @author kyq1024
 * @version 1.0
 */
public class SysconfigHelper {
    private static final String SYS_CONFIG_FILE = "sysconfig.properties";
    private static Properties sysProperties = new Properties();

    // 加载配置文件
    static {
        try {
            sysProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(SYS_CONFIG_FILE));
        } catch (Exception e) {
            System.out.println("sysconfig.properties not found!");
        }
    }

    /**
     * 读取对应的配置
     */
    public static String getProperty(String strKey) {
        String strValue = "";

        try {
            strValue = sysProperties.getProperty(strKey);
        } catch (Exception e) {
            System.out.println("Property <" + strKey + "> not found!");
        }

        return StringUtil.replaceNull(strValue);
    }
}
