class CustomJobIntentService : JobIntentService() {

    companion object {
        const val ID = 100

        //method for enqueuing work to this service, just call from client to start
        fun enqueueWork(context : Context, work : Intent) {
            enqueueWork(context, CustomJobIntentService::class.java, ID, work)
        }
    }

    override fun onHandleWork(intent: Intent) {
        //do some job here
        //use BroadcastReceiver or something else to post the result
    }
}