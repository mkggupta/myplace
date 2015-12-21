package com.myplace.service.about.service.v1_0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.modules.about.AboutDAO;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.about.exception.AboutServiceException;


public class AboutServiceImpl implements AboutService {
	private static Logger logger = LoggerFactory.getLogger(AboutServiceImpl.class);
	private AboutDAO aboutDAO ;

	public void setAboutDAO(AboutDAO aboutDAO) {
		this.aboutDAO = aboutDAO;
	}

	@Override
	public String getAboutUs() throws AboutServiceException{
		
		try {
		  return aboutDAO.getAboutUs();
		} catch (DataAccessFailedException e) {
			throw new AboutServiceException(ErrorCodesEnum.ABOUT_SERVICE_FAILED_EXCEPTION);
		}
	}


	
}
