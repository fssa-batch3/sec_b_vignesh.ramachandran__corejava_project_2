package in.fssa.srcatering.model;

public class Menu extends MenuEntity {
	
	public Menu() {

	}

	
	public Menu(int id, String menu_name) {
		super.setId(id);
		super.setMenu_name(menu_name);
	}

	@Override
	public int compareTo(MenuEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
