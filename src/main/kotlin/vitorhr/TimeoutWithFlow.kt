package vitorhr

import kotlinx.coroutines.flow.channelFlow

class TimeOutWithFlow {
    suspend fun timeoutFlow() = channelFlow<Int> {
        // NOT YET IMPLEMENTED
    }
}