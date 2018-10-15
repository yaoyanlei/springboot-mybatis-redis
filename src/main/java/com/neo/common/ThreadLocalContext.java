package com.neo.common;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ThreadLocalContext extends InheritableThreadLocal<RequestContext> {
    /**
     * 上下文信息
     */
    private static final ThreadLocalContext threadLocalContext = new ThreadLocalContext() {
        @Override
        protected RequestContext initialValue() {
            return new RequestContext();
        }
    };

    /**
     * 获得当前线程对象
     * 
     * @return
     */
    public static ThreadLocalContext getInstance() {
        return threadLocalContext;
    }
}
