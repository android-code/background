class CustomIntentService : IntentService("name") {

    override fun onHandleIntent(intent: Intent?) {
        //retrieve data
        val action = intent?.action
        val data = intent?.dataString
        //do some work based on action
    }

    //avoid to override Service's callback like onStartCommand
    //they are automatically invoked by IntentService
}