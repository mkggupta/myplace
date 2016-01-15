package com.myplace.dao.modules.notification;

import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.NotificationMessage;







public interface NotificationDAO {
	
	public void saveNotificationInfo(List<Long> userIdList,NotificationMessage notificationMessage) throws DataUpdateFailedException;
	
	public List<NotificationMessage> getNotificationInfoByType(int type) throws DataAccessFailedException;
	
	public List<NotificationMessage> getNotificationInfoByTypeUserId(int type,long userId) throws DataAccessFailedException;
	
	public List<NotificationMessage> getNotificationInfoByUserId(long userId) throws DataAccessFailedException;
	
	public void updateUserNotificationInfo(long userId,long notifId,byte status) throws DataUpdateFailedException;
	
	public void updateUserNotificationInfoByUserId(long userId, byte status) throws DataUpdateFailedException ;

}
