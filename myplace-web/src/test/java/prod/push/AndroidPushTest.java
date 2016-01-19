package prod.push;

import java.util.HashMap;
import java.util.Map;


import com.myplace.dto.PushMessage;
import com.myplace.service.push.AndroidPushMessageServiceImpl;

public class AndroidPushTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("deviceKey","ewouVcKk594:APA91bG8PNZqCFtPoo6uDqh6w2lHgctHITNmA3vznQbExuSvSGoFMV-4_Tyn1izzDk-98SphjCQKJYg2CW6CJZ93-zzxbcuJjJZT0ehc0UCyJ4MiBkHsby3nGU_mqofeK_JUQGFieLNE");
		PushMessage pm = new PushMessage();
		   pm.setType(1);
		   pm.setDescription("test here mkg");
		   pm.setClkurl("http://gmail.com");
		params.put("pushMessage",pm);
		com.myplace.service.push.PushMessageService pushMessageService = new AndroidPushMessageServiceImpl();
		System.out.println(pushMessageService.pushNotification(params));
		
		System.out.println("we have sent push");
	}

}
