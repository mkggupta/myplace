package com.myplace.common.user.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dto.NotificationMessage;
import com.myplace.dto.PushMessage;

public class NotificationUtils {

	static Logger logger = LoggerFactory.getLogger(NotificationUtils.class);
	
	
	public static NotificationMessage transformPushMessageToNotificationMesage(PushMessage pm) {
		NotificationMessage nm = new NotificationMessage();
		if (null != pm) {
			try {
				BeanUtils.copyProperties(nm, pm);
			} catch (IllegalAccessException e) {
				logger.error("Error in copying PushMessage " + pm + " to userInfo", e);
			} catch (InvocationTargetException e) {
				logger.error("Error in copying PushMessage " + pm + " to userInfo", e);
			}
		}
		return nm;
	}
}
