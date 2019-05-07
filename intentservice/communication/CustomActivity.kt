class CustomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
        button.setOnClickListener { startIntentService() }
    }

	//just call startService to run IntentService
    private fun startIntentService() {
        val intent = Intent(this, CustomIntentService::class.java)
        intent.action = "action"
        intent.putExtra("extra", "value")
        startService(intent)
    }
}