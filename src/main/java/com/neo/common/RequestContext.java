package com.neo.common;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neo.controller.UserController;

/**
 * @author zhouqisheng
 *
 */
@SuppressWarnings("serial")
public class RequestContext implements Serializable {

    private static final Logger REQUEST = LoggerFactory.getLogger("request");
    protected static Logger logger = LoggerFactory.getLogger(RequestContext.class);

    // 数据部统计日志
    private String response;

    private boolean readMaster = false;

    // 用于统计请求的时间
    private long startTime;

    // 是否打印返回结果
    private boolean printResponse = true;

    private transient HttpServletRequest originRequest;
    private transient HttpServletResponse originResponse;

    /**
     * 记录请求的用户uid
     */
    private long uid;
    private int appId;
    /**
     * /原始appId
     */
    private int originAppId;
    private String ip;

    private String token;

    public static RequestContext getRequestContext() {
        return ThreadLocalContext.getInstance().get();
    }

    public static void clear() {
        ThreadLocalContext.getInstance().remove();
    }

    public boolean isReadMaster() {
        return readMaster;
    }

    public void setReadMaster(boolean readMaster) {
        this.readMaster = readMaster;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isPrintResponse() {
        return printResponse;
    }

    public void setPrintResponse(boolean printResponse) {
        this.printResponse = printResponse;
    }

    public HttpServletRequest getOriginRequest() {
        return originRequest;
    }

    public void setOriginRequest(HttpServletRequest originRequest) {
        this.originRequest = originRequest;
    }

    public HttpServletResponse getOriginResponse() {
        return originResponse;
    }

    public void setOriginResponse(HttpServletResponse originResponse) {
        this.originResponse = originResponse;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAppId() {
        return appId;
    }

    public int getOriginAppId() {
        return originAppId;
    }

    public void setOriginAppId(int originAppId) {
        this.originAppId = originAppId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public static void logRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request == null) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        String token = RequestContext.getRequestContext().getToken();
        int status = response.getStatus();
        long now = System.currentTimeMillis();

        String responseStr = "";
        if (RequestContext.getRequestContext().isPrintResponse()) {
            responseStr = RequestContext.getRequestContext().getResponse();
        }
        REQUEST.info("{} uid:{} usetime:{} appId:{} token:{} url:{} request:{} response:{}", status,
                RequestContext.getRequestContext().getUid(), (now - RequestContext.getRequestContext().getStartTime()),
                RequestContext.getRequestContext().getAppId(), token, request.getRequestURI(), map, responseStr);

        logger.info( "response:{}",responseStr);
        System.err.println("返回报文："+responseStr);
    }

}
