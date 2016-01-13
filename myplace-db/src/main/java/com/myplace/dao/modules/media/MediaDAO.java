package com.myplace.dao.modules.media;

import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.dto.UserFileInfo;






public interface MediaDAO {
	
	
	public void saveDefaultFileInfo(DefaultFileInfo defaultFileInfo) throws DataUpdateFailedException;
	
	public List<DefaultFileInfo> getDefaultFileInfoByType(int type) throws DataAccessFailedException;
	
	public List<DefaultFileInfo> getDefaultFileInfoByTypeId(int type,int id) throws DataAccessFailedException;
	
	public void saveUserFileInfo(UserFileInfo userFileInfo) throws DataUpdateFailedException;
	
	public List<UserFileInfo> getUserFileInfoByUserId(long userId) throws DataAccessFailedException;
	
	public void deleteUserFileInfo(long userId) throws DataUpdateFailedException;

}
