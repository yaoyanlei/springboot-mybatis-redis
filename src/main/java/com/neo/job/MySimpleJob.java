package com.neo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MySimpleJob implements SimpleJob {

	@Override
	public void execute(ShardingContext shardingContext) {
		System.err.println("----------5分钟执行一次-----------");
	}

}
