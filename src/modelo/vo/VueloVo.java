package modelo.vo;

import java.sql.Time;
import java.sql.Timestamp;

public class VueloVo {
	
	private int idvuelo;
	private String numero_vuelo;
	private Timestamp fecha;
	private int precio;
	private String aeropuerto_origen;
	private String aeropuerto_destino;
	private String tiempo_vuelo;
	private String demora;
	

	public int getIdvuelo() {
		return idvuelo;
	}
	
	public void setIdvuelo(int idvuelo) {
		this.idvuelo = idvuelo;
	}	
	
	public String getNumero_vuelo() {
		return numero_vuelo;
	}

	public void setNumero_vuelo(String numero_vuelo) {
		this.numero_vuelo = numero_vuelo;
	}
	
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public String getAeropuerto_origen() {
		return aeropuerto_origen;
	}
	
	public void setAeropuerto_origen(String aeropuerto_origen) {
		this.aeropuerto_origen = aeropuerto_origen;
	}
	
	public String getAeropuerto_destino() {
		return aeropuerto_destino;
	}
	
	public void setAeropuerto_destino(String aeropuerto_destino) {
		this.aeropuerto_destino = aeropuerto_destino;
	}
	public String getTiempo_vuelo() {
		return tiempo_vuelo;
	}

	public void setTiempo_vuelo(String tiempo_vuelo) {
		this.tiempo_vuelo = tiempo_vuelo;
	}

	public String getDemora() {
		return demora;
	}

	public void setDemora(String demora) {
		this.demora = demora;
	}

	@Override
	public String toString() {
		return "VueloVo {\nidvuelo=" + idvuelo + ",\n numero_vuelo=" + numero_vuelo + ",\n fecha=" + fecha
				+ ",\n precio=" + precio + ",\n aeropuerto_origen=" + aeropuerto_origen + ",\n aeropuerto_destino="
				+ aeropuerto_destino + ",\n tiempo_vuelo=" + tiempo_vuelo + ",\n demora=" + demora + "\n}";
	}
	
	

}
