package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue
{
	
	
	private Object obj;

	//创建锁
	ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();
	
	public void readObj()
	{
		//上读锁
		rrw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"读到的内容是："+obj);
		} finally {
			//解读锁
			rrw.readLock().unlock();
		}
	}
	
	public void writeObj(Object obj)
	{
		//上写锁
		rrw.writeLock().lock();
		try {
			this.obj = obj;
			System.out.println(Thread.currentThread().getName()+"写入内容");
		} finally {
			//解写锁
			rrw.writeLock().unlock();
		}
	}
	
}

/**
 * 
 * @Description: 一个线程写入,100个线程读取
 * 
 */
public class ReadWriteLockDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		//创建资源对象
		MyQueue mq = new MyQueue();
		
		//一个线程写
		new Thread(()->{
			//写内容
			mq.writeObj("hadoop20190311");
		}, "AA").start();
		
		//100个线程读
		for (int i = 1; i <= 100; i++) {
			new Thread(()->{
				//读内容
				mq.readObj();
			}, String.valueOf(i)).start();
		}
	}
}
