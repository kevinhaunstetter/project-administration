package dao;

import java.util.List;

import Exceptions.MyDAOExcepcion;

import java.util.Collection;

import entidades.Tarea;

/**
 * Definimos la interface DAO, desde aca hacemos la implementacion para establecer con lenguaje SQL 
 * como vamos a impactar en la DB.
 *
 */
public interface TareaDAO {

	public void insertTarea(Tarea t) throws MyDAOExcepcion;
	public void deleteTareaById(Tarea t) throws MyDAOExcepcion;
	public void updateTarea(Tarea t) throws MyDAOExcepcion;
	public boolean getTareaById(Tarea t) throws MyDAOExcepcion;
	public List<Tarea>  getAllTareas() throws MyDAOExcepcion;	
}