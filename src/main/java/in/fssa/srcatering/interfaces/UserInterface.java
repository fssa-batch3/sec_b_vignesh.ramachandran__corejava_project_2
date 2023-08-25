package in.fssa.srcatering.interfaces;

import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.model.User;

public interface  UserInterface extends Base<User> {

	public abstract User findByEmail(String email) throws DAOException;

	public abstract int count();
}
