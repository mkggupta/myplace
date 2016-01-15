package com.myplace.dao.modules.notification;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.constants.NotificationConstant;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.dto.NotificationMessage;
import com.myplace.framework.exception.util.ErrorCodesEnum;



public class NotificationDAOImpl extends AbstractDBManager implements NotificationDAO {

	Logger logger = LoggerFactory.getLogger(NotificationDAOImpl.class);


	@Override
	public void saveNotificationInfo(List<Long> userIdList,NotificationMessage notificationMessage) throws DataUpdateFailedException {
		for(Long userId :userIdList){
			try {
				notificationMessage.setUserId(userId);
				sqlMapClient_.insert(NotificationConstant.INSERT_NOTIFICATION_INFO, notificationMessage);
			} catch (SQLException e) {
				logger.error("Exception in saveNotificationInfo a:  error  : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationMessage> getNotificationInfoByType(int type) throws DataAccessFailedException {
		try {
			 return (List<NotificationMessage>)sqlMapClient_.queryForList(NotificationConstant.GET_NOTIFICATION_INFO_BY_TYPE,type);
			}catch(SQLException e){
				logger.error("Exception in getNotificationInfoByType : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationMessage> getNotificationInfoByTypeUserId(int type, long userId)throws DataAccessFailedException {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("type", type);
			parameterMap.put("userId", userId);
			parameterMap.put("status", "1");
			return (List<NotificationMessage>) sqlMapClient_.queryForList(NotificationConstant.GET_NOTIFICATION_INFO_BY_USERID_TYPE, parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in getNotificationInfoByTypeUserId a:  error  : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationMessage> getNotificationInfoByUserId(long userId)	throws DataAccessFailedException {
		try {
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("type", NotificationConstant.PUBLIC_NOTIFICATION_TYPE);
				parameterMap.put("userId", userId);
			 return (List<NotificationMessage>)sqlMapClient_.queryForList(NotificationConstant.GET_NOTIFICATION_INFO_BY_USERID,parameterMap);
			}catch(SQLException e){
				logger.error("Exception in getNotificationInfoByUserId : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}	
	}
	@Override
	public void updateUserNotificationInfo(long userId,long notifId, byte status) throws DataUpdateFailedException {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("status", status);
			parameterMap.put("userId", userId);
			parameterMap.put("notifId", notifId);
			sqlMapClient_.update(NotificationConstant.UPDATE_NOTIFICATION_INFO_STATUS, parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in updateUserNotificationInfo error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void updateUserNotificationInfoByUserId(long userId, byte status) throws DataUpdateFailedException {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("status", status);
			parameterMap.put("userId", userId);
			
			sqlMapClient_.update(NotificationConstant.UPDATE_NOTIFICATION_INFO_STATUS_BY_USERID, parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in updateUserNotificationInfoByUserId error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}


	
}
