package com.aldikitta.structuredconcurencyexample

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class StructuredConcurrency {
    var count = 0
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserCount(): Int {

        // this coroutinesScope is a suspend function, it different with CoroutinesScope
        coroutineScope {
            launch(IO) {
                delay(1000)
                count = 50
            }

            deferred = async(IO) {
                delay(3000)
                return@async 70
            }
        }
        return count + deferred.await()

    }
}