package com.myplace.dao.modules.advt;



import java.util.List;
import java.util.Map;

import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.exception.DataUpdateFailedException;
import com.myplace.dto.AdvertisementInfo;
import com.myplace.dto.AdvtStats;
import com.myplace.dto.AdvtTemplate;
import com.myplace.dto.FileInfo;
import com.myplace.dto.PaymentInfo;

public interface AdvtDAO {
	public Long saveAdvtInfo(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException;
	public void saveAdvtDetail(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException;
	public void saveAdvtFileInfo(FileInfo fileInfo) throws DataUpdateFailedException;
	public void savePaymentInfo(PaymentInfo paymentInfo) throws DataUpdateFailedException;
	public List<AdvtTemplate> getAdvtTemplate(Byte type) throws DataAccessFailedException;
	public List<AdvertisementInfo> getMyAdvtList(Long userId,int startLimit,int endLimit)throws DataAccessFailedException;
	public List<Long> getAdvtListByUserId(Long userId)throws DataAccessFailedException;
	public List<AdvtStats> getAdvtStatsList(Map<String, Object> parameterMap)throws DataAccessFailedException;
	public int updateAdvtStatus(Long userId,Long advtCode,byte status) throws DataUpdateFailedException;
}
