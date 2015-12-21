package com.myplace.dao.modules.search;



import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dto.BusinessSearchDTO;
import com.myplace.dto.BusinessSearchVO;

public interface SearchDAO {
	
	public List<BusinessSearchDTO> getBusinessListByCatId(Integer catId,int startLimit,int endLimit ) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListCatIdNearMe(Integer catId,Float latitude,Float longitude,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListBySubCatId(Integer catId,String subCatId,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListNearMe(BusinessSearchVO businessSearchVO,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListByZip(String zipCode,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListByText(String text,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListCatIdNearMe(BusinessSearchVO businessSearchVO,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListBySubCatId(BusinessSearchVO businessSearchVO,String subCatId,int startLimit,int endLimit) throws DataAccessFailedException;
	public List<BusinessSearchDTO> getBusinessListByText(BusinessSearchVO businessSearchVO,String text,int startLimit,int endLimit) throws DataAccessFailedException;
}
