package com.tg.controller;

public class PlusWorker extends Worker{

	@Override
	public Object handle(Object input) {
		String name = Thread.currentThread().getName();
		Integer i=Integer.valueOf(String.valueOf(input));
		System.out.println("线程:"+name+"运行:"+i);
		return i;
	}
	
}
