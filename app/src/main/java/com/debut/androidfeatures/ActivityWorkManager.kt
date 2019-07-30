package com.debut.androidfeatures

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*

class ActivityWorkManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        init()
    }

    private fun init() {
        //set data object in key value pair
        val data = Data.Builder().putString(Constants.TITLE, "My work manager").build()

        //set constraints to the worker class
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        //this is oneTimeRequest
        val oneTimeWorkRequest =
            OneTimeWorkRequest.Builder(MyWorker::class.java).setConstraints(constraints).setInputData(data).build()
        //we will initialize the work
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)

        //This will observe the work
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer<WorkInfo> {
                Log.e("work states", "${it.state}")
                Log.e("Output  data", "${it.outputData.getString(Constants.SUCCESS)}")
            })
    }
}
