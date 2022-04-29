import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        hotFlowExample.sharedFlow(sharedFlow)

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

    private val _stateFlow = MutableStateFlow(States.NO_CONNECTION)
    val stateFlow = _stateFlow.asStateFlow()

    suspend fun stateFlow() {
        _stateFlow.emit(States.CONNECTING)
    }

    suspend fun sharedFlow(sharedFlow: MutableSharedFlow<States>) {
        sharedFlow.emit(States.SCANNING)
        sharedFlow.emit(States.CONNECTING)
    }
}