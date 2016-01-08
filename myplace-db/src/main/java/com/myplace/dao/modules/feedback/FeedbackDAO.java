package com.myplace.dao.modules.feedback;

import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.FeedBackInfo;
import com.myplace.dto.FeedBackReplyInfo;

public interface FeedbackDAO {
	public void saveFeedbackInfo(FeedBackInfo feedBackInfo) throws DataUpdateFailedException;
	public void saveFeedbackReplyInfo(FeedBackReplyInfo feedBackReplyInfo) throws DataUpdateFailedException;
	public List<FeedBackInfo> getFeedbackInfoList (int startLimit, int endLimit) throws DataAccessFailedException;
	public void changeFeedBackStatus(FeedBackInfo feedBackInfo) throws DataUpdateFailedException;
	public void changeFeedBackReplyInfoStatus(FeedBackReplyInfo feedBackReplyInfo) throws DataUpdateFailedException;
}
