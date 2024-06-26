package hello.advanced.trace.hellotrace

import org.junit.jupiter.api.Test

class HelloTraceV2Test {
    @Test
    fun begin_end() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.end(status1)
        trace.end(status2)
    }

    @Test
    fun begin_exception() {
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.exception(status1, IllegalStateException())
        trace.exception(status2, IllegalStateException())
    }
}
