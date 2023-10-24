package in.fssa.srcatering.model;

import java.util.Objects;

public class Category extends CategoryEntity {

	public Category() {

	}

	public Category(int id, String name, String image, int menu_id) {
		super.setId(id);
		super.setCategoryName(name);
		super.setImage(image);
		super.setMenuId(menu_id);
	}

    @Override
    public int compareTo(CategoryEntity o) {
        return Integer.compare(getId(), o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getId() == category.getId() && Objects.equals(getCategoryName(), category.getCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoryName());
    }
}
