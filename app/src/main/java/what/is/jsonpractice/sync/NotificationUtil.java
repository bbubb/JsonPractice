package what.is.jsonpractice.sync;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import what.is.jsonpractice.R;

public class NotificationUtil {

    public static final String NOTIFICATION_CHANNEL_ID = "shibe_notification_channel";
    public static final int SHIBE_LOADED_NOTIFICATION_ID = 3333;


    public static void notifyUserLoaded(Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Shibe Loaded Notification", NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Shibe Load Complete")
                .setContentText("Shibe is done loading!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Shibe is done loading!"))
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);

        notificationManager.notify(SHIBE_LOADED_NOTIFICATION_ID, builder.build());


    }


}

