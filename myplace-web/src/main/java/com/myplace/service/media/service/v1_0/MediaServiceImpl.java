package com.myplace.service.media.service.v1_0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dao.modules.media.MediaDAO;
import com.myplace.dto.DefaultFileInfo;
import com.myplace.framework.exception.util.ErrorCodesEnum;
import com.myplace.service.media.exception.MediaServiceException;


public class MediaServiceImpl implements MediaService {
	private static Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);
	private MediaDAO mediaDAO ;
	public void setMediaDAO(MediaDAO mediaDAO) {
		this.mediaDAO = mediaDAO;
	}

	@Override
	public void saveDefaultMedia(DefaultFileInfo defaultFileInfo ) throws MediaServiceException{
		try {
		     mediaDAO.saveDefaultFileInfo(defaultFileInfo);
		} catch (DataUpdateFailedException e) {
			logger.error("saveDefaultMedia --"+e.getLocalizedMessage());
			throw new MediaServiceException(ErrorCodesEnum.MEDIA_SERVICE_FAILED_EXCEPTION);
		}
	}


	
}
