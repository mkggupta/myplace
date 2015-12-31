package com.myplace.service.push;

import java.util.Map;

public interface PushMessageService {
	public boolean pushNotification(Map<String, Object> params);
}
