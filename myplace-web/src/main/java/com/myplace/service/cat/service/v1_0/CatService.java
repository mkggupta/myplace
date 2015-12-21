package com.myplace.service.cat.service.v1_0;

import java.util.List;

import com.myplace.dto.CategoryDTO;
import com.myplace.dto.SubCategoryDTO;
import com.myplace.service.cat.exception.CatServiceException;

public interface CatService {
	
	
	public List<CategoryDTO> getCategoryList(String countryCode) throws CatServiceException;
	
	public  List<SubCategoryDTO> getSubCategoryList(Integer catId) throws CatServiceException;
	

	
}
