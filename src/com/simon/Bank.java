package com.simon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	
	public int[] accounts;  //账户余额数组
	private Lock bankLock;
	private Condition sufficientFund;
	
	/**
	 * 
	 * @param n 数组大小
	 */
	public Bank() {
		bankLock = new ReentrantLock();
		sufficientFund = bankLock.newCondition();
		accounts = new int[10];
		for(int i = 0; i < accounts.length; i++) {
			accounts[i] = 1000; //账号默认余额1000
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
			while(accounts[from] < amount) {
				try {
					sufficientFund.await();  
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			accounts[from] = accounts[from] - amount;
			accounts[to] = accounts[to] + amount;
			System.out.println("Total Balance: " + getTotalBalance());
			sufficientFund.signalAll();
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
