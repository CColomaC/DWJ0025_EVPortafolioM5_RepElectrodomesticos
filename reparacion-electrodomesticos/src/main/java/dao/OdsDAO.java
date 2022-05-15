package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.OrdenDeServicio;

public interface OdsDAO {

	public List<OrdenDeServicio> findAllOrdenesDeServicio() 	throws SQLException, NamingException;
	public OrdenDeServicio findOrdenDeServicioById(int odsId) throws SQLException, NamingException;
	public void createOrdenDeServicio(OrdenDeServicio ods)	throws SQLException, NamingException;
	public void editOrdenDeServicio(OrdenDeServicio ods)		throws SQLException, NamingException;
	public void deleteOrdenDeServicio(int odsId)				throws SQLException, NamingException;
	public OrdenDeServicio findLastCreatedOrdenDeServicio()	throws SQLException, NamingException;
}
