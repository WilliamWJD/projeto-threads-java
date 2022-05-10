package com.thread;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// THREAD PARA ENVIAR E-MAIL EM BACKGROUND
		Thread threadEmail = new Thread(thread1);
		threadEmail.start();

		Thread threadSavalS3 = new Thread(thread2);
		threadSavalS3.start();

		JOptionPane.showMessageDialog(null, "Sistema continua executando");
	}

	private static Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println("Salvando dados no bucket S3");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private static Runnable thread1 = new Runnable() {

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println("Executando rotina de envio de e-mail");
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
}
