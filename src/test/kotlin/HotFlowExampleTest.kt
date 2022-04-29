import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HotFlowExampleTest {

    private val hotFlowExample = HotFlowExample()

    @Test
    fun `assert state flow`() = runTest {
        // Act
        val takeResult = hotFlowExample.stateFlow.take(1).toList()

        // Assert
        assertEquals(States.NO_CONNECTION, takeResult[0])
    }
}