import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HotFlowExampleTest {

    private val hotFlowExample = HotFlowExample()

    @Test
    fun `assert state flow`() = runBlockingTest {
        // Act
        val takeResultDeferred = async { hotFlowExample.stateFlow.take(2).toList() }
        hotFlowExample.stateFlow()
        val takeResult = takeResultDeferred.await()

        // Assert
        assertEquals(States.NO_CONNECTION, takeResult[0])
        assertEquals(States.CONNECTING, takeResult[1])
        takeResultDeferred.cancel()
    }
}