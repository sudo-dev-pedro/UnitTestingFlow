import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val flowExample = FlowExample()

    runBlocking {
        flowExample.flow().collect {
            println("[Flow] State: $it")
        }
    }

    runBlocking {
        flowExample.channelFlow().collect {
            println("[ChannelFlow] State: $it")
        }
    }
}

class FlowExample {
    fun flow() = flow {
        for (i in 1..3) {
            delay(1000)
            emit(map(i))
        }
    }

    fun channelFlow(): Flow<States> = channelFlow {
        coroutineScope {
            launch(IO) {
                delay(1000)
                send(States.SCANNING)
                delay(2000)
                send(States.CONNECTING)
                delay(3000)
                send(States.CONNECTED)
            }
        }
    }

    private fun map(id: Int): States = when (id) {
        1 -> States.SCANNING
        2 -> States.CONNECTING
        3 -> States.CONNECTED
        else -> States.NO_CONNECTION
    }
}