package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.NamingException;

import dao.ClientesDAO;
import dao.ClientesDAOImp;
import dao.ElectrodomesticosDAO;
import dao.ElectrodomesticosDAOImp;
import dao.OdsDAO;
import dao.OdsDAOImp;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private ClientesDAO clientesDAO;
	private ElectrodomesticosDAO electrodomesticosDAO;
	private OdsDAO odsDAO;
	
	@Override
	public void init() throws ServletException{
		super.init();
		this.clientesDAO = new ClientesDAOImp();
		this.electrodomesticosDAO = new ElectrodomesticosDAOImp(this.clientesDAO);
		this.odsDAO= new OdsDAOImp(this.electrodomesticosDAO);
	}
	
    public Controller() 
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "listarclte":	
			List<Cliente> clientes	= null;
			try {
				clientes 			= clientesDAO.findAllClientes();
			} catch ( Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("clientes", clientes);			
			request.getRequestDispatcher("/WEB-INF/jsp/vista/listaclte.jsp").forward(request, response);
			break;
		
		case "listarartefacto":
			List<Electrodomestico>  electrodomesticos 	= null;
			try {
				electrodomesticos 	= electrodomesticosDAO.findAllElectrodomesticos();
			} catch(Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("electrodomesticos", electrodomesticos);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/listaartefacto.jsp").forward(request, response);
			break;
			
		case "listarods":
			List<OrdenDeServicio> ods = null;
			try {
				ods = odsDAO.findAllOrdenesDeServicio();				
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("ods", ods);	
			request.getRequestDispatcher("/WEB-INF/jsp/vista/listaods.jsp").forward(request, response);
			break;
		
		case "formulario":
			request.getRequestDispatcher("/WEB-INF/jsp/vista/formulario-A.jsp").forward(request, response);
			break;
			
		case "editar":
			
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				
				OrdenDeServicio odsEdit = odsDAO.findOrdenDeServicioById(id);
				
				
				request.setAttribute("ods", odsEdit);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/modificar-orden.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
			
		case "verOds":
			
			id = Integer.parseInt(request.getParameter("id"));
			try {
				
				OrdenDeServicio odsEdit = odsDAO.findOrdenDeServicioById(id);
				
				request.setAttribute("ods", odsEdit);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/Ods.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			break;
		
		default:
			response.sendError(500);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "addCliente":

			String nombre 		= request.getParameter("nombre");
			String direccion	= request.getParameter("direccion");
			String telefono 	= request.getParameter("telefono");

			Cliente nuevoCliente = new Cliente(nombre,direccion,telefono);
			
			try {
				
				clientesDAO.createCliente(nuevoCliente);
				
				nuevoCliente = clientesDAO.findLastCreatedCliente();

				request.setAttribute("cliente", nuevoCliente);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/formulario-B.jsp").forward(request, response);				
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			break;
		
		case "addElectrodomestico":
		
			int idCliente = Integer.parseInt(request.getParameter("id"));

			Cliente cliente = null;
			try {
				cliente = clientesDAO.findClienteById(idCliente);
			} catch (SQLException | NamingException e1) {
				e1.printStackTrace();
				response.sendError(500);
			}

			String tipoArtefacto 	= request.getParameter("tipoArtefacto");
			String problemaArtefacto 	= request.getParameter("problemaArtefacto");

			Electrodomestico electrodomestico = new Electrodomestico(tipoArtefacto,problemaArtefacto,cliente);

			
			try {

				electrodomesticosDAO.createElectrodomestico(electrodomestico);
				
				electrodomestico = electrodomesticosDAO.findLastCreatedElectrodomestico();

				LocalDate fecha = LocalDate.now();

				String estado = "Pendiente";
				OrdenDeServicio Ods = new OrdenDeServicio(estado,fecha,fecha,electrodomestico);
				odsDAO.createOrdenDeServicio(Ods);

				request.setAttribute("cliente", cliente);
				request.getRequestDispatcher("/WEB-INF/jsp/vista/formulario-B.jsp").forward(request, response);		
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
			}
			break;
			
		case "finalizar":
			idCliente = Integer.parseInt(request.getParameter("id"));
			
			try {
				Electrodomestico elec = electrodomesticosDAO.findElectrodomesticoByClienteId(idCliente);
				if(elec == null) {
					//elimina el ultimo cliente creado si no hay electrodomesticos asociados
					Cliente clienteAEliminar = clientesDAO.findLastCreatedCliente();
					clientesDAO.deleteCliente(clienteAEliminar.getId());
					
					// redirecciona junto a una alerta de que se ha cancelado la orden
					request.setAttribute("success", 0);
					request.getRequestDispatcher("Index.jsp").forward(request, response);
				}

			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("success", 1);
			request.getRequestDispatcher("Index.jsp").forward(request, response);				
			break;
			
		case "finalizarEdit":
			// toma los id de los objetos asociados a la orden de trabajo
			int idOds 				= Integer.parseInt(request.getParameter("idOds"));
			idCliente 				= Integer.parseInt(request.getParameter("idCliente"));
			int idArtefacto 		= Integer.parseInt(request.getParameter("idElectrodomestico"));

			String estadoOrden 		= request.getParameter("estadoOrden");

			tipoArtefacto 				= request.getParameter("tipoArtefacto");
			problemaArtefacto 			= request.getParameter("problemaArtefacto");
			
			String nombreCliente 	= request.getParameter("nombreCliente");
			String direccionCliente = request.getParameter("direccionCliente");
			String telefonoCliente 	= request.getParameter("telefonoCliente");

			try {
				electrodomestico 	= electrodomesticosDAO.findElectrodomesticoById(idArtefacto);
				cliente				= electrodomestico.getCliente_id();

				electrodomestico.setTipo(tipoArtefacto);
				electrodomestico.setProblema(problemaArtefacto);
				
				cliente.setNombre(nombreCliente);
				cliente.setDireccion(direccionCliente);
				cliente.setTelefono(telefonoCliente);

				electrodomesticosDAO.editElectrodomestico(electrodomestico);
				clientesDAO.editCliente(cliente);
				
				odsDAO.editOrdenDeServicio(new OrdenDeServicio(idOds,estadoOrden));
				request.setAttribute("success", 2);
				request.getRequestDispatcher("Index.jsp").forward(request, response);	
				
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
	
		default:
			response.sendError(500);
		}
	}
}
