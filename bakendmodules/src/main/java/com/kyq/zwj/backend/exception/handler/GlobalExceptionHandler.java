package com.kyq.zwj.backend.exception.handler;

import com.kyq.zwj.backend.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 21:57
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {

        //所有错误转向同一个页面，提供一个errorCode供业务人员排查.
        String errorCode = UUIDUtil.getUUID();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        model.put("errorCode",errorCode);
        ex.printStackTrace();
        //error log tracer
        logger.error(String.format("[errorCode:%s;errorMsg:%s]",errorCode,ex.getMessage()));
        ModelAndView modalView = new ModelAndView("../bizmodules/jsp/exception/error_page", model);
        modalView.addObject("errorCode1",errorCode);
        return modalView;
    }
}
