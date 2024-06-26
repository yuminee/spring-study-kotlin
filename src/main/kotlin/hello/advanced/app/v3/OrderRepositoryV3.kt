package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(
    private val trace: LogTrace,
) {
    fun save(
        traceId: TraceId,
        itemId: String,
    ) {
        val status = trace.begin("OrderRepository.save()")
        try {
            if (itemId.equals("ex")) {
                throw IllegalArgumentException("예외 발생!")
            }
            sleep(1000L)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
