package com.tg.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {

	//任务队列
	protected Queue<Object> workQueue=new ConcurrentLinkedDeque<Object>();
	
	//Worker进程队列
	protected Map<String,Thread> threadMap=new HashMap<String,Thread>();
	
	//子任务处理结果集
	protected Map<String,Object> resultMap=new HashMap<String,Object>();
	
	//判断所有子任务是否已经结束
	public boolean isComplete(){
		for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
			if(Thread.State.TERMINATED!=entry.getValue().getState()){
				return false;
			}
		}
		return true;
	}
	
	public Master(Worker worker,int countWorker){
		worker.setWorkQueue(workQueue);
		worker.setResultMap(resultMap);
		for(int i=0;i<countWorker;i++){
			threadMap.put(Integer.toString(i), new Thread(worker,Integer.toString(i)));
		}
	}
	
	//提交任务
	public void submit(Object obj){
		workQueue.add(obj);
	}
	
	//返回子任务结果集
	public Map<String,Object> getResultMap(){
		return resultMap;
	}
	
	//开始处理worker中和任务
	public void execute(){
		for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
			entry.getValue().start();
		}
	}
}
