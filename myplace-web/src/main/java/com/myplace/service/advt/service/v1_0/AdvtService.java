package com.myplace.service.advt.service.v1_0;

import java.util.HashMap;
import java.util.List;

import com.myplace.dto.AdvertisementDTO;
import com.myplace.dto.AdvtTemplate;
import com.myplace.service.advt.exception.AdvtServiceException;

public interface AdvtService {
	public Long createAdvt(AdvertisementDTO advertisementDTO) throws AdvtServiceException;
	
	public List<AdvtTemplate> getAdvtTemplate(Byte type) throws AdvtServiceException;
	
	public HashMap<String, Object> getAdvtList(Long userId,int startLimit,int endLimit, boolean order) throws AdvtServiceException;
	
	public void changeAdvtStatus(long userId,long advtCode,Byte status) throws AdvtServiceException ;
	
	public HashMap<String, Object> getAdvtDetails(Long userId,Long advtCode) throws AdvtServiceException;
	
	public HashMap<String, Object> getAdvtStatsList(Long userId,Long advtCode) throws AdvtServiceException;
	
}
