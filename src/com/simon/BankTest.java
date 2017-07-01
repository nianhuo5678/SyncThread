package com.simon;

public class BankTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank bank = new Bank();
		Runnable r = new TransferRunnable(bank);
		for(int i = 0; i < 20; i++) {
			Thread t = new Thread(r);
			t.start();
		}
	}

}
