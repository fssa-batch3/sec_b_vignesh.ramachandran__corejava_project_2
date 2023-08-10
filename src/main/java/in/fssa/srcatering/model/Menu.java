package in.fssa.srcatering.model;

public class Menu extends MenuEntity {
	
	public Menu() {

	}

	
	public Menu(int id, String menu_name,String description) {
		super.setId(id);
		super.setMenu_name(menu_name);
		super.setDescription(description);
	}

	@Override
	public int compareTo(MenuEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
