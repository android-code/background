//first of generic types are params, second progress and third is final result
private class CustomAsyncTask : AsyncTask<String, Int, String>() {

    override fun onPreExecute() {
        //prepare before running background job like show progress dialog
    }

    override fun doInBackground(vararg params: String): String {
        //some background job with passed params like URLs
        var total = ""
        for((counter, url) in params.withIndex()) {
            //download info from url
            val result = "result from : $url\n"
            total = total.plus(url)
            publishProgress(counter) //inform that some part of full request if completed
        }
        return total
    }

    override fun onProgressUpdate(vararg values: Int?) {
        //show some progress updates based on Int from publishProgress
    }

    override fun onPostExecute(result: String) {
        //do something on main thread when background job finished like update view
    }

    override fun onCancelled() {
        //stop and clear or show some message
    }
}

//to start just call
//CustomAsyncTask().execute("url1", "url2", "url3")