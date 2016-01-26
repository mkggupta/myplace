package com.myplace.dao.modules.user;

import java.util.List;

import com.myplace.dao.entities.UserPushInfo;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.ForgetPasswordVerification;
import com.myplace.dto.UserAuth;
import com.myplace.dto.UserEmailVerification;
import com.myplace.dto.UserInfo;
import com.myplace.dto.UserStats;
import com.myplace.dto.UserThirdPartyAuth;






public interface UserDAO {
	
	public void saveUserInfo(UserInfo userInfo) throws DataUpdateFailedException;
	public Long saveUserAuth(UserAuth userAuth) throws DataUpdateFailedException;
	public void saveUserThirdPartyAuth(UserThirdPartyAuth userThirdPartyAuth) throws DataUpdateFailedException;
	public Long getUserExists(String userKey,int thirdPartyId) throws DataAccessFailedException;
	public UserInfo getUserInfoByUserId(long userId) throws DataAccessFailedException ;
	public String getUserNameById(long userId) throws DataAccessFailedException ;
	public void updateUser(UserInfo userInfo) throws DataUpdateFailedException ;
	public Long getUserIdByNamePassword(String userName,String password) throws DataAccessFailedException;
	public Long getUserIdByNameForApp(String userName) throws DataAccessFailedException;
	public void saveUpdateUserPushInfo(UserPushInfo userPushInfo) throws DataUpdateFailedException;
	public boolean isUserExists(long userId) throws DataAccessFailedException;
	public void updateUserPushStatus(long userId, String pushStatus) throws DataUpdateFailedException;
	public List<UserPushInfo> getUserPushInfoList(List<Long> userIdList) throws DataAccessFailedException;
	public boolean changePassword(long userId, String newPassword,String oldPassword)throws DataUpdateFailedException;
	public long saveUserEmailVerification(UserEmailVerification userEmailVerification) throws DataUpdateFailedException;
	public long saveUserForgetPasswordVerification(ForgetPasswordVerification forgetPasswordVerification) throws DataUpdateFailedException;
	public boolean updateUserEmailVerification(UserEmailVerification userEmailVerification) throws DataUpdateFailedException;
	public boolean updateUserForgetPasswordVerification(ForgetPasswordVerification forgetPasswordVerification) throws DataUpdateFailedException;
	public UserAuth getUserAuthDetails(String userName) throws DataAccessFailedException;
	public boolean resetPassword(long userId,String userName, String newPassword)throws DataUpdateFailedException;
	public boolean updateUserStatus(String userEmail, int userStatus)throws DataUpdateFailedException;
	public void updateUserLocation(long userId ,float latitude, float longitude, String location)throws DataUpdateFailedException;
	public UserAuth getUserAuthDetailsByUserId(long userId) throws DataAccessFailedException;
	public void saveUserStats(UserStats userStats) throws DataUpdateFailedException;
	public void updateBussinessStats(long userId, boolean isIncrement) throws DataUpdateFailedException;
	public UserStats getUserStats(long userId) throws DataAccessFailedException;
	public byte getUserStatus(long userId) throws DataAccessFailedException;
	public UserAuth getUserAuthDetailsByUserNamePassword(String userName, String password) throws DataAccessFailedException;
	

}
