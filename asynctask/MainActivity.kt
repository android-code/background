class MainActivity : AppCompatActivity(), Listener {
    
    //reference to task allows to manage it's lifecycle
    private var asyncTask : SaferAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
		
        button.setOnClickListener {
            //AsyncTask is single shot, so create new task every time
            if(asyncTask == null || asyncTask?.status != AsyncTask.Status.RUNNING) {
                asyncTask = SaferAsyncTask(this)
                asyncTask?.execute("url1", "url2", "url3")
            }
        }
    }

    override fun onDestroy() {
        //stop the task when exit from Activity
        if(asyncTask?.status == AsyncTask.Status.RUNNING)
            asyncTask?.cancel(true)
        super.onDestroy()
    }

    override fun onStarting() {
        //create progress dialog
    }

    override fun onProgress(progress: Int) {
        //update progress dialog
    }

    override fun onFinished(result: String) {
        //close progress dialog
        //show the result
    }

    override fun onCancel() {
        //close progress dialog
        //show error message
    }

    private class SaferAsyncTask(listener : Listener) : AsyncTask<String, Int, String>() {

        private val reference = WeakReference<Listener>(listener)

        override fun onPreExecute() {
            reference.get()?.onStarting()
        }

        override fun doInBackground(vararg params: String): String {
            var total = ""
            for((counter, url) in params.withIndex()) {
                val result = "result from : $url\n"
                total = total.plus(result)
                publishProgress(counter) //inform that some part of full request if completed
            }
            return total
        }

        override fun onProgressUpdate(vararg values: Int?) {
            values[0]?.let { reference.get()?.onProgress(it) }
        }

        override fun onPostExecute(result: String) {
            reference.get()?.onFinished(result)
        }

        override fun onCancelled() {
            reference.get()?.onCancel()
        }
    }
}

private interface Listener {
    fun onStarting()
    fun onProgress(progress : Int)
    fun onFinished(result : String)
    fun onCancel()
}