package com.myplace.dao.modules.about;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myplace.dao.constants.AboutConstant;
import com.myplace.dao.exception.DataAccessFailedException;
import com.myplace.dao.modules.base.AbstractDBManager;
import com.myplace.framework.exception.util.ErrorCodesEnum;

public class AboutDAOImpl extends AbstractDBManager implements AboutDAO {
	private static Logger logger = LoggerFactory.getLogger(AboutDAOImpl.class);
	public String getAboutUs() throws DataAccessFailedException{
		try {
			return (String) sqlMapClient_.queryForObject(AboutConstant.GET_ABOUT_US);
			}catch(SQLException e){
				logger.error("Exception in getAboutUs : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
}
