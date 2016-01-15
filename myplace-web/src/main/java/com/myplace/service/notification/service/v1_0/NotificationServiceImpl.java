package com.myplace.service.notification.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.common.constant.PushConstant;
import com.myplace.common.util.MyPlaceProperties;
import com.myplace.common.util.MyPlacePropertyKeys;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.notification.NotificationDAO;
import com.myplace.dto.NotificationMessage;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.notification.exception.NotificationServiceException;


public class NotificationServiceImpl implements NotificationService {
	private static Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	private NotificationDAO  notificationDAO;
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	
	@Override
	public List<NotificationMessage> getNotificationList(long userId ) throws NotificationServiceException{
		try {
			List<NotificationMessage> notifList =   notificationDAO.getNotificationInfoByUserId(userId);
			if(null!=notifList && notifList.size()>0){
				for(NotificationMessage notificationMessage:notifList){
					if(notificationMessage.getType().equals(PushConstant.PERSONAL_NOTIFICATION_TYPE)){
						MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
						String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
						notificationMessage.setDelUrl(baseUrl+"notif/pvt/delete/"+userId+"/"+notificationMessage.getNotifId());
					}	
				}
			}
			return notifList;
		} catch (DataAccessFailedException e) {
			logger.error("getNotificationList --"+e.getLocalizedMessage());
			throw new NotificationServiceException(ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION);
		}
	}

	@Override
	public List<NotificationMessage> getPublicNotificationList() throws NotificationServiceException{
		try {
			return notificationDAO.getNotificationInfoByType(PushConstant.PUBLIC_NOTIFICATION_TYPE);
		} catch (DataAccessFailedException e) {
			logger.error("getPublicNotificationList --"+e.getLocalizedMessage());
			throw new NotificationServiceException(ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public void deleteUserNotification(long userId, long notifId) throws NotificationServiceException{
		
		try {
			 notificationDAO.updateUserNotificationInfo(userId,notifId,PushConstant.NOTIFICATION_DELETED_STATUS);
		} catch (DataUpdateFailedException e) {
			logger.error("deleteUserNotification --"+e.getLocalizedMessage());
			throw new NotificationServiceException(ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public void deleteAllUserNotification(long userId) throws NotificationServiceException{
		try {
			 notificationDAO.updateUserNotificationInfoByUserId(userId,PushConstant.NOTIFICATION_DELETED_STATUS);
		} catch (DataUpdateFailedException e) {
			logger.error("deleteAllUserNotification --"+e.getLocalizedMessage());
			throw new NotificationServiceException(ErrorCodesEnum.NOTIFICATION_SERVICE_FAILED_EXCEPTION);
		}
	}

	
}
