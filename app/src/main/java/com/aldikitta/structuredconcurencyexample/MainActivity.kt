package com.aldikitta.structuredconcurencyexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aldikitta.structuredconcurencyexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnCount.setOnClickListener {
            binding.tvCount.text = count++.toString()
        }
        binding.btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
//                binding.tvUserMessage.text = UnstructuredConcurrency().getTotalUserCount().toString()
                binding.tvUserMessage.text = StructuredConcurrency().getTotalUserCount().toString()

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main) {
                for (i in 1..200000) {
                    binding.tvUserMessage.text =
                        "Downloading user $i in ${Thread.currentThread().name}"
                    delay(1000)

                }

            }
        }

    }
}