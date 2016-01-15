package com.myplace.dao.modules.category;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.constants.CategoryConstant;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.dto.CategoryDTO;
import com.myplace.dto.SubCategoryDTO;
import com.myplace.framework.exception.util.ErrorCodesEnum;






public class CategoryDAOImpl extends AbstractDBManager implements CategoryDAO {
	private static Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<CategoryDTO> getCategoryList(String countryCode) throws DataAccessFailedException{
		try {
			logger.debug("getCategoryList=="+ countryCode);
			 return (List<CategoryDTO>) sqlMapClient_.queryForList(CategoryConstant.GET_CATEGORY_LIST,countryCode);
			}catch(SQLException e){
				logger.error("Exception in getCategoryList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoryDTO> getSubCategoryList(Integer catId) throws DataAccessFailedException {
		try {
		return (List<SubCategoryDTO>) sqlMapClient_.queryForList(CategoryConstant.GET_SUB_CATEGORY_LIST,catId);
	} catch (SQLException e) {
		logger.error("Exception in getMyAdvtList : " + e.getMessage());
		throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
	}
	}
	public String getCategoryNameByCatId(Long catId)throws DataAccessFailedException{
		try {
			return (String) sqlMapClient_.queryForObject(CategoryConstant.GET_CATEGORY_NAME,catId);
		} catch (SQLException e) {
			logger.error("Exception in getCategoryNameByCatId : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	public String getSubCategoryName(Long catId,Long subCatId)throws DataAccessFailedException{
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("catId", catId);
			params.put("subCatId", subCatId);
			return (String) sqlMapClient_.queryForObject(CategoryConstant.GET_SUB_CATEGORY_NAME,catId);
		} catch (SQLException e) {
			logger.error("Exception in getSubCategoryName : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}

	
}
