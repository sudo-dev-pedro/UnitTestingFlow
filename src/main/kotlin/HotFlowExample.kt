import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

suspend fun main() {
    val hotFlowExample = HotFlowExample()

    // Replay gera um cache/buffer
    val sharedFlow = MutableSharedFlow<States>(2)

//    hotFlowExample.stateFlow()
//
//    coroutineScope {
//        hotFlowExample.stateFlow.collect {
//            println("State Flow: $it")
//        }

    coroutineScope {

        launch {
            sharedFlow.collect {
                println("First Shared Flow: $it")
            }
        }
        launch {
            sharedFlow.collect {
                delay(2000)
                println("Second Shared Flow: $it")
            }
        }
        launch {
            sharedFlow.emit(States.CONNECTED)
        }
    }
}

class HotFlowExample {

    private val _sharedFlow = MutableSharedFlow<States>(2)
    val sharedFlow = _sharedFlow

    suspend fun emitSharedFlowValues() {
        _sharedFlow.emit(States.NO_CONNECTION)
        _sharedFlow.emit(States.CONNECTING)
    }


    suspend fun emitConcurrentSharedFlowValues() {
        coroutineScope {
            launch {
                delay(2000L)
                _sharedFlow.emit(States.NO_CONNECTION)
            }
            launch {
                _sharedFlow.emit(States.CONNECTING)
            }
        }
    }
}