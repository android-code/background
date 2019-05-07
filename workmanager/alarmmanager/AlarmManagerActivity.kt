class AlarmManagerActivity : AppCompatActivity() {

    val alarmManager : AlarmManager by lazy { getSystemService(ALARM_SERVICE) as AlarmManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmmanager)

        button.setOnClickListener {
            startAlarm()
        }
    }

    private fun startAlarm() {
        //prepare Intent and PendingIntent (e.g. send alarm to BroadcastReceiver)
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 100, intent, 0)

        //schedule alarm with specific trigger time and PendingIntent
        //use specific method based on OS version in addition 
        val time = System.currentTimeMillis() + 3000
        val type = RTC_WAKEUP
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(type, time, pendingIntent)
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            alarmManager.setExact(type, time, pendingIntent)
        else
            alarmManager.set(type, time, pendingIntent)
    }
}