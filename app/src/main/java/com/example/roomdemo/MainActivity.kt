package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.roomdemo.DataBase.SubscriberDatabase
import com.example.roomdemo.DataBase.SubscriberRepository
import com.example.roomdemo.DataBase.SubscriberViewModelFactory
import com.example.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscriberViewModel = ViewModelProvider(this)[SubscriberViewModel::class.java]

        //creating a DAO instance
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        //creating a Repository instance
        val repository = SubscriberRepository(dao)
        //creating a SubscriberViewModelFactory instance
        val factory = SubscriberViewModelFactory(repository)

        //assigning the viewModel object to the SubscriberViewModel class
        binding.myViewModel = subscriberViewModel

        // adding a lifecycle owner to the MainActivity class
        binding.lifecycleOwner = this

        displaySubscriberList()

    }

    // writing a fun that will observed by the LiveData to
// get all the list of subscribers

   private fun displaySubscriberList(){
        subscriberViewModel.subscriber.observe(this, Observer {
            Log.i("MyTag", it.toString())
        })

    }
}