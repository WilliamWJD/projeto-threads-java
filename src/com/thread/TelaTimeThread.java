package com.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel jPanel = new JPanel(new GridBagLayout());

	private JLabel descricaoHora = new JLabel("Time Thread 1");
	private JTextField mostraTempo = new JTextField();

	private JLabel descricaoHora2 = new JLabel("Time Thread 2");
	private JTextField mostraTempo2 = new JTextField();

	private JButton buttonStart = new JButton("Start");
	private JButton buttonStop = new JButton("Stop");

	private Runnable thread1 = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					mostraTempo.setText(
							new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	private Runnable thread2 = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					mostraTempo2.setText(
							new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	private Thread thread1Time;
	private Thread thread2Time;
	
	public TelaTimeThread() {
		setTitle("Minha tela de time com Thread");
		setSize(new Dimension(240, 240));
		// centraliza a tela
		setLocationRelativeTo(null);
		setResizable(false);

		// controla posição dos elementos na tela
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		// ---------------------------------------------
		jPanel.add(descricaoHora, gridBagConstraints);
		descricaoHora.setPreferredSize(new Dimension(200, 25));

		gridBagConstraints.gridy++;
		jPanel.add(mostraTempo, gridBagConstraints);
		mostraTempo.setEditable(false);
		mostraTempo.setPreferredSize(new Dimension(200, 25));

		// ---------------------------------------------

		gridBagConstraints.gridy++;
		jPanel.add(descricaoHora2, gridBagConstraints);
		descricaoHora2.setPreferredSize(new Dimension(200, 25));

		gridBagConstraints.gridy++;
		jPanel.add(mostraTempo2, gridBagConstraints);
		mostraTempo2.setEditable(false);
		mostraTempo2.setPreferredSize(new Dimension(200, 25));

		// --------------------------------------------

		gridBagConstraints.gridwidth = 1;

		// ---------------------------------------------

		buttonStart.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy++;
		jPanel.add(buttonStart, gridBagConstraints);

		buttonStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx++;
		jPanel.add(buttonStop, gridBagConstraints);

		// evento de clique do botão start
		buttonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time = new Thread(thread1);
				thread2Time = new Thread(thread2);
				
				thread1Time.start();
				thread2Time.start();
				
				buttonStop.setEnabled(true);
				buttonStart.setEnabled(false);
			}
		});

		// evento de clique do botão stop
		buttonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1Time.stop();
				thread2Time.stop();
				
				buttonStop.setEnabled(false);
				buttonStart.setEnabled(true);
			}
		});

		add(jPanel, BorderLayout.WEST);
		
		buttonStop.setEnabled(false);
		
		// exibe a tela para o usuário
		setVisible(true);
	}
}
