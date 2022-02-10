package com.example.sslpinning

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sslpinning.Constans.BASE_URL
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.URL
import java.security.KeyStore
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class MessageViewModel(application: Application) : BaseViewModel(application) {

    var errorMessage = MutableLiveData<String>()
    var coffees = MutableLiveData<List<Coffee>>()
    private val TAG = "MessageViewModel"


    fun getMessage() {
        viewModelScope.launch {
            val response = RetrofitInstance.retrofitInstance.getMessage()
            response.enqueue(object : Callback<List<Coffee>> {
                override fun onResponse(
                    call: Call<List<Coffee>>,
                    response: Response<List<Coffee>>
                ) {
                    coffees.postValue(response.body())
                    Log.e(TAG, "SUCCESS")
                }

                override fun onFailure(call: Call<List<Coffee>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    Log.e(TAG, "${t.message}")
                }
            })
        }
    }

}