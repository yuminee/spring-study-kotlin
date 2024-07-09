package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Service

@Service
class OrderServiceV3(
    private val orderRepository: OrderRepositoryV3,
    private val trace: LogTrace,
) {
    fun orderItem(
        traceId: TraceId,
        itemId: String,
    ) {
        val status = trace.begin("OrderService.orderItem()")
        try {
            orderRepository.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
