package implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.MyDAOExcepcion;
import basics.DBManager;
import dao.EmpleadoDAO;
import entidades.Empleado;

public class EmpleadoDAOImpl implements EmpleadoDAO {

	@Override
	public void insertEmpleado(Empleado e) throws MyDAOExcepcion {

		String sql = "INSERT INTO Empleado (sueldoHora, nombreCompleto) VALUES (?, ?)";
		Connection c = DBManager.getInstance().connect();

		try {
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, e.getSueldoHora());
			s.setString(2, e.getNombreCompleto());
			s.execute();
			c.commit();

		} catch (SQLException excep) {
			try {
				excep.printStackTrace();
				c.rollback();
			} catch (SQLException excep1) {
				excep1.printStackTrace();
			}
			throw new MyDAOExcepcion("Hubo un problema en la insercion, por favor revise.");
		} finally {
			try {
				c.close();
			} catch (SQLException excep) {
				excep.printStackTrace();
			}
		}

	}

	@Override
	public void deleteEmpleadoById(Empleado e) throws MyDAOExcepcion {
		String sql = "DELETE FROM EMPLEADO WHERE legajo = ? ";

		Connection c = DBManager.getInstance().connect();

		try {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getLegajo());
			int res = ps.executeUpdate();

			if (res == 0) {
				throw new MyDAOExcepcion("El empleado que desea borrar no existe");
			}

			c.commit();
		} catch (SQLException excep) {
			try {
				c.rollback();
				excep.printStackTrace();
			} catch (SQLException excep1) {
				throw new MyDAOExcepcion("El empleado que desea borrar no existe");
			}
		} finally {
			try {
				c.close();
			} catch (SQLException excep) {
				excep.printStackTrace();
			}
		}
	}

	@Override
	public void updateEmpleado(Empleado e) throws MyDAOExcepcion {

		String sql = "UPDATE empleado SET sueldoHora=  ? , nombreCompleto = ? where legajo= ? ";
		Connection c = DBManager.getInstance().connect();

		try {
			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, e.getSueldoHora());
			ps.setString(2, e.getNombreCompleto());
			ps.setInt(3, e.getLegajo());

			ps.executeUpdate();
			c.commit();

		} catch (SQLException excep) {
			try {
				c.rollback();
				excep.printStackTrace();
			} catch (SQLException excep1) {
				excep.printStackTrace();
				throw new MyDAOExcepcion("Hubo un problema en la actualizacion, por favor revise.");
			}
		} finally {
			try {
				c.close();
			} catch (SQLException excep) {
				excep.printStackTrace();
			}
		}

	}

	@Override
	public List<Empleado> getAllEmpleados() throws MyDAOExcepcion {
		List<Empleado> resultado = new ArrayList<Empleado>();

		String sql = "SELECT * FROM empleado";
		Connection c = DBManager.getInstance().connect();

		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Empleado e = new Empleado();
				e.setLegajo(rs.getInt("legajo"));
				e.setSueldoHora(rs.getInt("sueldoHora"));
				e.setNombreCompleto(rs.getString("nombreCompleto"));
				resultado.add(e);
			}

		} catch (SQLException e) {
			try {
				e.printStackTrace();
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new MyDAOExcepcion("Hubo un problema en la busqueda, por favor revise.");
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return resultado;

	}

	@Override
	public Empleado getEmpleadoById(Empleado emp) throws MyDAOExcepcion {
		String sql = "SELECT * FROM EMPLEADO where legajo = " + emp.getLegajo();
		Connection c = DBManager.getInstance().connect();
		try {
			Empleado empleado= new Empleado();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				
				empleado.setLegajo(rs.getInt("legajo"));
				empleado.setNombreCompleto(rs.getString("nombreCompleto"));
				empleado.setSueldoHora(rs.getInt("sueldoHora"));
				
				return empleado;
			}				
		} catch (SQLException e) {
				throw new MyDAOExcepcion("Hubo un error en la busqueda:" + e.getMessage());
			}finally {
				try {c.close();}
				catch(SQLException e1){}}
		return null;
	}

}