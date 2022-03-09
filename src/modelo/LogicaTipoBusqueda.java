package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.swing.JOptionPane;

import controlador.Coordinador;
import modelo.dao.BusquedaDao;
import modelo.dao.VueloDao;
import modelo.vo.AeropuertoVo;
import modelo.vo.VueloVo;
import net.datastructures.AdjacencyMapGraph;
import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.GraphAlgorithms;
import net.datastructures.Map;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.ProbeHashMap;
import net.datastructures.Vertex;

public class LogicaTipoBusqueda {
	private static final int TAM = 2; // Es el tamaño del arreglo de los totales.

	private static Coordinador coordinador = new Coordinador(); // objeto miCoordinador que permite la relacion entre
																// esta clase y la clase coordinador
	private BusquedaDao busquedaDao;
	private static List<AeropuertoVo> listaAeropuertos;

	public LogicaTipoBusqueda() {
		busquedaDao = new BusquedaDao();
		listaAeropuertos = new ArrayList<>();
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	// valida que los campos no esten vacios
	public boolean validarTipoDeBusqueda(VueloVo vueloVo, int itemTipoBusqueda) {
		boolean verificacion = true;
		if (vueloVo.getAeropuerto_origen() == "Seleccione" || vueloVo.getAeropuerto_destino() == "Seleccione"
				|| itemTipoBusqueda == -1) {
			JOptionPane.showMessageDialog(null, "Por favor Complete los datos", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			verificacion = false;

		} else if (vueloVo.getAeropuerto_origen().equals(vueloVo.getAeropuerto_destino())) {
			JOptionPane.showMessageDialog(null, "El Aeropuerto Origen debe ser distinto al Aeropuerto Destino",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			verificacion = false;

		} else if (itemTipoBusqueda == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un tipo de busqueda", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			verificacion = false;
		}
		return verificacion;

	}

	public Graph<String, VueloVo> crearGrafo(List<VueloVo> listaVuelos) {
		return busquedaDao.crearGrafo(listaVuelos);

	}

	/**
	 * Este método calcula el camino mas corto segun la opcion de los tipos de busqueda con hilos
	 * 
	 * @param g        recibe el grafo principal.
	 * @param orig     recibe el aeropuerto origen que el usuario eligio.
	 * @param dest     recibe el aeropuerto destino que el usuario eligio.
	 * @param busqueda recibe el tipo de busqueda que el usuario eligio.
	 * @return PositionalList<Vertex<String>> contiene los nodos que hacen el camino
	 *         mas corto.
	 */
	// Calcula el camino mas corto
	public List<PositionalList<Vertex<String>>> caminoMasCorto(Graph<String, VueloVo> g, String origen, String destino,
			int tipoBusqueda) {

		ExecutorService es = Executors.newCachedThreadPool();
		List<FutureTask> hilos = new ArrayList<FutureTask>();

		// Copio y creo los grafos para aplicar el algoritmo de disktra
		Graph<String, Integer> grafo = masEconomico(g);
		Graph<String, Integer> grafo2 = menosHoras(g);
		

		System.out.println("------------------------ hiloooo ---------------------------------");

		// Creo los hilos
		hilos.add(new FutureTask(new LogicaHilo(grafo, origen, destino, "Mas Economico")));// mas economico
		hilos.add(new FutureTask(new LogicaHilo(grafo2, origen, destino, "Menos Horas")));// menos horas

		// Los ejecuto
		for (FutureTask futureTask : hilos) {
			es.submit(futureTask);
		}

		/**
		 * [0] mas economico [1] menos horas [2] menos escalas
		 */
		List<PositionalList<Vertex<String>>> listCaminos = new ArrayList<>();
		boolean finProcesos;

		// Me aseguro que todos los hilos finalicen y devuelvan un valor.
		while (true) {

			finProcesos = true;

			for (FutureTask hilo : hilos) {
				if (!hilo.isDone()) {
					finProcesos = false;
				}
			}

			if (finProcesos) {
				for (FutureTask hilo : hilos) {
					try {
						listCaminos.add((PositionalList<Vertex<String>>) hilo.get());

					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}
				break;
			}
		}

		// Finalizo el ExecutorService
		es.shutdown();

		boolean verif = true;
		//Verifico que las lista no esten nulas
		for (PositionalList<Vertex<String>> positionalList : listCaminos) {
			if(positionalList == null) {
				verif = false;
			}
		}
		
		// Se añade el menos escala
		if(verif) {
			listCaminos.add(menosEscalas(listCaminos.get(0), listCaminos.get(1)));
		}
		

		return listCaminos;

	}

	/**
	 * Este método calcula el camino mas corto de los aeropuerto con menos horas.
	 * 
	 * @param g recibe el grafo principal
	 * @return Graph<String, Integer> retorna un grafo de tipo String,integer ya que
	 *         Disktra trabaja solo con enteros.
	 */
	private Graph<String, Integer> menosHoras(Graph<String, VueloVo> grafos) {
		// Crea un grafo con edge solo de entero para calcular el camino mas corto con
		// dikstra
		Graph<String, Integer> grafo = new AdjacencyMapGraph<>(false);
		// Creo un Mapa con los vertices
		Map<Vertex<String>, Vertex<String>> mapaVertice = new ProbeHashMap<>();
		for (Vertex<String> guardaVertice : grafos.vertices()) { // obtengo los vertices y los agrego al mapa
			mapaVertice.put(guardaVertice, grafo.insertVertex(guardaVertice.getElement()));
		}
		Vertex<String>[] vertice;

		for (Edge<VueloVo> arco : grafos.edges()) {
			// Tranformo la hora en un entero
			String hora = arco.getElement().getTiempo_vuelo();
			String[] parts1 = hora.split(":");
			Integer tiempo_vuelo = Integer.parseInt(parts1[0] + parts1[1]);
			System.out.println(tiempo_vuelo);

			vertice = grafos.endVertices(arco);
			grafo.insertEdge(mapaVertice.get(vertice[0]), mapaVertice.get(vertice[1]), (tiempo_vuelo));// origen,
																										// destino y
																										// horario
		}
		return grafo;
	}

	/**
	 * Este método calcula el camino mas corto de los aeropuertos mas economicos.
	 * 
	 * @param g recibe el grafo principal
	 * @return Graph<String, Integer> retorna un grafo de tipo String,integer ya que
	 *         Disktra trabaja solo con enteros.
	 */
	private Graph<String, Integer> masEconomico(Graph<String, VueloVo> grafos) {
		// Crea un grafo con edge solo de entero para calcular el camino mas corto con
		// dikstra
		Graph<String, Integer> grafo = new AdjacencyMapGraph<>(false);
		// Creo un Mapa con los vertices
		Map<Vertex<String>, Vertex<String>> mapaVertice = new ProbeHashMap<>();
		// recorre el grafo
		for (Vertex<String> guardaVertice : grafos.vertices()) { // obtengo los vertices y los agrego al mapa
			// vertice
			mapaVertice.put(guardaVertice, grafo.insertVertex(guardaVertice.getElement()));
		}
		//
		Vertex<String>[] vertice;

		for (Edge<VueloVo> arco : grafos.edges()) { // recorre el grafo trayendo los arcos
			vertice = grafos.endVertices(arco); // obtiene los vertice que estan en el extremo del arco
			// inserta en el nuevo grafo el precio
			grafo.insertEdge(mapaVertice.get(vertice[0]), mapaVertice.get(vertice[1]), arco.getElement().getPrecio());// origen,
																														// destino
																														// y
																														// precio
		}

		return grafo;
	}

	/**
	 * Este método calcula los 3 metodos (Economico, menos horas) y elige el que
	 * posee menos escalas.
	 * 
	 * @param g    recibe el grafo principal
	 * @param orig recibe el aeropuerto origen que el usuario eligio.
	 * @param dest recibe el aeropuerto destino que el usuario eligio.
	 * @return PositionalList<Vertex<String>> contiene los nodos que hacen el camino
	 *         mas corto.
	 */
	private PositionalList<Vertex<String>> menosEscalas(PositionalList<Vertex<String>> economico,
			PositionalList<Vertex<String>> horas) {

		if (economico.size() > horas.size()) {
			return horas;
		}
		return economico;
	}

	/**
	 * Este método separa el camino mas corto en 3 partes, los nombres por un lado,
	 * la información por otro y por último los totales(precio y cantidad de hora).
	 * 
	 * @param grafo        recibe el grafo principal
	 * @param caminoCorto  recibe el camino mas corto
	 * @param tipoBusqueda
	 * @param nombres      recibe los nombres de los aeropuertos
	 * @return ArrayList<Object[]>
	 */
	public static ArrayList<Object[]> obtenerBusqueda(Graph<String, VueloVo> grafo,
			List<PositionalList<Vertex<String>>> caminoCorto, int tipoBusqueda) {
		String mensaje = "";
		// Arreglo que retornamos, toma el tamaño de la PositionalList
		String[] fechaVuelo = new String[caminoCorto.get(tipoBusqueda).size()];
		String[] caminoNombre = new String[caminoCorto.get(tipoBusqueda).size()]; //
		String[] edge = new String[caminoCorto.get(tipoBusqueda).size()];
		Object[] totales = new Object[TAM];
		ArrayList<Object[]> mostrar = new ArrayList<>();

		/**
		 * [0] mas economico [1] menos horas [2] menos escalas
		 */

		// Almaceno los vertices del grafo
		ArrayList<Vertex<String>> vertices = new ArrayList<Vertex<String>>();
		// Almaceno los arcos
		ArrayList<VueloVo> vuelo = new ArrayList<>();

		// obtengo los vertices del grafo q estan en la lista
		for (Position<Vertex<String>> aux : caminoCorto.get(tipoBusqueda).positions()) {
			vertices.add(busqueda(grafo, aux.getElement().getElement()));
		}
		// obtengo los arcos
		for (int k = 0; k < caminoCorto.get(tipoBusqueda).size() - 1; k++) {
			vuelo.add(arcos(grafo, vertices.get(k), vertices.get(k + 1)));
		}

		int totalPrecio = 0, totalHora = 0, hora = 0, minutos = 0, totalMinuto = 0;

		AeropuertoVo nombreVuelo = null;
		listaAeropuertos = coordinador.getLogicaAeropuerto().validarConsultaAeropuerto();

		// Recorre la cantidad de veces que tiene un nodo
		for (int i = 0; i < caminoCorto.get(tipoBusqueda).size(); i++) {
			String abreviacionVuelo = vertices.get(i).getElement();
			// Busca la abreviacion en la lista de los aeropuertos y guarda el nombre para
			// mostrar en los paneles
			for (AeropuertoVo aeropuertoVo : listaAeropuertos) {
				if (aeropuertoVo.getAbreviacion().equals(abreviacionVuelo.trim())) {
					nombreVuelo = aeropuertoVo;
				}
			}

			fechaVuelo[i] = (i < vuelo.size() ? vuelo.get(i).getFecha().toString() : "");
			caminoNombre[i] = "(" + vertices.get(i).getElement() + ") " + nombreVuelo.getNombre();
			edge[i] = (i < vuelo.size() ? "&nbsp;&nbsp;&nbsp; ~ &nbsp;Nombre de Vuelo:&nbsp;"
					+ vuelo.get(i).getNumero_vuelo() + "<br> &nbsp;&nbsp;&nbsp; ~ &nbsp; Precio:$&nbsp;"
					+ vuelo.get(i).getPrecio() + "<br> &nbsp;&nbsp;&nbsp; ~ &nbsp; Demora en Aeropuerto:&nbsp;"
					+ vuelo.get(i).getDemora() + "<br> &nbsp;&nbsp;&nbsp; ~ &nbsp; Horas de Vuelo:&nbsp;"
					+ vuelo.get(i).getTiempo_vuelo() + " hs." : "");

			System.out
					.println("tiempo vuelo sin formatear: " + (i < vuelo.size() ? vuelo.get(i).getTiempo_vuelo() : 0));
			String tiempo = (i < vuelo.size() ? vuelo.get(i).getTiempo_vuelo() : "");
			String[] tiempoVuelo = tiempo.split(":");

			hora = (tiempoVuelo.length < 2 ? 0 : Integer.parseInt(tiempoVuelo[0]));
			minutos = (tiempoVuelo.length < 2 ? 0 : Integer.parseInt(tiempoVuelo[1]));
			totalHora += hora;
			totalMinuto += minutos;
			if (totalMinuto > 59) {
				totalHora = totalHora + 1;
				totalMinuto = totalMinuto - 60;
			} else {
				totalHora = totalHora;
				totalMinuto = totalMinuto;
			}

			totalPrecio += (i < vuelo.size() ? vuelo.get(i).getPrecio() : 0);

			/*
			 * mensaje += "" + vertices.get(i).getElement() + "<br>    " + (i < vuelo.size()
			 * ? vuelo.get(i).getPrecio() : "") + "<br>      " + (i < vuelo.size() ?
			 * vuelo.get(i).getDemora() : "") + "<br>     " + (i < vuelo.size() ?
			 * vuelo.get(i).getTiempo_vuelo() : "") + "    ";
			 */

		}
		String horas = String.valueOf(totalHora + ":" + totalMinuto + " hs");
		System.out.println("valueOf : " + horas);
		// mensaje = "caminoNombre: " + caminoNombre +"Total hora = "+totalHora +" total
		// minutos: "+ totalMinuto + " precio: "+totalPrecio;

		totales[0] = totalPrecio + "";
		totales[1] = horas;
		mostrar.add(fechaVuelo);
		mostrar.add(caminoNombre);
		mostrar.add(edge);
		mostrar.add(totales);
		return mostrar;
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

	/**
	 * Este método busca el arco en el grafo y lo devuelve.
	 * 
	 * @param g        recibe el grafo principal
	 * @param vertice  recibe los nodos cuya información se quiere buscar.
	 * @param vertice2 recibe los nodos cuya información se quiere buscar.
	 * @return Edge<Vuelo> retorna la información que estan entre los nodos
	 *         recibidos
	 */
	private static VueloVo arcos(Graph<String, VueloVo> g, Vertex<String> vertice, Vertex<String> vertice2) {
		Edge<VueloVo> arco = null;
		arco = g.getEdge(vertice, vertice2);
		return arco.getElement();
	}

}
