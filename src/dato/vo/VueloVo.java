package dato.vo;

import java.sql.Time;
import java.sql.Timestamp;

public class VueloVo {
	
	private int idvuelo;
	private String numero_vuelo;
	private Timestamp fecha;
	private int precio;
	private AeropuertoVo aeropuerto_origen;
	private AeropuertoVo aeropuerto_destino;
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
	
	public AeropuertoVo getAeropuerto_origen() {
		return aeropuerto_origen;
	}

	public void setAeropuerto_origen(AeropuertoVo aeropuerto_origen) {
		this.aeropuerto_origen = aeropuerto_origen;
	}

	public AeropuertoVo getAeropuerto_destino() {
		return aeropuerto_destino;
	}

	public void setAeropuerto_destino(AeropuertoVo aeropuerto_destino) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idvuelo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VueloVo other = (VueloVo) obj;
		if (idvuelo != other.idvuelo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VueloVo {\nidvuelo=" + idvuelo + ",\n numero_vuelo=" + numero_vuelo + ",\n fecha=" + fecha
				+ ",\n precio=" + precio + ",\n aeropuerto_origen=" + aeropuerto_origen + ",\n aeropuerto_destino="
				+ aeropuerto_destino + ",\n tiempo_vuelo=" + tiempo_vuelo + ",\n demora=" + demora + "\n}";
	}
	
	

}
