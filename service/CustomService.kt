class CustomService : Service() {

    //use worker thread if long running operation to not block main UI thread
    //or provide some multithreading if needed
    private val handlerThread = HandlerThread("HandlerThread")
    private lateinit var handler : Handler

    override fun onBind(intent: Intent?): IBinder? {
        //invokes when bindService called, retrieve intent and decide what to do
        //if service is created by bindService and onStartCommand wasn't called then runs only as components are bound
        
        return null //provide communication interface or return null when no bind needed
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //invokes when startService or startForegroundService called, retrieve intent and decide what to do
        //continues to run until stops itself or by another component
		
        return START_STICKY //restart service strategy after destroyed by the system
    }

    override fun onCreate() {
        //invokes one time setup before onStartCommand or onBind
        //not called when service already running
        super.onCreate()
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    override fun onDestroy() {
        //invokes when stopSelf or stopService is called
        //service is no longer used or is being destroyed by system or client
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        handlerThread.quitSafely()
        handlerThread.interrupt()
    }

    //more lifecycle callbacks
}

//run startService or bindService and pass Intent to run Service