package com.example.sslpinning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.sslpinning.MessageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MessageViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tempViewModel: MessageViewModel by viewModels()
        this.viewModel = tempViewModel

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getMessage()
        }
        viewModel.coffees.observe(this, Observer {
            Toast.makeText(applicationContext, it[0].title, Toast.LENGTH_SHORT).show()
            Log.e("MainActivity", it[0].title)
            findViewById<TextView>(R.id.message).text = it[0].title
        })
    }


//    private lateinit var viewModel: ViewModel
//    private val retrofitService = RetrofitService.getInstance()
//    private val TAG = "MainActivity"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        viewModel =
//            ViewModelProvider(this, ViewModelFactory(RetrofitRepository(retrofitService))).get(
//                ViewModel::class.java
//            )
//
//        viewModel.getMessage()
//        viewModel.message.observe(this, Observer {
//            Toast.makeText(applicationContext,it.message,Toast.LENGTH_SHORT).show()
//            Log.e(TAG, it.message)
//        })
//        viewModel.getMessage()
//    }
}