class BoundService : Service() {

    inner class CustomBinder : Binder() {
        fun getService() : CustomService = this@CustomService
        //allow to call public methods
    }

    private val binder = CustomBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        //called when all clients disconnect, return true to allow call onRebind
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        //called when new client connect after all had disconnected
        super.onRebind(intent)
    }

    //can be used by Binder
    fun action() {
        //some work
    }
}