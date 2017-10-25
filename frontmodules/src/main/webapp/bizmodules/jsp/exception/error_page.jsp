<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.struts2.json.JSONUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PrintWriter writer = response.getWriter();
    HashMap outMap = new HashMap();
    outMap.put("errorcode", request.getAttribute("errorCode"));
    outMap.put("msg", "系统错误请联系管理员");
    //outMap.put("message", strMessage);
    writer.write(JSONUtil.serialize(outMap));
    writer.close();
%>