import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import android.util.Log;
public class OneSignalExtender extends NotificationExtenderService {
   @Override
   protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
     	// Read properties from result.
     	Log.i("console received stuff "+receivedResult.toString());
      // Return true to stop the notification from displaying.
      return false;
   }
}