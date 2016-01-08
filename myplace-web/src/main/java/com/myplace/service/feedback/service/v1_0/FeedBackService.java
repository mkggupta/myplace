package com.myplace.service.feedback.service.v1_0;

import java.util.List;

import com.myplace.dto.FeedBackInfo;
import com.myplace.dto.FeedBackReplyInfo;
import com.myplace.service.feedback.exception.FeedBackServiceException;

public interface FeedBackService {
	
	
	public List<FeedBackInfo> getFeedBackList(int pageLimit,int endLimit) throws FeedBackServiceException;
	
	public void saveFeedBackInfo(FeedBackInfo feedBackInfo)throws FeedBackServiceException;
	
	public void saveFeedBackReplyInfo(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException;

	public void changeFeedBackStatus(FeedBackInfo feedBackInfo)throws FeedBackServiceException;
	
	public void changeFeedBackReplyInfoStatus(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException;
}
