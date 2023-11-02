package com.muhammadfauzanazhar.trpl5bcourse

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("f22613ed761dd451107e")
    fun getQuote(): Call<List<QuoteModal>>
}