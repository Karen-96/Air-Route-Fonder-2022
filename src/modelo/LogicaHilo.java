package modelo;

import controlador.Coordinador;
import modelo.vo.Buffer;
import modelo.vo.BufferTipoBusqueda;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.Vertex;

public class LogicaHilo implements Runnable {
	Coordinador coordinador;
	private BufferTipoBusqueda buffer;
	private String origen;
	private String destino;
	
	
	public <V> LogicaHilo( BufferTipoBusqueda buffer, String origen, String destino) {
		this.buffer = buffer;
		this.origen = origen;
		this.destino = destino;
		
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}
	
	/**
	 * Este método busca el vertice en el grafo y lo devuelve.
	 * 
	 * @param g          recibe un grafo
	 * @param aeropuerto recibe la abreviación del aeropuerto a buscar en el grafo.
	 * @return Vertex<V> retorna el nodo cuya abreviación es la que recibio por
	 *         parametro.
	 */

	public static <V, E> Vertex<V> busqueda(Graph<V, E> g, String aeropuerto) {
		for (Vertex<V> aux : g.vertices())
			if (aux.getElement().equals(aeropuerto))
				return aux;
		return null;
	}

	@Override
	public void run() {
		if(buffer.size() > 0) {
			try {
				Graph<String, Integer> grafo = buffer.get();
				GraphAlgorithms.shortestPathList(grafo, busqueda(grafo, origen), busqueda(grafo, destino));
				
				
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
		
	}
	

}
