package com.kyq.zwj.backend.to;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Description:  EventBody
 * Copyright: © 2014 CSNT. All rights reserved.
 * Company:CSNT
 *
 * @author kyq1024
 * @version 1.0
 */
public class RequestParameterTO {
    private static final Logger trace = LoggerFactory.getLogger(RequestParameterTO.class);
    private String postData;
    private Map postMap;
    private List postList;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public Map getPostMap() {
        Map inMap = null;
        try {
            inMap = (Map) JSONUtil.deserialize(postData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return inMap;
    }

    public void setPostMap(Map postMap) {
        this.postMap = postMap;
    }

    public List getPostList() {
        return postList;
    }

    public void setPostList(List postList) {
        this.postList = postList;
    }
}
