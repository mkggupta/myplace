package com.myplace.service.search.service.v1_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.common.constant.MyPlaceBusinessConstant;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.MyPlaceProperties;
import com.myplace.common.util.MyPlacePropertyKeys;
import com.myplace.common.util.MyPlaceUtil;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.modules.business.BusinessDAO;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dao.modules.search.SearchDAO;
import com.myplace.dto.BusinessFileInfo;
import com.myplace.dto.BusinessSearchDTO;
import com.myplace.dto.BusinessSearchVO;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.search.exception.SearchServiceException;


public class SearchServiceImpl implements SearchService {
	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	private SearchDAO searchDAO ;
	private BusinessDAO businessDAO ;
	private MediaDAO mediaDAO;

	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	@Autowired
	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	@Autowired
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	@Override
	public HashMap<String, Object> getBusinessSearch(BusinessSearchVO searchVO,int startLimit,int endLimit) throws SearchServiceException{
		 HashMap<String, Object>  resultMap = new HashMap<String, Object>();
		 List<BusinessSearchDTO> bussList = new ArrayList<BusinessSearchDTO>();
		try {
			logger.debug("getBusinessSearch--"+searchVO.getUserId()+"---"+searchVO.getType()+"--"+searchVO.getZipCode()+"--"+searchVO.getLatitude()+"--"+searchVO.getLongitude());
			if(MyPlaceBusinessConstant.CAT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					//bussList= searchDAO.getBusinessListCatIdNearMe(searchVO.getCatId(), searchVO.getLatitude(), searchVO.getLongitude(), startLimit, endLimit+1);
					bussList= searchDAO.getBusinessListCatIdNearMe(searchVO, startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
						bussList =searchDAO.getBusinessListByCatId(searchVO.getCatId(), startLimit, endLimit+1);
					}
				}else{
					bussList =searchDAO.getBusinessListByCatId(searchVO.getCatId(), startLimit, endLimit+1);
				}
				
			}else if(MyPlaceBusinessConstant.SUB_CAT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList = searchDAO.getBusinessListBySubCatId(searchVO, "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
						bussList = searchDAO.getBusinessListBySubCatId(searchVO.getCatId(), "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
					}
				}else{
					bussList = searchDAO.getBusinessListBySubCatId(searchVO.getCatId(), "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
				}
			
				
			}else if(MyPlaceBusinessConstant.ZIP_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				logger.debug("getBusinessSearch--"+startLimit+"---"+searchVO.getType()+"--"+searchVO.getZipCode()+"--"+endLimit);
				bussList = searchDAO.getBusinessListByZip(searchVO.getZipCode(), startLimit, endLimit+1);
			}else if(MyPlaceBusinessConstant.NEAR_ME_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList = searchDAO.getBusinessListNearMe(searchVO, startLimit, endLimit+1);	
				}
				
			}else if(MyPlaceBusinessConstant.TEXT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList =searchDAO.getBusinessListByText(searchVO,"%"+searchVO.getText()+ "%", startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
					 bussList =searchDAO.getBusinessListByText(searchVO,"%"+searchVO.getText()+ "%", startLimit, endLimit+1);
					}
				}else{
					bussList =searchDAO.getBusinessListByText("%"+searchVO.getText()+ "%", startLimit, endLimit+1);
				}
			} 
			logger.debug("getBusinessSearch- searchVO.getUserId()="+searchVO.getUserId());
			
			for(BusinessSearchDTO businessSearchDTO:bussList){
				if(null!= searchVO.getUserId() && searchVO.getUserId()>0){
					logger.debug("getBusinessSearch->>> searchVO.getUserId()="+ businessSearchDTO.getUserId());
					if(null!=businessSearchDTO.getUserId() && businessSearchDTO.getUserId().equals(searchVO.getUserId())){
						MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
						String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
						businessSearchDTO.setUpdateUrl(baseUrl+"business/pvt/updatebuss");
						businessSearchDTO.setButtonName("Update Business Info");
					}
				}
				
					List<String> bussImgUrlsList = new ArrayList<String>();
					List<BusinessFileInfo>  BusinessFileInfoList = businessDAO.getBusinessFileInfo(businessSearchDTO.getBussId());
					if(null!=BusinessFileInfoList && BusinessFileInfoList.size()>0){
						for(BusinessFileInfo  businessFileInfo : BusinessFileInfoList){
							bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));		
						}
					}else{
						List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByTypeId(MyPlaceConstant.CAT_TYPE,Integer.parseInt(businessSearchDTO.getCatId().toString()));
						for(DefaultFileInfo  defaultFileInfo : defaultFileInfoList){
							bussImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));		
						}
					}
					if(null!= bussImgUrlsList && bussImgUrlsList.size()>0){
						businessSearchDTO.setImgUrls(bussImgUrlsList);
					}
					
					//business/business/pub/buss/{bussId} get business public detail url
					businessSearchDTO.setDetailUrl(MyPlaceUtil.getBusinessDetailUrl(businessSearchDTO.getBussId()));
				}

		} catch (DataAccessFailedException e) {
			throw new SearchServiceException(ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION);
		}
		logger.debug("getBusinessSearch--"+bussList);
		if(null!=bussList && bussList.size()>0){
			logger.debug("before getBusinessSearch--"+bussList.size());
			if(bussList.size()>endLimit){
				bussList.remove(endLimit);
				MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
				 String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
				resultMap.put(MyPlaceBusinessConstant.NEXTURL, baseUrl+"search/pub/getBuss/more?sLimit="+(startLimit+endLimit));
			}
			logger.debug("after getBusinessSearch--"+bussList.size());
			resultMap.put("searchResult", bussList);
			//resultMap.put(MyPlaceBusinessConstant.FEEDTYPE, MyPlaceBusinessConstant.NORMALTYPE);

		}
		return resultMap;
	}
	
	
}
