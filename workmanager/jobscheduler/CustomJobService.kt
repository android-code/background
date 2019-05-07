class CustomJobService : JobService() {

    //note that JobService is running on main thread like standard Service
    private val uiHandler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    override fun onStartJob(params: JobParameters?): Boolean {
        //reached when job starts running
        executor.execute {
            //do some background job
            jobFinished(params, false) //call to inform that job is finished
            //post some result on UI if needed
        }
        return false //is work still in progress?
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        //reached when job has stopped - manual or auto
        jobFinished(params, false)
        return false //should work be retried?
    }
}