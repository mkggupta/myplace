package com.myplace.dao.modules.category;



import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dto.CategoryDTO;
import com.myplace.dto.SubCategoryDTO;

public interface CategoryDAO {
	
	public List<CategoryDTO> getCategoryList(String countryCode) throws DataAccessFailedException;
	public List<SubCategoryDTO> getSubCategoryList(Integer catId)throws DataAccessFailedException;
	public String getCategoryNameByCatId(Long catId)throws DataAccessFailedException;
	public String getSubCategoryName(Long catId,Long subCatId)throws DataAccessFailedException;
	
}
