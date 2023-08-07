package in.fssa.srcatering.model;

public class User extends UserEntity {

	public User(int id, String name, String email, String password,long phone_number, boolean status) {

		super.setId(id);
		super.setName(name);
		super.setEmail(email);
		super.setPhone_number(phone_number);
		super.setPassword(password);
		super.setStatus(status);
	}

	public User() {

	}

}
