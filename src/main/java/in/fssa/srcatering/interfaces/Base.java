package in.fssa.srcatering.interfaces;

import java.util.List;

import in.fssa.srcatering.exception.DAOException;

public interface Base<T> {

	public abstract List<T> findAll() throws DAOException;

	public abstract void create(T t) throws DAOException;

	public abstract void update(int id, T t) throws DAOException;

	public abstract void delete(int id) throws DAOException;

	public abstract T findById(int id) throws DAOException;

}
