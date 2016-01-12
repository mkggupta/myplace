package com.myplace.dao.constants;

public interface UserConstants {

	String INSERT_USER_AUTH = "insert_user_auth";
	String INSERT_USER_THIRD_PARTY_AUTH_DETAILS = "insert_user_third_party_auth";
	String INSERT_USER_INFO = "insert_user_info";
	String GET_USERID_BY_USERKEY_ID = "get_userid_by_userkey_id";
	String GET_USERID_BY_USERNAME_PASSWORD = "get_userid_by_username_password";
	String GET_USERID_BY_USERNAME_FOR_APP = "get_userid_by_username_for_app";
	String GET_USER_DETAIL_BY_ID = "get_user_info_by_id";
	String GET_USER_NAME_BY_ID = "get_user_name_by_id";
	String QUERY_UPDATE_USER_INFO = "update_user_info";
	String GET_USER_EXIST_BY_ID = "get_user_exist_by_id";
	String INSERT_USER_PUSH_INFO = "insert_user_push_info";
	String UPDATE_USER_PUSH_INFO = "update_user_push_info";
	String QUERY_UPDATE_USER_PUSH_MESSAGE_STATUS = "update_user_push_message_status";
	String GET_USER_PUSH_INFO_LIST= "get_user_push_ino_list_by_userid";
	int USER_PUSH_ACTIVE_STATUS=1;
	String INSERT_USER_FILE_INFO= "insert_user_file_info";
	String GET_USER_FILE_INFO= "get_user_file_info_by_userid";
	String DELETE_USER_FILE_INFO= "delete_user_file_info";
	
}
