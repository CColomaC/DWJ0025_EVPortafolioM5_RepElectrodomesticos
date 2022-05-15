package modelo;

import java.time.LocalDate;

public class OrdenDeServicio {

	//PROPIEDADES Y ATRIBUTOS
	private int id;
	private String estado;
	private LocalDate fechaSolicitud;
	private LocalDate fechaActualizacionOrden;
	private Electrodomestico electrodomestico_id;
	
		//CONSTRUCTORES
		public OrdenDeServicio() {
		}
	
		public OrdenDeServicio(String estado, LocalDate fechaSolicitud, LocalDate fechaActualizacionOrden,
				Electrodomestico electrodomestico_id) {
			this.estado 					= estado;
			this.fechaSolicitud 			= fechaSolicitud;
			this.fechaActualizacionOrden 	= fechaActualizacionOrden;
			this.electrodomestico_id 		= electrodomestico_id;
		}
	
		public OrdenDeServicio(int id, String estado, LocalDate fechaSolicitud, LocalDate fechaActualizacionOrden,
				Electrodomestico electrodomestico_id) {
			super();
			this.id 						= id;
			this.estado 					= estado;
			this.fechaSolicitud 			= fechaSolicitud;
			this.fechaActualizacionOrden	= fechaActualizacionOrden;
			this.electrodomestico_id 		= electrodomestico_id;
		}
		
		public OrdenDeServicio(int id, String estado) {
			this.id 					  = id;
			this.estado 				  = estado;
		}
	
		//GETTERS Y SETTERS
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getEstado() {
			return estado;
		}
	
		public void setEstado(String estado) {
			this.estado = estado;
		}
	
		public LocalDate getFechaSolicitud() {
			return fechaSolicitud;
		}
	
		public void setFechaSolicitud(LocalDate fechaSolicitud) {
			this.fechaSolicitud = fechaSolicitud;
		}
	
		public LocalDate getFechaActualizacionOrden() {
			return fechaActualizacionOrden;
		}
	
		public void setFechaActualizacionOrden(LocalDate fechaActualizacionOrden) {
			this.fechaActualizacionOrden = fechaActualizacionOrden;
		}
	
		public Electrodomestico getElectrodomestico_id() {
			return electrodomestico_id;
		}
	
		public void setElectrodomestico_id(Electrodomestico electrodomestico_id) {
			this.electrodomestico_id = electrodomestico_id;
		}

}