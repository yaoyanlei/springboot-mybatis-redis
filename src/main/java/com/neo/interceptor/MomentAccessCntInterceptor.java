package com.neo.interceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;import com.neo.common.PageDataByCursor;
import com.neo.common.RequestContext;
import com.neo.common.Result;
import com.neo.entity.HomeFeedVo;
import com.neo.entity.Moment;



@Component
public class MomentAccessCntInterceptor extends HandlerInterceptorAdapter {
	
	private final static String COMMUNITY_FEED_ATTENTION_LIST = "/pet/v1/community/feed/attention/list";
	private final static String COMMUNITY_FEED_ATTENTION_LIST_V2 = "/pet/v2/community/feed/attention/list";
	private final static String NEW_RECOMMEN_HOME_FEED = "/pet/v3/new_recommends/home/feed";
															
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.err.println("-----------------MomentAccessCntInterceptor请求前拦截-------------------");
//		System.err.println(getJSONString(request));
		//pre(request);
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		
		System.err.println("--------------请求完成后处理---------------");
		System.err.println("请求URL："+request.getRequestURL().toString());
		//getResponseInfo(response, true);
		//http://localhost:8080/user/jedistest
		//System.err.println(response.getOutputStream().toString());
		 
		
	}
	
	
	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		System.err.println("------------最后面拦截-----------");
		RequestContext.logRequest(request, response);
		String responseStr = RequestContext.getRequestContext().getResponse();
		if (null == responseStr || "".equals(responseStr)) {
			System.err.println("---请求返回为空---");
			return;
		}
		System.err.println("请求的状态：-----------------"+response.getStatus());
		//关注流请求
		if (COMMUNITY_FEED_ATTENTION_LIST.equals(request.getRequestURI())
				||COMMUNITY_FEED_ATTENTION_LIST_V2.equals(request.getRequestURI())) {
			parseAttention(responseStr);
		}else if(NEW_RECOMMEN_HOME_FEED.equals(request.getRequestURI())) {
			parseRecommends(responseStr);
		}else {
			System.err.println("error request");
		}
		RequestContext.clear();
	}

	private void parseRecommends(String responseStr) {
		//推荐流解析
		try {
			Result<?> result = JSONObject.parseObject(responseStr, Result.class);
			if (result.isStatus()&&result.getData()!=null) {
				JSONObject json = JSONObject.parseObject(result.getData().toString(), JSONObject.class);
				//Object pageCnt = json.get("pageCnt");
				//System.err.println("----------pageCnt:"+pageCnt);
				Object data = json.get("data");
				if (data != null) {
					List<HomeFeedVo> list = JSONObject.parseArray(data.toString(), HomeFeedVo.class);
					if (CollectionUtils.isNotEmpty(list)) {
						for (HomeFeedVo homeFeedVo : list) {
							System.err.println(homeFeedVo.getMoment().toString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseAttention(String responseStr) {
		try {
			System.err.println("---------------请求关注流-----------------");
			Result<?> result = JSONObject.parseObject(responseStr, Result.class);
			if (result.isStatus()&&result.getData()!=null) {
				PageDataByCursor<?> pageDataByCursor = JSONObject.parseObject(result.getData().toString(), PageDataByCursor.class);
				List<?> list = pageDataByCursor.getData();
				System.err.println("首页流成都："+list.size());
				if (CollectionUtils.isNotEmpty(list)) {
					List<HomeFeedVo> listVo = JSONObject.parseArray(list.toString(), HomeFeedVo.class);
					for (HomeFeedVo homeFeedVoz : listVo) {
						System.err.println(homeFeedVoz.getMoment().toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  String CHARSET = "UTF-8";
	
	public  String getJSONString(HttpServletRequest request){
		String json = "";
		try { 
		ServletInputStream in = request.getInputStream(); 
		String content = IOUtils.toString(in, CHARSET);
		json = URLDecoder.decode(content, CHARSET); 
		json = json.substring(json.indexOf("=") + 1);
		} catch (IOException e) { 
		e.printStackTrace();
		}
		return json;
		}

	
	
	public void pre(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
        sb.append("{");

        Enumeration<String> headers = request.getHeaderNames();
        int i = 0;
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();

            if (i > 0)
                sb.append(", ");
            sb.append(header + ": " + request.getHeader(header));
            i++;
        }
        sb.append("}");
        System.err.println(getRequestInfo(request, true));
	}
	
	
	private static String getResponseInfo(HttpServletResponse request, boolean requestDetails) {
        StringBuffer sb = new StringBuffer();
//        sb.append(request.getMethod()).append(" ");
//        sb.append(request.getRequestURI());
        request.getHeader("Data");
        if (requestDetails) {
            Collection<String> e = request.getHeaderNames();
            System.err.println(e.size());
            e.forEach(Str -> System.err.println(Str));
         
        }

        return sb.toString();
    }
	
	private static String getRequestInfo(HttpServletRequest request, boolean requestDetails) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getMethod()).append(" ");
        sb.append(request.getRequestURI());
        if (requestDetails) {
            Enumeration<String> e = request.getParameterNames();
            sb.append("{");
            int i = 0;
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String val = request.getParameter(name);
                if (val != null && !val.isEmpty()) {
                    if (i > 0)
                        sb.append(", ");
                    sb.append(name).append(": ").append(val);
                    i++;
                }
            }
            sb.append("}");
        }
        return sb.toString();
    }

}
