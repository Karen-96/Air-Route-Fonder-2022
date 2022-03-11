package dato.vo;

public class AeropuertoVo {
	
	private int idAeropuerto;
	private String abreviacion;
	private String nombre;

	public int getIdAeropuerto() {
		return idAeropuerto;
	}
	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}
	public String getAbreviacion() {
		return abreviacion;
	}
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abreviacion == null) ? 0 : abreviacion.hashCode());
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
		AeropuertoVo other = (AeropuertoVo) obj;
		if (abreviacion == null) {
			if (other.abreviacion != null)
				return false;
		} else if (!abreviacion.equals(other.abreviacion))
			return false;
		return true;
	}
	
	
	

}
