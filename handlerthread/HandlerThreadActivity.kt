class HandlerThreadActivity : AppCompatActivity() {

    //work to do by requestHandler
    val runnable = Runnable {
        //some background job
        responseHandler.sendMessage(Message()) //send message or post the job
        responseHandler.post { 
            //some UI action
        }
    }

    //handler on the main thread, override handleMessage if needed
    val responseHandler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            //some action on UI
        }
    }

    //handler on the background thread of handlerThread object
    lateinit var requestHandler: Handler

    //instead of Thread use reusable HandlerThread
    //this can be also done by extend HandlerThread class and putting there Handler object
    val handlerThread = HandlerThread("HandlerThread")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)

        handlerThread.start() //start the thread
        requestHandler = Handler(handlerThread.looper) //now Looper of handlerThread can be passed

        button.setOnClickListener {
            requestHandler.post(runnable) //post the job to MessageQueue
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //remove tasks and messages from handlers
        requestHandler.removeCallbacksAndMessages(null)
        responseHandler.removeCallbacksAndMessages(null)
        handlerThread.quitSafely() //quit HandlerThread's Looper
        handlerThread.interrupt() //stop current job
    }
}