package in.fssa.srcatering.service;

import java.util.Iterator;
import java.util.List;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.util.StringUtil;
import in.fssa.srcatering.validator.UserValidator;

public class UserService {

	UserDAO userdao = new UserDAO();

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<User> getAll() throws ServiceException {

		List<User> userList;
		try {
			userList = userdao.findAll();
			Iterator<User> iterator = userList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

			return userdao.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Getall User");
		}

	}

	/**
	 * 
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void create(User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserValidator.isEmailAlreadyExists(newUser.getEmail());
			userdao.create(newUser);
		} catch (DAOException e) {
			if(e.getMessage().contains("Email already exists")) {
				e.printStackTrace();
				throw new ServiceException("Failed to Create User EmailId already exists");
			}else {
				e.printStackTrace();
				throw new ServiceException("Failed to Create User");
			}
		}

	}

	/**
	 * 
	 * @param id
	 * @param newUser
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void update(int id, User newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserValidator.isUserIdIsValid(id);

			userdao.update(id, newUser);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Update User");
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public void delete(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isUserIdIsValid(id);

			userdao.delete(id);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to Delete User");
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findById(int id) throws ValidationException, ServiceException {

		try {
			UserValidator.isUserIdIsValid(id);

			return userdao.findById(id);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
	}

	/**
	 * 
	 * @param email
	 * @return
	 * @throws ValidationException
	 * @throws ServiceException
	 */
	public User findByEmail(String email) throws ValidationException, ServiceException {

		try {

			StringUtil.rejectIfInvalidString(email, "Email");

			return userdao.findByEmail(email);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}

	}

}
