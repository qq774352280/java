package com.tg.controller;

import java.util.Map;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Worker implements Runnable {

	//任务队列,取子任务
	protected Queue<Object> workQueue;
	
	//子任务处理结果集
	protected Map<String,Object> resultMap;
	
	public Object handle(Object input){
		return input;
	}
	@Override
	public void run() {
		while(true){
			//获取子任务
			Object input = workQueue.poll();
			if(null==input){
				break;
			}
			//处理子任务
			Object re = handle(input);
			//将子任务结果写入结果集中
			resultMap.put(Integer.toString(input.hashCode()), re);
		}
	}

}
