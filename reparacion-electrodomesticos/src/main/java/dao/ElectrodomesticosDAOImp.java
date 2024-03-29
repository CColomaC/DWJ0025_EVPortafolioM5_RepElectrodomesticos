package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Cliente;
import modelo.Electrodomestico;

public class ElectrodomesticosDAOImp implements ElectrodomesticosDAO {

	private ClientesDAO clientesDAO;
	
	public ElectrodomesticosDAOImp(ClientesDAO clientesDAO) {
		this.clientesDAO = clientesDAO;
	}
	
	
	@Override
	public List<Electrodomestico> findAllElectrodomesticos() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM electrodomestico");
			List<Electrodomestico> electrodomesticos = new ArrayList<>();
			while(rs.next()) {
				int id 				= rs.getInt("id_electrodomestico");
				String tipo 		= rs.getString("tipo");
				String problema 	= rs.getString("problema");
				int idCliente 		= rs.getInt("id_cliente");
				Cliente cliente 	= clientesDAO.findClienteById(idCliente);
				
				Electrodomestico electrodomestico= new Electrodomestico(id,tipo,problema,cliente);
				electrodomesticos.add(electrodomestico);
			}
			return electrodomesticos;
		}
	}

	@Override
	public Electrodomestico findElectrodomesticoById(int electrodomesticoId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM electrodomestico WHERE id_electrodomestico = ?");
			) {
			ps.setInt(1, electrodomesticoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 				= rs.getInt("id_electrodomestico");
				String tipo 		= rs.getString("tipo");
				String problema 	= rs.getString("problema");
				int idCliente 		= rs.getInt("id_cliente");
				Cliente cliente 	= clientesDAO.findClienteById(idCliente);
				
				return new Electrodomestico(id,tipo,problema,cliente);
			}
			
		}
		return null;
	}

	@Override
	public void createElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO electrodomestico(tipo, problema, id_cliente) VALUES (?,?,?)");

			) {
			ps.setString(1, electrodomestico.getTipo());
			ps.setString(2, electrodomestico.getProblema());
			ps.setInt(3, electrodomestico.getCliente_id().getId());
			ps.executeUpdate();
			
		}
		
	}

	@Override
	public void editElectrodomestico(Electrodomestico electrodomestico) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("UPDATE electrodomestico SET tipo = ?, problema = ? WHERE id_electrodomestico = ?");
			) {

				ps.setString(1, electrodomestico.getTipo());
				ps.setString(2, electrodomestico.getProblema());
				ps.setInt(3, electrodomestico.getId());
				ps.executeUpdate();
			} 
		
	}

	@Override
	public void deleteElectrodomestico(int electrodomesticoId) throws SQLException, NamingException {
		
	}

	@Override
	public Electrodomestico findLastCreatedElectrodomestico() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM electrodomestico ORDER BY id_electrodomestico DESC LIMIT 1");
			if(rs.next()) {
				int id 				= rs.getInt("id_electrodomestico");
				String tipo			= rs.getString("tipo");
				String problema		= rs.getString("problema");
				int idCliente 		= rs.getInt("id_cliente");
				Cliente cliente 	= clientesDAO.findClienteById(idCliente);
				return new Electrodomestico(id,tipo,problema,cliente);
			}
			
		}
		return null;
	}


	@Override
	public Electrodomestico findElectrodomesticoByClienteId(int clienteId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM electrodomestico WHERE id_cliente = ?");
			) {
			ps.setInt(1, clienteId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 				= rs.getInt("id_electrodomestico");
				String tipo			= rs.getString("tipo");
				String problema		= rs.getString("problema");
				int idCliente 		= rs.getInt("id_cliente");
				Cliente cliente 	= clientesDAO.findClienteById(idCliente);
				
				return new Electrodomestico(id,tipo,problema,cliente);
			}
			
		}
		return null;	}
	
}
