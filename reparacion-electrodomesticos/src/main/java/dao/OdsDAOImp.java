package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Electrodomestico;
import modelo.OrdenDeServicio;

public class OdsDAOImp implements OdsDAO {	
	
private ElectrodomesticosDAO electrodomesticosDAO;
	
	public OdsDAOImp(ElectrodomesticosDAO electrodomesticosDAO) {
		this.electrodomesticosDAO = electrodomesticosDAO;
	}
	
	@Override
	public List<OrdenDeServicio> findAllOrdenesDeServicio() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			
			String query = "SELECT * FROM ordendeservicio";				
			ResultSet rs = st.executeQuery(query);
			List<OrdenDeServicio> ordenesDeServicio = new ArrayList<>();
			while(rs.next()) {
				int id 					= rs.getInt("id_ods");
				String estado 				= rs.getString("estado");
				LocalDate fechaSolicitud 		= rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaActualizacionOrden 	= rs.getObject("fechaactualizacionorden", LocalDate.class);
				int id_electrodomestico 		= rs.getInt("id_electrodomestico");
				
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(id_electrodomestico);

				OrdenDeServicio ordenDeServicio	= new OrdenDeServicio(id,estado,fechaSolicitud,fechaActualizacionOrden,electrodomestico);
				ordenesDeServicio.add(ordenDeServicio);
			}
			return ordenesDeServicio;
		}
	}
	
	@Override
	public OrdenDeServicio findOrdenDeServicioById(int odsId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM ordendeservicio WHERE id_ods = ?");
			) {
			ps.setInt(1, odsId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 					= rs.getInt("id_ods");
				String estado 				= rs.getString("estado");
				LocalDate fechaSolicitud 		= rs.getObject("fechasolicitud", LocalDate.class);
				LocalDate fechaActualizacionOrden 	= rs.getObject("fechaactualizacionorden", LocalDate.class);
				int id_electrodomestico 		= rs.getInt("id_electrodomestico");
				
				Electrodomestico electrodomestico = electrodomesticosDAO.findElectrodomesticoById(id_electrodomestico);

				return new OrdenDeServicio(id,estado,fechaSolicitud,fechaActualizacionOrden,electrodomestico);
			}
			
		}
		return null;
	}

	@Override
	public void createOrdenDeServicio(OrdenDeServicio ods) throws SQLException, NamingException {
		try(
			Connection conn = DBUtils.getConexion();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ordendeservicio(estado, fechasolicitud, fechaactualizacionorden, id_electrodomestico) VALUES (?,?,?,?)");

			) {
			ps.setString(1, ods.getEstado());
			ps.setObject(2, ods.getFechaSolicitud());
			ps.setObject(3, ods.getFechaActualizacionOrden());
			ps.setInt(4, ods.getElectrodomestico_id().getId());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void editOrdenDeServicio(OrdenDeServicio ods) throws SQLException, NamingException {
		try(
			Connection conn = DBUtils.getConexion();
			PreparedStatement ps = conn.prepareStatement("UPDATE ordendeservicio SET fechaactualizacionorden = ?, estado = ? WHERE id_ods = ?");
			) {

			ps.setObject(1, LocalDate.now());
			ps.setString(2, ods.getEstado());
			ps.setInt(3, ods.getId());
			ps.executeUpdate();
		} 
	}

	@Override
	public void deleteOrdenDeServicio(int odsId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ordendeservicio WHERE id_ods = ?");
			) {
				ps.setInt(1, odsId);
				ps.executeUpdate();
			} 
	}


	@Override
	public OrdenDeServicio findLastCreatedOrdenDeServicio() throws SQLException, NamingException {
		
		return null;
		
	}

}
