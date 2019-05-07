class ThreadActivity : AppCompatActivity() {

    val runnable = Runnable {
        //some background job
        runOnUiThread { 
            //UI job if needed
        }
    }
    val thread = Thread(runnable) //pass runnable or create anonymous object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        button.setOnClickListener {
            //do not allow start thread if is started
            if(thread.state == Thread.State.NEW)
                thread.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(thread.state != Thread.State.TERMINATED)
            thread.interrupt() //stop the thread if needed
    }
}