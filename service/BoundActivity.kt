class BoundActivity : AppCompatActivity() {

    private var service : CustomService? = null
    private var isBound = false
    
    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CustomService.CustomBinder
            this@MainActivity.service = binder.getService()
            isBound = true
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, CustomService::class.java)
        intent.putExtra("PARAM", "value")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        
        //use service binder action to communicate
        button.setOnClickListener { service?.action() }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}