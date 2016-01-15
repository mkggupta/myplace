package com.myplace.service.cat.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.modules.category.CategoryDAO;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dto.CategoryDTO;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.SubCategoryDTO;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.cat.exception.CatServiceException;


public class CatServiceImpl implements CatService {
	private static Logger logger = LoggerFactory.getLogger(CatServiceImpl.class);
	private CategoryDAO catDAO ;
	private MediaDAO mediaDAO;

	@Autowired
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}
	public void setCatDAO(CategoryDAO catDAO) {
		this.catDAO = catDAO;
	}
	@Override
	public List<CategoryDTO> getCategoryList(String countryCode) throws CatServiceException{
		
		try {
			List<CategoryDTO> categoryDTOList ;
			logger.debug("getCategoryList from "+countryCode);
			 categoryDTOList = catDAO.getCategoryList(countryCode);
			 if(null!=categoryDTOList && categoryDTOList.size()>0){
				 List<DefaultFileInfo> fileList = mediaDAO.getDefaultFileInfoByType(MyPlaceConstant.CAT_TYPE);
					logger.debug("getCategoryList fileList= "+fileList);
					 if(null!=fileList && fileList.size()>0){
						 for(CategoryDTO categoryDTO:categoryDTOList){
							 for(DefaultFileInfo defaultFileInfo:fileList){
								 if(categoryDTO.getCatId()==defaultFileInfo.getId()){
									 categoryDTO.setImgUrl(StorageUtil.getDefaultImageUrl(defaultFileInfo));	 
								 } 
							}
						 }
					}
			 }
		  return categoryDTOList;
		} catch (DataAccessFailedException e) {
			logger.error("getCategoryList fileList error= "+e.getLocalizedMessage());
			throw new CatServiceException(ErrorCodesEnum.CATEGORY_SERVICE_FAILED_EXCEPTION);
		}
	}
	@Override
	public  List<SubCategoryDTO> getSubCategoryList(Integer catId) throws CatServiceException{
		
		try {
			List<SubCategoryDTO> subCatDTOList ;
			subCatDTOList =catDAO.getSubCategoryList(catId);
			if(null!=subCatDTOList && subCatDTOList.size()>0){
				List<DefaultFileInfo> fileList = mediaDAO.getDefaultFileInfoByType(MyPlaceConstant.SUBCAT_TYPE);
				logger.debug("getSubCategoryList fileList "+fileList);
				 if(null!=fileList){
					 for(SubCategoryDTO subCategoryDTO:subCatDTOList){
						 for(DefaultFileInfo defaultFileInfo:fileList){
							 if(subCategoryDTO.getSubCatId()==defaultFileInfo.getId()){
								 subCategoryDTO.setImgUrl(StorageUtil.getDefaultImageUrl(defaultFileInfo));	 
							 } 
						}
					 }
				 }
			}
		  return subCatDTOList;
		} catch (DataAccessFailedException e) {
			throw new CatServiceException(ErrorCodesEnum.SUB_CATEGORY_SERVICE_FAILED_EXCEPTION);
		}
	}
	
}
