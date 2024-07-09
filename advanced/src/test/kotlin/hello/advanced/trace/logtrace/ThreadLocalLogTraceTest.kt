package hello.advanced.trace.logtrace

import org.junit.jupiter.api.Test

class ThreadLocalLogTraceTest {
    val trace = ThreadLocalLogTraceLogTrace()

    @Test
    fun begin_end_level2() {
        val status1 = trace.begin("hello1")
        val status2 = trace.begin("hello2")
        trace.end(status1)
        trace.end(status2)
    }

    @Test
    fun begin_exception_level2() {
        val status1 = trace.begin("hello1")
        val status2 = trace.begin("hello2")
        trace.exception(status1, IllegalStateException())
        trace.exception(status2, IllegalStateException())
    }
}
