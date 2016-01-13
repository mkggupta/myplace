package com.myplace.service.push;



import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.io.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.myplace.common.constant.MyPlaceConstant;
import com.myplace.common.util.MyPlaceProperties;
import com.myplace.common.util.MyPlacePropertyKeys;
import com.myplace.dto.PushMessage;

public class AndroidPushMessageServiceImpl implements PushMessageService {
	private static Logger logger = LoggerFactory
			.getLogger(AndroidPushMessageServiceImpl.class);
	private static int waitTimeOut = 15000;

	
	private static Set<String> errorStates;
	private static int retryCount = 1;
	private static String pushurl = "https://android.googleapis.com/gcm/send";
	private static String pushkey = "";
	private final static Pattern ERROR_PATTERN = Pattern.compile("Error=(.*)",
			Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	MyPlaceProperties myplaceProperties = MyPlaceProperties.getInstance();
	String baseUrl = myplaceProperties.getProperty(MyPlacePropertyKeys.BASE_URL);
	public AndroidPushMessageServiceImpl() {
		pushurl = myplaceProperties.getProperty(MyPlacePropertyKeys.ANDROID_PUSHURL);
		pushkey = myplaceProperties.getProperty(MyPlacePropertyKeys.ANDROID_AUTHORIZATION_KEY);
		waitTimeOut = Integer.parseInt(myplaceProperties.getProperty(MyPlacePropertyKeys.ANDROID_WAIT_TIME));
		
	}

	@Override
	public boolean pushNotification(Map<String, Object> params) {
		try {
			String deviceKey = (String) params
					.get(MyPlaceConstant.DEVICE_KEY);
			PushMessage pushMessage = (PushMessage) params
					.get(MyPlaceConstant.PUSH_MESSAGE);
			
			logger.debug("pushMessage called for Android pushMessage::"+pushMessage.toString()+" for deviceKey ::"+deviceKey);
			
			if(StringUtils.isNotBlank(deviceKey)&& null!=pushMessage){
				boolean pushed = pushMessage(deviceKey, pushMessage);
				return pushed;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error(
					"Exception while AndroidpushNotification "
							+ e.getLocalizedMessage(), e);
		}
		return false;
	}

	protected boolean pushMessage(String deviceKey, PushMessage message) {
		logger.info("pushMessage called for Android.");
		
		boolean pushed = false;
		Gson gson = new Gson();  
		int count = 0;
		while (count < retryCount) {

			logger.info("Inside while count is " + count + " retryCount " + retryCount);
			URL url;
			HttpsURLConnection connection = null;
			DataOutputStream wr = null;
			try {
				String content = "registration_id="
						+ URLEncoder.encode(deviceKey, "UTF-8") + "&collapse_key="
						+ URLEncoder.encode("0", "UTF-8") + "&data.message="
						+ gson.toJson(message);
						//+ URLEncoder.encode(message, "UTF-8");
				url = new URL(pushurl);
				connection = (HttpsURLConnection) url.openConnection();
				connection.setHostnameVerifier(new HostnameVerifier() {

					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				connection.setRequestMethod("POST");
				connection.setReadTimeout(waitTimeOut);
				connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
				connection.setRequestProperty("Authorization",
						"key=" + pushkey);

				connection.setDoInput(true);
				connection.setDoOutput(true);

				wr = new DataOutputStream(connection.getOutputStream());

				wr.writeBytes(content);
				wr.flush();
				wr.close();

				String response = new String(Streams.readAll(connection
						.getInputStream()));

				logger.info("Response " + response);
				int responseCode = connection.getResponseCode();
				String responseMessage = connection.getResponseMessage();
				logger.info("Status is " + responseCode);
				if (responseCode == 200) {
					count++;
					if ("OK".equalsIgnoreCase(responseMessage)) {
						pushed = true;
						count = retryCount;
						Matcher matcher = ERROR_PATTERN.matcher(response);

						if (matcher.find()) {
							response = matcher.group(1);
							matcher.end();
							if (null!=errorStates && errorStates.contains(response.trim())) {
								logger.error("push failed due to " + response);

							}
							break;
						} else {
							logger.info("Successfully pushed");
						}
					} else {
						count++;
						logger.error(" Due to some problme could not push "
								+ responseCode);
					}
				}
			} catch (SSLHandshakeException e) {
				logger.error("push failed", e);
				count++;
			} catch (IOException e) {

				count++;
				
				logger.error("push failed  IOException ", e);
			} catch (Exception e) {
				logger.error("push failed", e);
				count++;

			} finally {
				try {
					if (wr != null) {
						wr.close();
					}
					if (connection != null) {
						connection.disconnect();
					}
				} catch (IOException e) {

					logger.error("Push failed", e);
				}
			}
		}
		logger.info("pushMessage finished for Android.");
		return pushed;
	}


}
