package modelo;

import java.util.concurrent.Callable;

import controlador.Coordinador;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.PositionalList;
import net.datastructures.Vertex;

public class LogicaHilo implements Callable<PositionalList<Vertex<String>>>{
	Coordinador coordinador;
	private String origen;
	private String destino;
	private Graph<String, Integer> grafo;
	private String tipoBusqueda;
	
	public LogicaHilo(Graph<String, Integer> grafo, String origen, String destino, String tipoBusqueda) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.tipoBusqueda = tipoBusqueda;
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
	public PositionalList<Vertex<String>> call() throws Exception {
		return GraphAlgorithms.shortestPathList(grafo, busqueda(grafo, origen), busqueda(grafo, destino));
	}

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	

}
