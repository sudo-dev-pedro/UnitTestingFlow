package vitorhr

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout

class Manager(
    private val flowInt: FlowInt,
    private val timeOutWithFlow: TimeOutWithFlow,
) {

    suspend fun callFlowIntOneToTen() = flowInt.returnFromOneToTen()

    suspend fun timeOut(): Flow<Int> = flow {
        try {
            withTimeout(1000L) {
                timeOutWithFlow.timeoutFlow()
                emit(SUCCESS_CODE)
            }
        } catch (e: Throwable) {
            emit(ERROR_CODE)
        }
    }

    companion object {
        const val ERROR_CODE = 987
        const val SUCCESS_CODE = 1
    }
}