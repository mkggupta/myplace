package com.myplace.service.business.service.v1_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.myplace.common.business.util.BusinessUtil;
import com.myplace.common.constant.MyPlaceBusinessConstant;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.constant.PushConstant;
import com.myplace.common.user.util.NotificationUtils;
import com.myplace.common.util.MyPlaceProperties;
import com.myplace.common.util.MyPlacePropertyKeys;
import com.myplace.common.util.MyPlaceUtil;
import com.myplace.common.util.StorageUtil;
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.business.BusinessDAO;
import com.myplace.dao.modules.category.CategoryDAO;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dao.modules.notification.NotificationDAO;
import com.myplace.dao.modules.search.SearchDAO;
import com.myplace.dao.modules.user.UserDAO;
import com.myplace.dto.BusinessFileInfo;
import com.myplace.dto.BusinessInfo;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.NotificationMessage;
import com.myplace.dto.PushMessage;
import com.myplace.dto.UserSearchDTO;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.business.exception.BusinessServiceException;
import com.myplace.service.push.PushMessageService;



public class BusinessServiceImpl implements BusinessService {
	private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
	private BusinessDAO businessDAO ;
	private SearchDAO searchDAO ;
	private UserDAO  userDAO;
	private PushMessageService pushMessageService;
	private CategoryDAO categoryDAO ;
	private MediaDAO mediaDAO;
	
	private NotificationDAO notificationDAO;
	
	@Autowired
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	@Autowired
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
	
	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Autowired
	public void setPushMessageService(PushMessageService pushMessageService) {
		this.pushMessageService = pushMessageService;
	}
	@Autowired
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public Long createBusiness(BusinessInfo businessInfo) throws BusinessServiceException{
		
		logger.debug("BusinessServiceImpl----"+businessInfo.getBussName());
		Long businessId = null;
		try {
			businessId = businessDAO.saveBusinessInfo(businessInfo);
			logger.debug("businessId----"+businessId);
			try {
				userDAO.updateBussinessStats(businessInfo.getUserId(),true);
			} catch (Exception e1) {
				logger.error("Could not update stats for user "+businessInfo.getUserId()+" and businessId----"+businessId);	
			}
			if(null==businessId || businessId ==0){
				throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
			}
			
			 List<BusinessFileInfo> fileInfoList = businessInfo.getBusinessFileInfo();
			logger.debug("fileInfo----"+fileInfoList);
			if(null!=fileInfoList && fileInfoList.size()>0){
				for(BusinessFileInfo businessFileInfo :fileInfoList){
					businessFileInfo.setBussId(businessId);
					businessDAO.saveBusinessFileInfo(businessFileInfo);
				}
			}
			//This code start for Push Notification 
			if(null!=businessId && businessId>0){
				try {
					List<UserSearchDTO> userSearchDTOList = searchDAO.getUserListNearMe(businessInfo.getBussLat(),businessInfo.getBussLong(),MyPlaceBusinessConstant.DEFAULT_PUSH_DISTANCE);
					logger.debug("userSearchDTOList----"+userSearchDTOList +"for getBussLat "+businessInfo.getBussLat()+" getBussLong="+businessInfo.getBussLong()+" dist="+MyPlaceBusinessConstant.DEFAULT_PUSH_DISTANCE);
					if(null!=userSearchDTOList && userSearchDTOList.size()>0)
					{	List<Long> userIdList = new ArrayList<Long>();
						for(UserSearchDTO userSearchDTO:userSearchDTOList){
							userIdList.add(userSearchDTO.getId());
						}
						logger.debug("userIdList----"+userIdList);
						if(null!=userIdList && userIdList.size()>0){
							List<UserPushInfo>  userPushInfoList = userDAO.getUserPushInfoList(userIdList);
							
							String serviceName= categoryDAO.getCategoryNameByCatId(businessInfo.getCatId());
							boolean pushStatus=false;
							PushMessage pushMessage = new PushMessage();
							pushMessage.setType(PushConstant.BUSINESS_CREATION_TYPE);
							pushMessage.setTitle(PushConstant.BUSINESS_CREATION_TITLE);
							pushMessage.setId(businessId);
							pushMessage.setDescription("Good News ! Now "+businessInfo.getBussName()+" specialized in "+serviceName+" is near you.");
							pushMessage.setClkurl(MyPlaceUtil.getServerBaseUrl()+"business/pub/buss/"+businessId);
							fileInfoList = businessInfo.getBusinessFileInfo();
							if(null!=fileInfoList && fileInfoList.size()>0){
									pushMessage.setImgurl(StorageUtil.getImageUrl(fileInfoList.get(0)));		
							}
							
							//insert user notification info
							NotificationMessage notificationMessage = NotificationUtils.transformPushMessageToNotificationMesage(pushMessage) ;
							notificationDAO.saveNotificationInfo(userIdList, notificationMessage);
							logger.debug("userPushInfoList----"+userPushInfoList);
							if(null!=userPushInfoList && userPushInfoList.size()>0){
								
								for(UserPushInfo userPushInfo:userPushInfoList){
									Map<String, Object> params= new HashMap<String, Object>();
									params.put(MyPlaceConstant.DEVICE_KEY,userPushInfo.getPushKey());
									params.put(MyPlaceConstant.PUSH_MESSAGE,pushMessage);
									pushStatus=pushMessageService.pushNotification(params);
									logger.debug("pushStatus----"+pushStatus +" for userid="+userPushInfo.getUserId());
								}
							}
						}
					}
				} catch (DataAccessFailedException e) {
					//we will not re-throw exception as business creation should not stop because of push
					logger.error("could not send push notification---"+e.getLocalizedMessage());
				}
			}
			//Code end for Push Notification 
		} catch (DataUpdateFailedException e) {
			logger.error("createBusiness---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessId;
	}
	
	public List<BusinessInfo> getMyBusinessList (Long userId) throws BusinessServiceException{
		
	try {
		List<BusinessInfo> businessInfoList = businessDAO.getMyBusinessList(userId);
		List<Long> businessIdList = new ArrayList<Long>();
		for(BusinessInfo businessInfo : businessInfoList){
			businessIdList.add(businessInfo.getBussId());
		}
		if(null!=businessIdList && businessIdList.size()>0){
			
			List<String> bussImgUrlsList = new ArrayList<String>();
			for(BusinessInfo businessInfo : businessInfoList){
				bussImgUrlsList = new ArrayList<String>();
				List<BusinessFileInfo>  BusinessFileInfoList = businessDAO.getBusinessFileInfo(businessInfo.getBussId());
				if(null!=BusinessFileInfoList && BusinessFileInfoList.size()>0){
					for(BusinessFileInfo  businessFileInfo : BusinessFileInfoList){
						bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));		
					}
				}else{
					List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByTypeId(MyPlaceConstant.CAT_TYPE,Integer.parseInt(businessInfo.getCatId().toString()));
					if(null!=defaultFileInfoList){
						for(DefaultFileInfo  defaultFileInfo : defaultFileInfoList){
							bussImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));		
						}
					}
				}
				if(null!= bussImgUrlsList && bussImgUrlsList.size()>0){
					businessInfo.setImgUrls(bussImgUrlsList);
				}
				//business/pvt/my/{userId}/{bussId} get business detail url
				businessInfo.setDetailUrl(MyPlaceUtil.getServerBaseUrl()+"business/pvt/my/"+userId+"/"+businessInfo.getBussId());
				if (businessInfo.getCatId()>0){
					String catName= categoryDAO.getCategoryNameByCatId(businessInfo.getCatId());
					businessInfo.setCatName(catName);
				}

			}
		}
			return businessInfoList;
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessList---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId, int appType) throws BusinessServiceException{

		try {
				BusinessInfo  businessInfo = businessDAO.getMyBusinessDetail(userId,businessId);
				if (null!=businessInfo){
					if (appType>3){
						businessInfo.setUpdateUrl(MyPlaceUtil.getEditBussProfileUIUrl());
						businessInfo.setDeleteUrl(MyPlaceUtil.getDeleteBusinessApiUrl());
					}else{
						// business update url and delete url
						businessInfo.setUpdateUrl(MyPlaceUtil.getUpdateBusinessApiUrl());
						businessInfo.setDeleteUrl(MyPlaceUtil.getDeleteBusinessApiUrl());
					}
					if (businessInfo.getCatId()>0){
						String catName= categoryDAO.getCategoryNameByCatId(businessInfo.getCatId());
						businessInfo.setCatName(catName);
					}
					List<BusinessFileInfo>  BusinessFileInfoList = businessDAO.getBusinessFileInfo(businessId);
					List<String> bussImgUrlsList = new ArrayList<String>();
					if(null!=BusinessFileInfoList && BusinessFileInfoList.size()>0){
						for(BusinessFileInfo  businessFileInfo : BusinessFileInfoList){
								bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));	
						}
					}else{
						List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByTypeId(MyPlaceConstant.CAT_TYPE,Integer.parseInt(businessInfo.getCatId().toString()));
						if(null!=defaultFileInfoList){
						  for(DefaultFileInfo  defaultFileInfo : defaultFileInfoList){
							bussImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));		
						  }
						}
					}
					if(null!= bussImgUrlsList && bussImgUrlsList.size()>0){
							businessInfo.setImgUrls(bussImgUrlsList);
					}
				}
			return businessInfo;
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getBusinessDetail (Long businessId) throws BusinessServiceException{

	try {
			BusinessInfo  businessInfo = businessDAO.getBusinessDetail(businessId);
			businessDAO.updateBusinessView(businessId);
			logger.debug("getBusinessDetail---businessId="+businessId);
			if (businessInfo.getCatId()>0){
				String catName= categoryDAO.getCategoryNameByCatId(businessInfo.getCatId());
				businessInfo.setCatName(catName);
			}
			List<BusinessFileInfo>  BusinessFileInfoList = businessDAO.getBusinessFileInfo(businessId);
			List<String> bussImgUrlsList = new ArrayList<String>();
			if(null!=BusinessFileInfoList && BusinessFileInfoList.size()>0){
				for(BusinessFileInfo  businessFileInfo : BusinessFileInfoList){
						bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));	
				}
			}else{
				List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByTypeId(MyPlaceConstant.CAT_TYPE,Integer.parseInt(businessInfo.getCatId().toString()));
				if(null!=defaultFileInfoList){
					for(DefaultFileInfo  defaultFileInfo : defaultFileInfoList){
						bussImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));		
					}
				}
			}
			if(null!= bussImgUrlsList && bussImgUrlsList.size()>0){
					businessInfo.setImgUrls(bussImgUrlsList);
			}
			return businessInfo;
		} catch (DataAccessFailedException ex) {
			logger.error("getBusinessDetail---"+ex.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}catch(DataUpdateFailedException e ){
			logger.error("getBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}

	@Override
	public void changeBussStatus(long userId, long businessId, Byte status)throws BusinessServiceException {
		int isupdated =0 ;
		try {
			isupdated = businessDAO.updateBussStatus(userId, businessId, status);
		} catch (DataUpdateFailedException e) {
			logger.error("changeBussStatus---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		logger.debug(isupdated+" changeBussStatus---"+status);
		if(status == MyPlaceConstant.BUSS_DELETED_STATUS && isupdated>0){
			try {
				userDAO.updateBussinessStats(userId,false);
			} catch (Exception e1) {
				logger.error("Could not update stats in changeBussStatus() for user "+userId+" and businessId----"+businessId);	
			}
		}
	}
	
	public BusinessInfo updateBusinessInfo (BusinessInfo businessInfo,int appType) throws BusinessServiceException{
		
		BusinessInfo businessInfoObj = null ;
		try {
			businessInfoObj = businessDAO.getBusinessDetail(businessInfo.getBussId());
			BusinessUtil.updateBusinessInfo(businessInfoObj, businessInfo);
		} catch (DataAccessFailedException e) {
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION);
		}
		
		try {
				businessDAO.updateBusinessDetail(businessInfoObj);
				logger.debug("update appType----"+appType);
				if (appType>3){
					// business update url and delete url for web
					businessInfoObj.setUpdateUrl(MyPlaceUtil.getEditBussProfileUIUrl());
					businessInfoObj.setDeleteUrl(MyPlaceUtil.getDeleteBusinessApiUrl());
				}else{
					// business update url and delete url
					businessInfoObj.setUpdateUrl(MyPlaceUtil.getUpdateBusinessApiUrl());
					businessInfoObj.setDeleteUrl(MyPlaceUtil.getDeleteBusinessApiUrl());
				}
				//business file update
			    List<BusinessFileInfo> fileInfoList = businessInfoObj.getBusinessFileInfo();
				logger.debug("updateBusinessInfofileInfo----"+fileInfoList);
				List<String> bussImgUrlsList = new ArrayList<String>();
				if(null!=fileInfoList && fileInfoList.size()>0){
					  businessDAO.deleteBusinessFileInfo(businessInfo.getBussId());
					for(BusinessFileInfo businessFileInfo :fileInfoList){
						businessFileInfo.setBussId(businessInfoObj.getBussId());
						businessDAO.saveBusinessFileInfo(businessFileInfo);
						bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));	
					}	
				}else{
					fileInfoList = businessDAO.getBusinessFileInfo(businessInfo.getBussId());
					logger.debug("updateBusinessInfo fileInfoList----"+fileInfoList);
					if(null!=fileInfoList && fileInfoList.size()>0){
						for(BusinessFileInfo  businessFileInfo : fileInfoList){
								bussImgUrlsList.add(StorageUtil.getImageUrl(businessFileInfo));	
						}
					}else{
						List<DefaultFileInfo> defaultFileInfoList = mediaDAO.getDefaultFileInfoByTypeId(MyPlaceConstant.CAT_TYPE,Integer.parseInt(businessInfo.getCatId().toString()));
						logger.debug("updateBusinessInfo defaultFileInfoList----"+defaultFileInfoList);
						if(null!=defaultFileInfoList){
							for(DefaultFileInfo  defaultFileInfo : defaultFileInfoList){
								bussImgUrlsList.add(StorageUtil.getDefaultImageUrl(defaultFileInfo));		
							}
						}
					}
				}
				logger.debug("updateBusinessInfo bussImgUrlsList----"+bussImgUrlsList);
				if(null!= bussImgUrlsList && bussImgUrlsList.size()>0){
					businessInfoObj.setImgUrls(bussImgUrlsList);
					businessInfoObj.setBusinessFileInfo(null);
				}
				
			//	businessInfoObj.setUpdateUrl(MyPlaceUtil.getServerBaseUrl()+"business/pvt/updatebuss");
				//businessInfoObj.setDeleteUrl(MyPlaceUtil.getServerBaseUrl()+"business/pvt/delbuss");
				
		} catch (DataAccessFailedException|DataUpdateFailedException e) {
			logger.error("Exception in updating user details in database for the businessInfoObj : " + businessInfoObj + " error  : " + e.getMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessInfoObj;
	}
	
	//this is only for dummy
	public Long sendPush(long businessId) throws BusinessServiceException{
		
		logger.debug("BusinessServiceImpl----"+businessId);
		
		try {
			
			
			//This code start for Push Notification 
			if(businessId>0){
				try {
					BusinessInfo businessInfo = businessDAO.getBusinessDetail(businessId);
					List<UserSearchDTO> userSearchDTOList = searchDAO.getUserListNearMe(businessInfo.getBussLat(),businessInfo.getBussLong(),MyPlaceBusinessConstant.DEFAULT_PUSH_DISTANCE);
					logger.debug("userSearchDTOList----"+userSearchDTOList);
					if(null!=userSearchDTOList && userSearchDTOList.size()>0)
					{	List<Long> userIdList = new ArrayList<Long>();
						for(UserSearchDTO userSearchDTO:userSearchDTOList){
							userIdList.add(userSearchDTO.getId());
						}
						logger.debug("userIdList----"+userIdList);
						if(null!=userIdList && userIdList.size()>0){
							List<UserPushInfo>  userPushInfoList = userDAO.getUserPushInfoList(userIdList);
							logger.debug("userPushInfoList----"+userPushInfoList);
							String serviceName= categoryDAO.getCategoryNameByCatId(businessInfo.getCatId());
							boolean pushStatus=false;
							PushMessage pushMessage = new PushMessage();
							pushMessage.setType(PushConstant.BUSINESS_CREATION_TYPE);
							pushMessage.setTitle(PushConstant.BUSINESS_CREATION_TITLE);
							pushMessage.setId(businessId);
							pushMessage.setDescription("Good News ! Now "+businessInfo.getBussName()+" specialized in "+serviceName+" is near you.");
							MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
							String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
							pushMessage.setClkurl(baseUrl+"business/pub/buss/"+businessId);
							
							List<BusinessFileInfo>  fileInfoList = businessDAO.getBusinessFileInfo(businessId);
							logger.debug("fileInfoList----"+fileInfoList);
							if(null!=fileInfoList && fileInfoList.size()>0){
									pushMessage.setImgurl(StorageUtil.getImageUrl(fileInfoList.get(0)));		
							}
							
							//insert user notification info
							//NotificationMessage notificationMessage = NotificationUtils.transformPushMessageToNotificationMesage(pushMessage) ;
							//notificationDAO.saveNotificationInfo(userIdList, notificationMessage);
							logger.debug("userPushInfoList----"+userPushInfoList);
							if(null!=userPushInfoList && userPushInfoList.size()>0){
								
								for(UserPushInfo userPushInfo:userPushInfoList){
									Map<String, Object> params= new HashMap<String, Object>();
									params.put(MyPlaceConstant.DEVICE_KEY,userPushInfo.getPushKey());
									params.put(MyPlaceConstant.PUSH_MESSAGE,pushMessage);
									pushStatus=pushMessageService.pushNotification(params);
									logger.debug("pushStatus----"+pushStatus +" for userid="+userPushInfo.getUserId());
								}
							}
						}
					}
				} catch (DataAccessFailedException e) {
					//we will not re-throw exception as business creation should not stop because of push
					logger.error("could not send push notification---"+e.getLocalizedMessage());
				}
			}
			//Code end for Push Notification 
		} catch (Exception e) {
			logger.error("send push---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessId;
	}

	
	
}
