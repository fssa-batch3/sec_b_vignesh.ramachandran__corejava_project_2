package in.fssa.srcatering.model;

import java.util.Objects;

public class Menu extends MenuEntity {

	public Menu() {

	}

	
	public Menu(int id, String menuName,String description, String image, boolean status) {
		super.setId(id);
		super.setMenuName(menuName);
		super.setDescription(description);
		super.setImage(image);
		super.setStatus(status);
	}

	@Override
	public int compareTo(MenuEntity o) {
		return Integer.compare(this.getId(), o.getId());
	}
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Menu other = (Menu) obj;
        return getId() == other.getId() &&
               Objects.equals(getMenuName(), other.getMenuName()) &&
               Objects.equals(getDescription(), other.getDescription()) &&
               Objects.equals(getImage(), other.getImage());
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMenuName(), getDescription(), getImage());
    }

}
