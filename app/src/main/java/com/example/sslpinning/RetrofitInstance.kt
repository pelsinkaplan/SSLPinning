package com.example.sslpinning

import com.example.sslpinning.Constans.BASE_URL
import com.example.sslpinning.Constans.DOMAIN
import com.example.sslpinning.Constans.FINGERPRINT
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitInstance {

    companion object {

        private val certificatePinner = CertificatePinner.Builder()
            .add(
                DOMAIN,
                FINGERPRINT
            )
            .build()
        private val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) //created above
            .build()

        val retrofitInstance: RetrofitApi by lazy {
            retrofit.create(RetrofitApi::class.java)
        }
    }
}

/*
        subject key identifier -> f7843909893a08b5661104e7b31cd4a0dda1a280
        encrypted -> Zjc4NDM5MDk4OTNhMDhiNTY2MTEwNGU3YjMxY2Q0YTBkZGExYTI4MA==

        thumbprint -> 2f0155636d442b9c135019b0deb2b2d6d9dbc1c1
        encrypted -> MmYwMTU1NjM2ZDQ0MmI5YzEzNTAxOWIwZGViMmIyZDZkOWRiYzFjMQ==

        fingerprint -> 0D:E4:3F:66:AF:A7:2B:1E:8B:1F:F9:5D:E5:6E:0D:6F:98:73:0C:10:78:72:A6:9A:83:09:C8:1D:2B:34:BD:FC
        SHA256 Fingerprint=D0:2D:9C:41:C0:E7:24:70:9C:64:AC:52:E4:CA:D5:77:25:5A:8F:3C:D8:8D:2B:45:E7:F6:20:85:04:53:37:0F
*/

