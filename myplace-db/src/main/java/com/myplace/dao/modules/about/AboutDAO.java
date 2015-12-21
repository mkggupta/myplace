package com.myplace.dao.modules.about;

import com.myplace.dao.exception.DataAccessFailedException;

public interface AboutDAO {
	public String getAboutUs() throws DataAccessFailedException;
}
