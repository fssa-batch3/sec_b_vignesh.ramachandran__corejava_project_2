package in.fssa.srcatering.model;

public abstract class UserEntity implements Comparable<UserEntity> {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private long phone_number;
	private boolean status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", phone_number=" + phone_number + ", status=" + status + "]";
	}
	
	@Override
	public int compareTo(UserEntity o) {
		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() < (o.getId())) {
				return -1;
			} else {
				return 1;
			}
		}
	}
	

}
