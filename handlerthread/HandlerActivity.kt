class HandlerActivity : AppCompatActivity() {

    val START = 1
    val FINISH = 2

    val runnable = Runnable {
        //some background job
        handler.sendMessage(createMessage(FINISH, "result")) //send message to handle action
        handler.post { 
            //do action on UI without message communication
        }
    }

    val thread: Thread = Thread(runnable)

    val handler = object: Handler(Looper.getMainLooper()) { //pass Looper of thread owner
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == START && thread.state == Thread.State.NEW) {
                thread.start()
            }
            else if(msg?.what == FINISH) {
                //some action with msj.obj result
            }
        }
    }

    fun createMessage(what : Int, obj : Any) : Message {
        val message = Message()
        message.what = what
        message.obj = obj
        return message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        button.setOnClickListener {
            //instead of direct thread.start() it can be done by sending message
            handler.sendEmptyMessage(START)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) //remove pending work from MessageQueue
        thread.interrupt()
    }	
}