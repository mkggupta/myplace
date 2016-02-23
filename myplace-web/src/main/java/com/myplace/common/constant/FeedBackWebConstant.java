package com.myplace.common.constant;

public interface FeedBackWebConstant {
	String USERID = "userId";
	String FEED_BACK_ID = "feedBackId";
	String FEED_BACK_TEXT = "feedBackText";
	String NAME = "name";
	String PHONENO = "contactNum";
	String EMAIL = "email";
	String STATUS = "status";
	String REPLY_TEXT = "replyText";
	String REPLYING_USER_ID = "replyingUserId";
	String FEED_BACK_REPLY_ID = "replyId";
	Byte FEEDBACK_OPEN_STATUS=1;
	Byte FEEDBACK_REVIEW_STATUS=2;
	Byte FEEDBACK_CLOSED_STATUS=3;
	Byte FEEDBACK_CAN_DONE_STATUS=4;
	Byte FEEDBACK_CANT_DONE_STATUS=5;
	Byte FEEDBACK_DELETE_STATUS=6;
	Byte REPLY_FEEDBACK_OPEN_STATUS=1;
	Byte REPLY_FEEDBACK_REVIEW_STATUS=2;
	Byte REPLY_FEEDBACK_CLOSED_STATUS=3;
	Byte REPLY_FEEDBACK_CAN_DONE_STATUS=4;
	Byte REPLY_FEEDBACK_CANT_DONE_STATUS=5;
	Byte REPLY_FEEDBACK_DELETE_STATUS=6;
}
