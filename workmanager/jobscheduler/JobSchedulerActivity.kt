class JobSchedulerActivity : AppCompatActivity() {

    lateinit var jobScheduler: JobScheduler //works min for API 21
    lateinit var jobDispatcher: FirebaseJobDispatcher //alernative for lower API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobscheduler)

        jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobDispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))

        button.setOnClickListener {
            //call schedule method from JobScheduler or JobDispatcher
            val resultCode = jobScheduler.schedule(createJobInfo())
            if(resultCode == JobScheduler.RESULT_SUCCESS) {
                //job is scheduled
            }
        }
		
        //call cancel method from JobScheduler or JobDispatcher to cancel pending Job on some event
    }

    //customized and schedule this JobInfo to JobScheduler
    private fun createJobInfo() : JobInfo {
        val componentName = ComponentName(this, CustomJobService::class.java)
        return JobInfo.Builder(1, componentName)
            .setMinimumLatency(1000)
            .setOverrideDeadline(3000)
            .setRequiresCharging(true)
            .build()
    }
	
	//customized and schedule this Job for JobDispatcher
	private fun createJob() : Job {
        return dispatcher.newJobBuilder()
            .setService(CustomJobService::class.java)
            .setConstraints(Constraint.DEVICE_CHARGING)
            .setTrigger(Trigger.executionWindow(1, 3))
            .setTag("TAG")
            .build()
    }
}