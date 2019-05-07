class BackgroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, BackgroundService::class.java)
        intent.putExtra("PARAM", "value")
        startService(intent)
    }

    override fun onStop() {
        super.onStop()
        stopService(Intent(this, BackgroundService::class.java))
    }
}