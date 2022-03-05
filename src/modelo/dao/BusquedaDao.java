package modelo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import modelo.vo.VueloVo;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Graph;
import net.datastructures.Vertex;

public class BusquedaDao {

	public static Graph<String, VueloVo> crearGrafo(List<VueloVo> edges) {

		// Creo un grafo
		Graph<String, VueloVo> grafo = new AdjacencyMapGraph<>(false);

		// first pass to get sorted set of vertex labels
		TreeSet<String> labels = new TreeSet<>();

		// Obtiene los nodos de la lista y los agrega al TreeSet
		for (VueloVo vueloVo : edges) {
			labels.add(vueloVo.getAeropuerto_origen());
			labels.add(vueloVo.getAeropuerto_destino());
		}
		// now create vertices (in alphabetical order)
		// crea los vertice en orden alfabeticamente
		HashMap<String, Vertex<String>> verts = new HashMap<>();
		
		for (String label : labels)
			verts.put(label, grafo.insertVertex(label));//grafo

		// Inserta nuevos edge al grafo
		for (VueloVo vueloVo : edges) {
			VueloVo vuelo = new VueloVo();
			vuelo.setDemora(vueloVo.getDemora());
			vuelo.setFecha(vueloVo.getFecha());
			vuelo.setIdvuelo(vueloVo.getIdvuelo());
			vuelo.setNumero_vuelo(vueloVo.getNumero_vuelo());
			vuelo.setPrecio(vueloVo.getPrecio());
			vuelo.setTiempo_vuelo(vueloVo.getTiempo_vuelo());
			grafo.insertEdge(verts.get(vueloVo.getAeropuerto_origen()), verts.get(vueloVo.getAeropuerto_destino()), vuelo); // pasa
		}
			
		return grafo;
	}

}
