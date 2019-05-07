class IntentServiceCommunication : IntentService("name") {

    override fun onHandleIntent(intent: Intent?) {
        //retrieve data
        val action = intent?.action
        val data = intent?.getStringExtra("extra")
        //do some work based on action

        sendBroadcast()
    }

    private fun sendBroadcast() {
        val broadcastIntent = Intent("FILTER_ACTION")
        broadcastIntent.putExtra("message", "result")
        sendBroadcast(broadcastIntent)
        Log.d("IntentService", "sendBroadcast")
    }
}