package in.fssa.srcatering.model;

public class CategoryDish extends CategoryDishEntity{
	
	public CategoryDish() {
		
	}
	
	public CategoryDish(int id, int menu_id, int category_id, int dish_id) {
		super.setId(id);
		super.setMenu_id(menu_id);
		super.setCategory_id(category_id);
		super.setDish_id(dish_id);
	}
 
}
