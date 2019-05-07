class WorkManagerActivity : AppCompatActivity() {

    private lateinit var workId : UUID //store id in case to manage work

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager)

        button.setOnClickListener {
            startSimpleWork()
            observeWorkStatus()
        }
    }

    private fun startSimpleWork() {
        val workRequest = createWorkRequest()
        WorkManager.getInstance().enqueue(workRequest)
        workId = workRequest.id
    }

    private fun createWorkRequest() : WorkRequest {
        //create input data
        val inputData = workDataOf(Pair("INPUT_KEY", "some_input"))

        //create constraint conditions
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiresCharging(true)
            .build()

        //init WorkRequest using WorkRequest class or some Builder for single or periodic work
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInputData(inputData)
            .setConstraints(constraint)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS) //retry work conditions
            .setInitialDelay(3, TimeUnit.SECONDS)
            .build()

        return workRequest
    }
	
	private fun observeWorkStatus() {
        WorkManager.getInstance().getWorkInfoByIdLiveData(workId)
            .observe(this, Observer { workInfo ->
                //do some action based on workInfo state
        })
    }
}