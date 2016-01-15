package com.myplace.service.notification.service.v1_0;

import java.util.List;

import com.myplace.dto.NotificationMessage;
import com.myplace.service.notification.exception.NotificationServiceException;

public interface NotificationService {
	
	public List<NotificationMessage> getNotificationList(long userId ) throws NotificationServiceException;
	
	public List<NotificationMessage> getPublicNotificationList() throws NotificationServiceException;
	
	public void deleteUserNotification(long userId, long notifId) throws NotificationServiceException;
	
	public void deleteAllUserNotification(long userId) throws NotificationServiceException;
}
