package in.fssa.srcatering.model;

public class AddressBookEntity {
	
	private int id;
	private int userId;
	private String name;
	private String email;
	private String phoneNumber;
	
	private String doorNo;
	private String streetName;
	private String subLocality;
	private String city;
	private String district;
	private String state;
	private int pincode;
	
	private int status;
	private int setAsDefault;
	private int selected;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getSubLocality() {
		return subLocality;
	}
	public void setSubLocality(String subLocality) {
		this.subLocality = subLocality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int isStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int isSetAsDefault() {
		return setAsDefault;
	}
	public void setSetAsdefault(int setAsdefault) {
		this.setAsDefault = setAsdefault;
	}
	public int isSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
	
	

}
