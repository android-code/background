class ForegroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, ForegroundService::class.java)
        intent.putExtra("PARAM", "value")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //background restriction for Android Oreo and above
            startForegroundService(intent)
        }
        else {
            //just start as normal
            startService(intent)
        }
        
        //remove from foreground by calling stopForeground
    }
}