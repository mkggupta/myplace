package com.myplace.dao.modules.user;

import java.util.List;

import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.UserAuth;
import com.myplace.dto.UserInfo;
import com.myplace.dto.UserThirdPartyAuth;






public interface UserDAO {
	
	public void saveUserInfo(UserInfo userInfo) throws DataUpdateFailedException;
	public Long saveUserAuth(UserAuth userAuth) throws DataUpdateFailedException;
	public void saveUserThirdPartyAuth(UserThirdPartyAuth userThirdPartyAuth) throws DataUpdateFailedException;
	public Long getUserExists(String userKey,int thirdPartyId) throws DataAccessFailedException;
	public UserInfo getUserProfile(long userId) throws DataAccessFailedException ;
	public String getUserNameById(long userId) throws DataAccessFailedException ;
	public void updateUser(UserInfo userInfo) throws DataUpdateFailedException ;
	public Long getUserIdByNamePassword(String userName,String password) throws DataAccessFailedException;
	public Long getUserIdByNameForApp(String userName) throws DataAccessFailedException;
	public void saveUpdateUserPushInfo(UserPushInfo userPushInfo) throws DataUpdateFailedException;
	public boolean isUserExists(long userId) throws DataAccessFailedException;
	public void updateUserPushStatus(long userId, String pushStatus) throws DataUpdateFailedException;
	public List<UserPushInfo> getUserPushInfoList(List<Long> userIdList) throws DataAccessFailedException;
	

}
