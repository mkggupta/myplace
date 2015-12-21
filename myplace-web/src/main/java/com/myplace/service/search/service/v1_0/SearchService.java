package com.myplace.service.search.service.v1_0;



import java.util.HashMap;

import com.myplace.dto.BusinessSearchVO;
import com.myplace.service.search.exception.SearchServiceException;

public interface SearchService {
	
	public HashMap<String, Object> getBusinessSearch(BusinessSearchVO searchVO,int pageLimit,int endLimit) throws SearchServiceException;
	
	
	

	
}
