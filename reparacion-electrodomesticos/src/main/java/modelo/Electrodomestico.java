package modelo;

public class Electrodomestico {
	
	//PROPIEDADES Y ATRIBUTOS
	private int id;
	private String tipo;
	private String problema;
	private Cliente cliente_id;
	
		//CONSTRUCTORES
		public Electrodomestico() {
		}

		public Electrodomestico(String tipo, String problema, Cliente cliente_id) {
			this.tipo 		= tipo;
			this.problema 	= problema;
			this.cliente_id = cliente_id;
		}

		public Electrodomestico(int id, String tipo, String problema, Cliente cliente_id) {
			this.id 		= id;
			this.tipo 		= tipo;
			this.problema 	= problema;
			this.cliente_id = cliente_id;
		}
		
		//GETTERS Y SETTERS
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getProblema() {
			return problema;
		}

		public void setProblema(String problema) {
			this.problema = problema;
		}

		public Cliente getCliente_id() {
			return cliente_id;
		}

		public void setCliente_id(Cliente cliente_id) {
			this.cliente_id = cliente_id;
		}

		
}
