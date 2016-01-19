package com.myplace.dao.modules.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.myplace.dao.constants.UserConstants;
import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.dto.ForgetPasswordVerification;
import com.myplace.dto.UserAuth;
import com.myplace.dto.UserEmailVerification;
import com.myplace.dto.UserInfo;
import com.myplace.dto.UserThirdPartyAuth;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;




public class UserDAOImpl extends AbstractDBManager implements UserDAO {

	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Override
	public void saveUserInfo(UserInfo userInfo) throws DataUpdateFailedException {
		try {
			sqlMapClient_.insert(UserConstants.INSERT_USER_INFO, userInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing user details in database for the user : " + userInfo + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}

	@Override
	public Long saveUserAuth(UserAuth userAuth)	throws DataUpdateFailedException {
		try {
			return (Long) sqlMapClient_.insert(UserConstants.INSERT_USER_AUTH, userAuth);
		} catch (NestedSQLException e) {
			Throwable t = e;
			while (t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof MySQLIntegrityConstraintViolationException) {
				logger.error("User already present in database username : " + userAuth.getUserName() + " : registration mode : "
						+ userAuth.getRegistrationMode() + " " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_UNIQUE_CONSTRAINT_VOILATION_EXCEPTION, e);
			} else {
				logger.error("Exception in storing user authentication details in database for the user : " + userAuth + " error  : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}

		} catch (SQLException e) {
			logger.error("Exception in storing user authentication details in database for the user : " + userAuth + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}

	@Override
	public void saveUserThirdPartyAuth(UserThirdPartyAuth userThirdPartyAuth)throws DataUpdateFailedException {
		try {
			sqlMapClient_.insert(UserConstants.INSERT_USER_THIRD_PARTY_AUTH_DETAILS, userThirdPartyAuth);
		} catch (NestedSQLException e) {
			Throwable t = e;
			while (t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof MySQLIntegrityConstraintViolationException) {
				logger.error("User already present in database username : " + userThirdPartyAuth.getId() + " : registration mode : "
						+ userThirdPartyAuth.getThirdPartyId() + " " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_UNIQUE_CONSTRAINT_VOILATION_EXCEPTION, e);
			} else {
				logger.error("Exception in storing user authentication details in database for the user : " + userThirdPartyAuth.getId() + " error  : "
						+ e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}

		} catch (SQLException e) {
			logger.error("Exception in storing user authentication details in database for the user : " + userThirdPartyAuth.getId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public Long getUserExists(String userKey,int thirdPartyId) throws DataAccessFailedException{
		Long userId=0l;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userKey", userKey);
			params.put("thirdPartyId", thirdPartyId);
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERKEY_ID, params);
		} catch (Exception e) {
			logger.error("Exception in getUserExists : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return userId;
	}
	
	public Long getUserIdByNamePassword(String userName,String password) throws DataAccessFailedException{
		Long userId=0l;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("password", password);
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERNAME_PASSWORD, params);
		} catch (Exception e) {
			logger.error("Exception in getUserExists : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return userId;
		
	}
	
	public Long getUserIdByNameForApp(String userName) throws DataAccessFailedException{
		Long userId=0l;
		try {
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERNAME_FOR_APP, userName);
		} catch (Exception e) {
			logger.error("Exception in getUserIdByName : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return userId;
		
	}
	
	public UserInfo getUserProfile(long userId) throws DataAccessFailedException {
		try {
			return (UserInfo) sqlMapClient_.queryForObject(UserConstants.GET_USER_DETAIL_BY_ID, userId);
		} catch (SQLException e) {
			logger.error("Exception in getUserProfile : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	public String getUserNameById(long userId) throws DataAccessFailedException {
		
		try {
			return  (String) sqlMapClient_.queryForObject(UserConstants.GET_USER_NAME_BY_ID, userId);
		} catch (SQLException e) {
			logger.error("Exception in getUserNameById : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public void updateUser(UserInfo userInfo) throws DataUpdateFailedException {
		try {
			sqlMapClient_.update(UserConstants.QUERY_UPDATE_USER_INFO, userInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing user details in database for the user : " + userInfo + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public void saveUpdateUserPushInfo(UserPushInfo userPushInfo) throws DataUpdateFailedException{
		try {
			sqlMapClient_.insert(UserConstants.INSERT_USER_PUSH_INFO, userPushInfo);
			 logger.debug("regPushDevice id: " + UserConstants.INSERT_USER_PUSH_INFO);
		} catch (SQLException e) {
			try {
				sqlMapClient_.update(UserConstants.UPDATE_USER_PUSH_INFO, userPushInfo);
			} catch (SQLException e1) {
				logger.error("Exception in storing user push info in database for the userpush : " + userPushInfo+" userid="+userPushInfo.getUserId() + " error  : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
			
		}
		
	}
	
	public boolean isUserExists(long userId) throws DataAccessFailedException{
		boolean isUserExist=false;
		try {
			Long id = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USER_EXIST_BY_ID, userId);
			 logger.debug("regPushDevice id: " + id+"---"+userId);
			if(id!=null &&  id>0 ){
				isUserExist =true;
			}
		} catch (Exception e) {
			logger.error("Exception in isUserExists : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return isUserExist;
	}
	
	public void updateUserPushStatus(long userId, String pushStatus) throws DataUpdateFailedException{
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("user_id", userId);
			parameterMap.put("status", pushStatus);
			sqlMapClient_.update(UserConstants.QUERY_UPDATE_USER_PUSH_MESSAGE_STATUS,parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in updating push status : "+e.getLocalizedMessage(),e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<UserPushInfo> getUserPushInfoList(List<Long> userIdList) throws DataAccessFailedException{
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userIdList", userIdList);
			parameterMap.put("status",UserConstants.USER_PUSH_ACTIVE_STATUS);
			return (List<UserPushInfo>) sqlMapClient_.queryForList(UserConstants.GET_USER_PUSH_INFO_LIST,parameterMap);
			}catch(SQLException e){
				logger.error("Exception in getUserPushInfoList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	public boolean changePassword(long userId, String newPassword,String oldPassword)throws DataUpdateFailedException{
			boolean isSucceed =false ;
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userId", userId);
			parameterMap.put("newPassword", newPassword);
			parameterMap.put("oldPassword", oldPassword);
			int updateState = sqlMapClient_.update(UserConstants.CHANGE_USER_PASSWORD,parameterMap);
			logger.debug("updateState-="+updateState);
			if(updateState==1){
				isSucceed=true;
			}
		} catch (SQLException e) {
			logger.error("Exception in changePassword : "+e.getLocalizedMessage(),e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return isSucceed;
	}
	
	public long saveUserEmailVerification(UserEmailVerification userEmailVerification) throws DataUpdateFailedException {
		try {
			return (long) sqlMapClient_.insert(UserConstants.INSERT_EMAIL_VERIFICATION_DETAILS, userEmailVerification);
			
		} catch (SQLException e) {
			logger.error("Exception in storing email verification details in database for the user : " + userEmailVerification.getUserId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public boolean updateUserEmailVerification(UserEmailVerification userEmailVerification) throws DataUpdateFailedException {
		boolean recordUpdate = false;
		try {
			int updateState = sqlMapClient_.update(UserConstants.UPDTE_EMAIL_VERIFICATION_DETAILS, userEmailVerification);
			if(updateState==1){
				recordUpdate=true;
			}	
		} catch (SQLException e) {
			logger.error("Exception in updateUserEmailVerification  in database for the user : " + userEmailVerification.getUserId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return recordUpdate;
	}
	
	
	public long saveUserForgetPasswordVerification(ForgetPasswordVerification forgetPasswordVerification) throws DataUpdateFailedException {
		try {
			return (long) sqlMapClient_.insert(UserConstants.INSERT_FORGET_PASSWORD_DETAILS, forgetPasswordVerification);
			
		} catch (SQLException e) {
			logger.error("Exception in storing email verification details in database for the user : " + forgetPasswordVerification.getUserId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public boolean updateUserForgetPasswordVerification(ForgetPasswordVerification forgetPasswordVerification) throws DataUpdateFailedException {
		boolean recordUpdate = false;
		try {
			int updateState = sqlMapClient_.update(UserConstants.UPDTE_FORGET_PASSWORD_DETAILS, forgetPasswordVerification);
			 if(updateState==1){
					recordUpdate=true;
			}
		} catch (SQLException e) {
			logger.error("Exception in storing email verification details in database for the user : " +forgetPasswordVerification.getUserId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return recordUpdate;
	}
	
	public UserAuth getUserAuthDetails(String userName) throws DataAccessFailedException {
		try {
			return (UserAuth) sqlMapClient_.queryForObject(UserConstants.GET_USER_AUTH_DETAILS, userName);
		} catch (SQLException e) {
			logger.error("Exception in getUserAuthDetails for the user : " + userName + " error  : "
					+ e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public boolean resetPassword(long userId,String userName, String newPassword)throws DataUpdateFailedException{
		boolean recordUpdate = false;
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userId", userId);
			parameterMap.put("userName", userName);
			parameterMap.put("newPassword", newPassword);
			logger.debug("userId-="+userId +"userName="+userName);
			int updateState  = sqlMapClient_.update(UserConstants.RESET_USER_PASSWORD,parameterMap);
			 if(updateState==1){
					recordUpdate=true;
			}
			logger.debug("resetPassword-="+recordUpdate);
		} catch (SQLException e) {
			logger.error("Exception in resetPassword : "+e.getLocalizedMessage(),e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return recordUpdate;
	}
	
	public boolean updateUserStatus(String userEmail, int userStatus)throws DataUpdateFailedException{
		boolean recordUpdate = false;
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userName", userEmail);
			parameterMap.put("status", userStatus);
			int updateState  = sqlMapClient_.update(UserConstants.UPDATE_USER_STATUS,parameterMap);
			 if(updateState==1){
				recordUpdate=true;
			}
			logger.debug("updateUserStatus-=");
		} catch (SQLException e) {
			logger.error("Exception in updateUserStatus : "+e.getLocalizedMessage(),e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return recordUpdate;
	}

	
}
