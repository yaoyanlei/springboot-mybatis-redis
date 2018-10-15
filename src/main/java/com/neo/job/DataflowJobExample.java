package com.neo.job;

import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.neo.entity.Moment;

public class DataflowJobExample implements DataflowJob<Moment> {

	@Override
	public List<Moment> fetchData(ShardingContext shardingContext) {
		System.err.println("-------fetch data-------");
		return null;
	}

	@Override
	public void processData(ShardingContext shardingContext, List<Moment> data) {
		System.err.println("-------process data-------");
	}

}
