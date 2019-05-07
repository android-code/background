class WorkManagerChainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workmanager_chain)

        button.setOnClickListener {
            startChainedWork()
        }
    }

    private fun startChainedWork() {
        WorkManager.getInstance()
            .beginWith(Arrays.asList(createPartOneWorkRequest(), createPartTwoWorkRequest()))
            .then(createFinalWorkRequest())
            .enqueue()
    }

    private fun createPartOneWorkRequest() : OneTimeWorkRequest {
        val inputData = workDataOf(Pair("SIZE", "small"))
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        return OneTimeWorkRequestBuilder<PartWorker>()
            .setInputData(inputData)
            .setConstraints(constraints)
            .build()
    }

    private fun createPartTwoWorkRequest() : OneTimeWorkRequest {
        val inputData = workDataOf(Pair("COLOR", "red"))
        val constraints = Constraints.Builder().setRequiresStorageNotLow(true).build()

        return OneTimeWorkRequestBuilder<PartWorker>()
            .setInputData(inputData)
            .setConstraints(constraints)
            .build()
    }

    private fun createFinalWorkRequest() : OneTimeWorkRequest {
        return OneTimeWorkRequestBuilder<FinalWorker>()
            .setInputMerger(ArrayCreatingInputMerger::class.java) //merge all values from every keys
            .setConstraints(Constraints.Builder().setRequiresBatteryNotLow(true).build())
            .build()
    }
}