class CommunicationActivity : AppCompatActivity() {

    private val receiver = CustomBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)
        button.setOnClickListener { startIntentService() }

        registerReceiver(receiver, IntentFilter("FILTER_ACTION"))
    }

    override fun onDestroy() {
        //if service is running and don't need anymore callback then stop IntentService
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    fun startIntentService() {
        val intent = Intent(this, CustomIntentService::class.java)
        intent.action = "action"
        intent.putExtra("extra", "value")
        startService(intent)
    }

    //implement as nested class if only need to interact with this activity
    class CustomBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.getStringExtra("message")
            //do something like update UI
        }
    }
}