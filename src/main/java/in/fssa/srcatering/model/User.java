package in.fssa.srcatering.model;

public class User extends UserEntity {

	public User(int id, String name, String email, String password,long phoneNumber, boolean status) {

		super.setId(id);
		super.setName(name);
		super.setEmail(email);
		super.setPhoneNumber(phoneNumber);
		super.setPassword(password);
		super.setStatus(status);
	}

	public User() {

	}

	@Override
	public int compareTo(UserEntity o) {
		return Integer.compare(this.getId(), o.getId());
	}

}
