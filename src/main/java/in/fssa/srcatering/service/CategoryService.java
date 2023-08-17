package in.fssa.srcatering.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import in.fssa.srcatering.dao.CategoryDAO;
import in.fssa.srcatering.exception.DAOException;
import in.fssa.srcatering.exception.ServiceException;
import in.fssa.srcatering.exception.ValidationException;
import in.fssa.srcatering.model.Category;
import in.fssa.srcatering.util.IntUtil;
import in.fssa.srcatering.validator.CategoryValidator;

public class CategoryService {

	CategoryDAO categorydao = new CategoryDAO();

	public List<Category> getAll() throws ServiceException {
		
		try {
			
			List<Category> categoryList = categorydao.findAll();

			Iterator<Category> iterator = categoryList.iterator();

			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			return categorydao.findAll();
			
		} catch (DAOException e) {
			throw new ServiceException("Failed to GetAll Category");
		}

		

	}
	
	public Category findById(int category_id) throws ValidationException, ServiceException {
		
		try {
			IntUtil.rejectIfInvalidInt(category_id, "CategoryId");
			this.isCategoryIdIsValid(category_id);
			
			return categorydao.findById(category_id);
			
		} catch (DAOException e) {
			throw new ServiceException("Failed to Get Category");
		}
		
	}
	
	public void isCategoryIdIsValid(int category_id) throws ValidationException {
		
		CategoryValidator.isCategoryIdIsValid(category_id);
	}

}
