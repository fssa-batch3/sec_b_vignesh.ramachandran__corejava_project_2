package in.fssa.srcatering.service;

import java.util.List;

import in.fssa.srcatering.dao.UserDAO;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.User;
import in.fssa.srcatering.validator.UserValidator;

public class UserService {
	
	UserDAO userdao = new UserDAO();
	
	public List<User> getAll() {
		
		System.out.println(userdao.findAll());
		return userdao.findAll();
		
		
	}
	
	public void create(User newUser) throws ValidationException,RuntimeException {
		
		UserValidator.isEmailAlreadyExists(newUser.getEmail());
		UserValidator.validate(newUser);
		userdao.create(newUser);
	}
	
	public void update(int id, User newUser) throws ValidationException,RuntimeException {
		
		UserValidator.isIdValid(id);
		UserValidator.validate(newUser);
		userdao.update(id, newUser);
	}
	
	public void delete(int id)throws ValidationException,RuntimeException {
		if(id < 0) {
			throw new RuntimeException("Invalid id");
		}
		UserValidator.isIdValid(id);
		userdao.delete(id);
	}
	
	public User findById(int id) {
		return userdao.findById(id);
	}
	
	public User findByEmail(String email) {
		return userdao.findByEmail(email);
	}

}
