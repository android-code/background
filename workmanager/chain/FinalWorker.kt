class FinalWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        //get merged input from pre workers
        //it contains all values in this key from every pre Worker
        val input = inputData.getString("RESULT") //so it should be: colored to red, resize to small
        //do something with input and return result
        return Result.success()
    }
}