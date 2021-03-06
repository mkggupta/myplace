package com.myplace.dao.modules.media;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.constants.MediaConstants;
import com.myplace.dao.constants.UserConstants;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.UserFileInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;



public class MediaDAOImpl extends AbstractDBManager implements MediaDAO {

	Logger logger = LoggerFactory.getLogger(MediaDAOImpl.class);
	@Override
	public void saveDefaultFileInfo(DefaultFileInfo defaultFileInfo) throws DataUpdateFailedException {
		try {
			sqlMapClient_.insert(MediaConstants.INSERT_DEFAULT_FILE_INFO, defaultFileInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing defualt media:  error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DefaultFileInfo> getDefaultFileInfoByType(int type)throws DataAccessFailedException {
		try {
			return (List<DefaultFileInfo>) sqlMapClient_.queryForList(MediaConstants.GET_DEFAULT_FILE_INFO_BY_TYPE, type);
		} catch (SQLException e) {
			logger.error("Exception in getDefaultFileInfoByType defualt media:  error  : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DefaultFileInfo> getDefaultFileInfoByTypeId(int type, int id)throws DataAccessFailedException {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("type", type);
			parameterMap.put("id", id);
			parameterMap.put("status", "ACTIVE");
			return (List<DefaultFileInfo>) sqlMapClient_.queryForList(MediaConstants.GET_DEFAULT_FILE_INFO_BY_ID_TYPE, parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in getDefaultFileInfoByTypeId defualt media:  error  : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public void saveUserFileInfo(UserFileInfo userFileInfo) throws DataUpdateFailedException{
		try {
			logger.debug("saveUserFileInfo=="+userFileInfo);
			 sqlMapClient_.insert(MediaConstants.INSERT_USER_FILE_INFO,userFileInfo);
		
			}catch(SQLException e){
				logger.error("Exception in saveUserFileInfo : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}	
	}
	
	@SuppressWarnings("unchecked")
	public List<UserFileInfo> getUserFileInfoByUserId(long userId) throws DataAccessFailedException{
		try {
			 return (List<UserFileInfo>)sqlMapClient_.queryForList(MediaConstants.GET_USER_FILE_INFO,userId);
			}catch(SQLException e){
				logger.error("Exception in getUserFileInfoByUserId : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}	
	}
	
	public void deleteUserFileInfo(long userId) throws DataUpdateFailedException{
		try {
			sqlMapClient_.delete(MediaConstants.DELETE_USER_FILE_INFO, userId);
		} catch (SQLException e) {
			logger.error("Exception in deleteUserFileInfo error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}


	
}
