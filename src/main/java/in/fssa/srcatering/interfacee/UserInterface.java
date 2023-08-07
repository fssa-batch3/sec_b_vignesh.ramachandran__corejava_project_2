package in.fssa.srcatering.interfacee;

import in.fssa.srcatering.model.User;

public interface  UserInterface extends Base<User> {

	public abstract User findByEmail(String email);

	public abstract int count();
}
