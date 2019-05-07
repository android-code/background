class ThreadPoolActivity : AppCompatActivity() {

    //this ExecutorService is wrapped ThreadPoolExecutor (this class extends ExecutorService)
    //equivalent of ThreadPoolExecutor with min and max only 1 thread in pool and LinkedBlockingQueue
    val executor : ExecutorService = Executors.newSingleThreadExecutor()

    val runnable = Runnable {
        //some background job
        uiHandler.post {
            //some UI job
        }
    }

    val callable = Callable {
        //some background job and return result
        return@Callable "result"
    }

    val uiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)

        button1.setOnClickListener {
            //use executor with Runnable        
            executor.execute(runnable)
        }

        button2.setOnClickListener {
            //use executor with Callable        
            val future : Future<String> = executor.submit(callable)
            val result = future.get()
            //this can be canceled by run future.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //remove tasks and messages from handlers
        uiHandler.removeCallbacksAndMessages(null)
        executor.shutdownNow() //kill all threads
    }
}