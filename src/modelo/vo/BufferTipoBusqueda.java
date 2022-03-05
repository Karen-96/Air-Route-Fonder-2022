package modelo.vo;

import java.util.concurrent.ArrayBlockingQueue;

import net.datastructures.Graph;

public class BufferTipoBusqueda implements Buffer {
	
	//private int cantidadTipoBuqueda;
	private final ArrayBlockingQueue<Graph<String, Integer>> cola;
	
	//Contructor
	public BufferTipoBusqueda(int cantidadTipoBuqueda) {
		cola = new ArrayBlockingQueue<>(cantidadTipoBuqueda);
	}

	@Override
	public void set(Graph<String, Integer> grafo) throws InterruptedException {
		cola.put(grafo);
		
	}

	@Override
	public Graph<String, Integer> get() throws InterruptedException {
		return cola.poll();
	}

	@Override
	public int size() {
		return cola.size();
	}
	
	

}
