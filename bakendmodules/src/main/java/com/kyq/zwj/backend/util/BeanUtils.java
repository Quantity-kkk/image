package com.kyq.zwj.backend.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 13:45
 */
public class BeanUtils {
    private static final Logger tracer = LoggerFactory.getLogger(BeanUtils.class);
    /**
     * 复制bean的内容到map中
     */
    public static Map bean2Map(Object objBean) {
        Map mapRet = new HashMap();
        try {
            if (objBean != null) {
                PropertyDescriptor[] props = Introspector.getBeanInfo(objBean.getClass()).getPropertyDescriptors();

                for (PropertyDescriptor property : props) {
                    String propertyName = property.getName();
                    String propertyClassName = property.getPropertyType().getCanonicalName();
                    Object valueList = BeanUtils.getProperty(objBean, propertyName);

                    if (propertyClassName.equals("java.util.List")) {
                        if (StringUtil.isNotEmpty(valueList)) {
                            List colNew = new ArrayList();
                            for (Object objItem : ((List) valueList)) {
                                Map mapDataItem = bean2Map(objItem);
                                colNew.add(mapDataItem);
                            }
                            mapRet.put(propertyName, colNew);
                        }
                    } else {
                        mapRet.put(propertyName, valueList);
                    }
                }
            }
        } catch (Exception e) {
            tracer.error(""+e);
        }

        return mapRet;
    }

    /**
     * 根据属性名字获取bean对象中的值
     */
    public static Object getProperty(Object objBean, String key) {
        Object value = null;
        try {
            value = PropertyUtils.getProperty(objBean, key);
        } catch (Exception e) {
            tracer.error("Can't get [" + key + "] in [" + objBean.getClass().getSimpleName() + "]");
        }
        return value;
    }
    /**
     * 将map转为entity对象
     * */
    public static <T> T map2Bean(Map srcMap, Class desBeanClass){
        Object desBean = getObject(desBeanClass);
        try {
            if (desBean != null && MapUtil.isNotEmpty(srcMap)) {
                    PropertyDescriptor[] props = Introspector.getBeanInfo(desBeanClass).getPropertyDescriptors();

                    for (PropertyDescriptor property : props) {
                        String propertyName = property.getName();
                        Class propertyClass = property.getPropertyType();

                        if (!Class.class.equals(propertyClass) && srcMap.containsKey(propertyName)) {
                            Object value = srcMap.get(propertyName);
                            if(StringUtil.isNotEmpty(value))
                                PropertyUtils.setProperty(desBean, propertyName, value);
                        }
                    }
            }
        } catch (Exception e) {
            tracer.error(""+e);
            throw new RuntimeException(e);
        }
        return (T)desBean;
    }

    public static Object getObject(Class clazz){
        Object desBean = null;
        try {
            desBean = clazz.newInstance();
        } catch (Exception e) {
            tracer.error(""+e);
            throw new RuntimeException(e);
        }
        return desBean;
    }
}
