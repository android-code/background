class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        //must run service in foreground immediately by show notification
        showNotification()
        //notification can't be dismissed unless the service is stopped or removed from foreground
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //do some action
        return START_STICKY
    }

    private fun showNotification() {
        //create NotificationChannel if not exists for Android Oreo and above
        val channelId = "channel_id"

        //customize notification
        val notification = NotificationCompat.Builder(this, channelId)
            .setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .build()
		
        //start service in foreground
        startForeground(100, notification)
    }
}