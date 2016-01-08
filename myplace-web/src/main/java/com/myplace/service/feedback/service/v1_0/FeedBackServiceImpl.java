package com.myplace.service.feedback.service.v1_0;

import java.util.List;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.feedback.FeedbackDAO;
import com.myplace.dto.FeedBackInfo;
import com.myplace.dto.FeedBackReplyInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.feedback.exception.FeedBackServiceException;

public class FeedBackServiceImpl implements FeedBackService {
	private FeedbackDAO feedbackDAO ;

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}

	public List<FeedBackInfo> getFeedBackList(int startLimit,int endLimit) throws FeedBackServiceException{
		try {
			return feedbackDAO.getFeedbackInfoList(startLimit, endLimit);
		} catch (DataAccessFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}	
	}
	
	public void saveFeedBackInfo(FeedBackInfo feedBackInfo)throws FeedBackServiceException{
		try {
			feedbackDAO.saveFeedbackInfo(feedBackInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public void saveFeedBackReplyInfo(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException{
		try {
			feedbackDAO.saveFeedbackReplyInfo(feedBackReplyInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}

	public void changeFeedBackStatus(FeedBackInfo feedBackInfo)throws FeedBackServiceException{
		try {
			feedbackDAO.changeFeedBackStatus(feedBackInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public void changeFeedBackReplyInfoStatus(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException{
		try {
			feedbackDAO.changeFeedBackReplyInfoStatus(feedBackReplyInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}

	
}
