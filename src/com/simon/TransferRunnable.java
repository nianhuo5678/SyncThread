package com.simon;

import java.util.Random;

public class TransferRunnable implements Runnable {

	private Bank bank;
	private Random ran;

	public TransferRunnable(Bank bank) {
		super();
		this.bank = bank;
		ran = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			bank.transfer(ran.nextInt(bank.size()), ran.nextInt(bank.size()), ran.nextInt(50) + 1);
			try {
				Thread.sleep(ran.nextInt(5) * 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
