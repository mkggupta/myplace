package com.myplace.service.media.service.v1_0;

import com.myplace.dto.DefaultFileInfo;
import com.myplace.service.media.exception.MediaServiceException;

public interface MediaService {
	
	public void saveDefaultMedia(DefaultFileInfo defaultFileInfo ) throws MediaServiceException;
	
}
