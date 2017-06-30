package com.simon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	
	public int[] accounts;  //账户余额数组
	private Lock bankLock;
	
	/**
	 * 
	 * @param n 数组大小
	 */
	public Bank() {
		bankLock = new ReentrantLock();
		accounts = new int[10];
		for(int i = 0; i < accounts.length; i++) {
			accounts[i] = 10000; //账号默认余额10000
		}
	}
	
	/**
	 * 转账方法，要使用锁防止多过一个线程同时调用本方法。
	 * @param from 转出账号
	 * @param to 转入账号
	 * @param amount 金额
	 */
	public void transfer(int from , int to, int amount) {
		bankLock.lock();
		try {
			accounts[from] = accounts[from] - amount;
			accounts[to] = accounts[to] + amount;
			System.out.println("Total Balance: " + getTotalBalance());
		} finally {
			bankLock.unlock();
		}

	}
	
	public int getTotalBalance() {
		int totalBalance = 0;
		for(int i = 0; i < accounts.length; i++) {
			totalBalance += accounts[i];
		}
		return totalBalance;
	}
	
	public int size() {
		return accounts.length;
	}
}
