package com.neo.constant;

/**
 * 不同场景下动态浏览的增量
 * @author sinky
 */
public class MomentAccessCnt {
	
	/**
	 * 视频或者图文类型的动态 浏览量+28
	 */
	public static final int PIC_OR_MOVIE_MOMENT_ACCESS_CNT = 28;
	
	/**
	 * 文章类型的动态 浏览量+18
	 */
	public static final int SPECIAL_MOMENT_ACCESS_CNT = 18;
	
	/**
	 * feed流中的动态 浏览量+1
	 */
	public static final int FEED_MOMENT_ACCESS_CNT = 1;
	
	/**
	 * 点赞或者评论的动态 浏览量+5
	 */
	public static final int LIKE_OR_RECOMMENDS_MOMENT_ACCESS_CNT = 5;

}
