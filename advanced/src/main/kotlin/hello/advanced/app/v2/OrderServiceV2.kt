package hello.advanced.app.v2

import hello.advanced.trace.TraceId
import hello.advanced.trace.hellotrace.HelloTraceV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV2(
    private val orderRepository: OrderRepositoryV3,
    private val trace: HelloTraceV2,
) {
    fun orderItem(
        traceId: TraceId,
        itemId: String,
    ) {
        val status = trace.beginSync(traceId, "OrderService.orderItem()")
        try {
            orderRepository.save(status.traceId, itemId)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}
