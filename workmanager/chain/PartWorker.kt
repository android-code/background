class PartWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    
    override fun doWork(): Result {
        val color = inputData.getString("COLOR")
        val size = inputData.getString("SIZE")
        
        //do some work
        
        if(color != null) {
            val output = workDataOf(Pair("RESULT", "colored to $color"))
            return Result.success(output)
        }
        else if(size != null) {
            val output = workDataOf(Pair("RESULT", "resize to $size"))
            return Result.success(output)
        }
        else {
            return Result.failure()
        }
    }
}