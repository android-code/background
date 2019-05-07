class BackgroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent != null && intent.hasExtra("PARAM")) {
            val data = intent.getStringExtra("PARAM")
            //do something based on param
            action()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        //will destroy on Android Oreo and above if app close
        super.onDestroy()
    }
    
    private fun action() {
        //some work
		
        //inform about finish by Toast
        Toast.makeText(this, "Some message", Toast.LENGTH_LONG).show()
		
        //after that service is no longer need, so destroy manual
        stopSelf()
    }
}