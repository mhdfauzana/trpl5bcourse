package com.muhammadfauzanazhar.trpl5bcourse.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.muhammadfauzanazhar.trpl5bcourse.QuoteModal
import com.muhammadfauzanazhar.trpl5bcourse.RetrofitAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun Quotes() {
    var quote by remember { mutableStateOf("Siap-siap termotivasi") }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        ) {
            Text(
                text = quote,
                modifier = Modifier.padding(16.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 200.dp, start = 20.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        val retrofit = Retrofit.Builder()
                            .baseUrl("https://api.npoint.io")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val service = retrofit.create(RetrofitAPI::class.java)

                        val call = service.getQuote()
                        val response: Response<List<QuoteModal>> = call.execute()

                        if (response.isSuccessful) {
                            val quotes = response.body()
                            if (!quotes.isNullOrEmpty()) {
                                val randomQuote = quotes.random()
                                quote = randomQuote.text
                            }
                        } else {
                            quote = "Gagal mengambil Quote, coba lagi nanti."
                        }
                    }
                },
            ) {
                Text(text = "Generate Quote", color = Color.White)
            }

        }
    }
}