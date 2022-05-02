import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HotFlowExampleTest {

    private val hotFlowExample = HotFlowExample()

    @Test
    fun `assert first 2 values from shared flow`() = runTest {
        // Arrange
        val takeResultDeferred = async { hotFlowExample.sharedFlow.take(2).toList() }
        advanceUntilIdle()

        // Act
        hotFlowExample.emitSharedFlowValues()

        // Assert
        val takeResult = takeResultDeferred.await()
        assertEquals(States.NO_CONNECTION, takeResult[0])
        assertEquals(States.CONNECTING, takeResult[1])
    }

    @Test
    fun `assert first 2 values from shared flow with replay`() = runTest {
        // Act
        hotFlowExample.emitSharedFlowValues()

        val takeResultDeferred = hotFlowExample.sharedFlow.take(2).toList()

        // Assert
        val takeResult = takeResultDeferred
        assertEquals(States.NO_CONNECTION, takeResult[0])
        assertEquals(States.CONNECTING, takeResult[1])
    }

    @Test
    fun `assert first value from shared flow`() = runTest {
        // Arrange
        val takeResultDeferred = async { hotFlowExample.sharedFlow.first() }
        advanceUntilIdle()

        // Act
        hotFlowExample.emitSharedFlowValues()

        // Assert
        val takeResult = takeResultDeferred.await()
        assertEquals(States.NO_CONNECTION, takeResult)
    }

    @Test
    fun `assert concurrent shared flow`() = runTest {
        // Arrange
        val takeResultDeferred = async { hotFlowExample.sharedFlow.take(2).toList() }
        advanceUntilIdle()

        // Act
        hotFlowExample.emitConcurrentSharedFlowValues()

        // Assert
        val takeResult = takeResultDeferred.await()
        assertEquals(States.CONNECTING, takeResult[0])
        assertEquals(States.NO_CONNECTION, takeResult[1])
    }
}