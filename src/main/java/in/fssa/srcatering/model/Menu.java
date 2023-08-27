package in.fssa.srcatering.model;

public class Menu extends MenuEntity {
	
	public Menu() {

	}

	
	public Menu(int id, String menuName,String description, String image) {
		super.setId(id);
		super.setMenuName(menuName);
		super.setDescription(description);
		super.setImage(image);
	}

	@Override
	public int compareTo(MenuEntity o) {
		return Integer.compare(this.getId(), o.getId());
	}

}
