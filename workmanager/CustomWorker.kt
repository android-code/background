class CustomWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    //do some custom work, e.g. upload or sync something
    override fun doWork(): Result {
        val input = inputData.getString("INPUT_KEY") //get the input
        //do some work
        val result = "some_work_result" //retrieve the result
        val output = workDataOf(Pair("OUTPUT_KEY", result)) //create the output

        //return the success, failure or retry result based on situations
        return Result.success(output)
    }
}