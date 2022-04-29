import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlowExampleTest {

    private val flowExample = FlowExample()

    @Test
    fun `when emit, should verify emissions`() = runTest {
        // Act
        val result = flowExample.flow().toList()

        // Assert
        assertEquals(States.SCANNING, result[0])
        assertEquals(States.CONNECTING, result[1])
        assertEquals(States.CONNECTED, result[2])
    }

    @Test
    fun `when emit, should verify take emissions`() = runTest {
        // Act
        val result = flowExample.flow().take(2).toList()

        // Assert
        assertEquals(States.SCANNING, result[0])
        assertEquals(States.CONNECTING, result[1])
    }

    @Test
    fun `when emit, should verify first and last emission`() = runTest {
        // Act
        val result = flowExample.flow().first()
        val resultLast = flowExample.flow().last()

        // Assert
        assertEquals(States.SCANNING, result)
        assertEquals(States.CONNECTED, resultLast)
    }
}