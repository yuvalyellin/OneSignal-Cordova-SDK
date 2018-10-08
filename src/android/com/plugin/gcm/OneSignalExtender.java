package com.plugin.gcm;

import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import android.util.Log;
import com.onesignal.OSNotificationReceivedResult;
import com.onesignal.OSNotificationDisplayedResult;
import java.math.BigInteger;

import org.json.JSONObject;
import org.json.JSONException;

import android.support.v4.app.NotificationCompat;

public class OneSignalExtender extends NotificationExtenderService {
   @Override
   protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
   Log.d("OneSignal", "onNotificationProcessing");
        JSONObject data = receivedResult.payload.additionalData;
        boolean silent = false;

        try {
            if (data.has("content_available")) {
                silent = data.getBoolean("content_available");
                if (silent) {
                    receivedResult.payload.title = "";
                    receivedResult.payload.body = "";
                    receivedResult.payload.lockScreenVisibility = Integer.parseInt("-1");
                    OverrideSettings overrideSettings = new OverrideSettings();
                    overrideSettings.androidNotificationId = -1;
                    overrideSettings.extender = new NotificationCompat.Extender() {
                        @Override
                        public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                            builder = builder.setVisibility(Integer.parseInt("-1"));
                            builder = builder.setVibrate(new long[]{0L});
                            return builder;
                        }
                    };
                    OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
                }
            }
        }
        catch (JSONException e) {
            Log.e("OneSignal", "onNotificationProcessingFailure: " + e.getMessage());
        }
        return silent;
    }
}