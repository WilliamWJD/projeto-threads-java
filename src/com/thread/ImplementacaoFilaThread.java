package com.thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread {
	private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_fila = new ConcurrentLinkedQueue<ObjetoFilaThread>();

	public static void add(ObjetoFilaThread obj) {
		pilha_fila.add(obj);
	}

	@Override
	public void run() {
		// ajustando pilha para iteração
		Iterator iteracao = pilha_fila.iterator();

		// syncronized bloqueia o acesso a esta lista por outros processos
		synchronized (iteracao) {
			while (iteracao.hasNext()) {/* enquanto conter dados na lista irá processar */
				ObjetoFilaThread processar = (ObjetoFilaThread) iteracao.next();

				/* processar 10 mil notas fiscais */
				/* fim do processo */

				iteracao.remove();

				try {
					Thread.sleep(100); // dar um tempo para descarga de memoria
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		try {
			Thread.sleep(1000); // processou toda a lista, da um tempo para descarga de memoria
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
