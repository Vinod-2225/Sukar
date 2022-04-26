package com.designmaster.sukar.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

import org.json.JSONObject
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService() {
//    private val CHANNEL_ID = "DressCodeChannelId"
//    private val CHANNEL_NAME = "DressCodeChannelName"

    private val CHANNEL_ID = "SukarChannelId"
    private val CHANNEL_NAME = "SukarChannelName"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        AppLogger.e("FCM Token:> ", p0)

        AppPrefs.setDeviceToken(this, p0)

    }

    override fun onMessageReceived(p0: RemoteMessage) {
//        try {
//            val notiObject = JSONObject(p0.data as Map<*, *>)
//            AppLogger.e("Notification Data", notiObject.toString())
//            var notiResp = RetrofitApiCall.getPayload(NotificationResponse::class.java, notiObject.toString())
//            //notiResp.title=notiObject.toString()
//            //notiResp.message=notiObject.toString()
//            showNotificationMessage(applicationContext, notiResp)
//        } catch (e: Exception) {
//            AppLogger.e("Notification Exception", e.message)
//        }


        //Displaying data in log
        //It is optional

//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "From: " + remoteMessage.getFrom());
//            Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
//            Log.d(TAG, "Notification Message Title: " + remoteMessage.getNotification().getTitle());
//            //Calling method to generate notification
//            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
//        }
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = "Default"
        val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(p0.notification!!.title)
                .setContentText(p0.notification!!.body).setAutoCancel(true).setContentIntent(pendingIntent)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        manager.notify(0, builder.build())
    }

//    private fun showNotificationMessage(mContext: Context, response: NotificationResponse) {
//        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val builder = NotificationCompat.Builder(mContext, CHANNEL_ID)
//        builder.setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
//        builder.setLights(Color.CYAN, 3000, 3000)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder.setSmallIcon(R.mipmap.app_icon)
////            builder.color = mContext.resources.getColor(R.color.colorAccent)
//        } else {
//            builder.setSmallIcon(R.mipmap.app_icon)
//        }
//        val intent = Intent(mContext, MainActivity::class.java)
//        val b = Bundle()
//        b.putParcelable(AppConstants.INTENT_KEY.DATA, response)
//        intent.putExtras(b)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val uniqueInt = (System.currentTimeMillis() and 0xfffffff).toInt()
//        var pendingIntent: PendingIntent? = null
//
//        pendingIntent = PendingIntent.getActivity(
//                mContext,
//                uniqueInt,
//                intent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        )
//        builder.setContentIntent(pendingIntent)
//        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.resources, R.mipmap.app_icon))
//        builder.setContentTitle(response.title)
//        builder.setContentText(response.message)
//        builder.setAutoCancel(true)
//        builder.priority = Notification.PRIORITY_HIGH
//        builder.setSound(sound)
//        builder.setChannelId(CHANNEL_ID)
//
//        val bigText = NotificationCompat.BigTextStyle()
//        bigText.bigText(response.message)
//        bigText.setSummaryText(response.title)
//
//        val notificationManager =
//            mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        //notificationManager.cancelAll();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
//            notificationChannel.enableLights(true)
//            notificationChannel.lightColor = Color.RED
//            notificationChannel.setShowBadge(true)
//            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        builder.setStyle(bigText)
//        notificationManager.notify(Random().nextInt(), builder.build())
//
///*        if (response.imageUrl.isNotEmpty()) {
//            try {
//                val request = ImageRequest(response.imageUrl, Response.Listener { response ->
//                    builder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(response))
//                    notificationManager.notify(Random().nextInt(), builder.build())
//                }, 0, 0, null, Bitmap.Config.RGB_565, Response.ErrorListener {
//                    builder.setStyle(bigText)
//                    notificationManager.notify(Random().nextInt(), builder.build())
//                })
//                ImageSingleton.getMySingleton(this).addToRequestQueue(request)
//            } catch (e: Exception) {
//                builder.setStyle(bigText)
//                notificationManager.notify(Random().nextInt(), builder.build())
//            }
//        } else {
//            builder.setStyle(bigText)
//            notificationManager.notify(Random().nextInt(), builder.build())
//        }*/
//    }

}
