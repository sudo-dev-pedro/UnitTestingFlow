package vitorhr

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow

class FlowInt {
    suspend fun returnFromOneToTen() =
        channelFlow {
            coroutineScope {
                send(1)
                delay(10L)
                send(2)
                delay(10L)
                send(3)
                delay(10L)
                send(4)
                delay(10L)
                send(5)
                delay(10L)
                send(6)
                delay(10L)
                send(7)
                delay(10L)
                send(8)
                delay(10L)
                send(9)
                delay(10L)
                send(10)
            }
        }
}