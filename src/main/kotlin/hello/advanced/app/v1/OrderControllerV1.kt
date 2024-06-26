package hello.advanced.app.v1

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV1(
    private val orderService: OrderServiceV1,
    private val trace: HelloTraceV1
) {
    @GetMapping("/v1/request")
    fun request(itemId: String): String{
        val status = trace.begin("OrderController.request()")
        try {
            orderService.orderItem(itemId)
            trace.end(status)
        } catch (e: Exception){
            trace.exception(status, e)
            throw e
        }
        return "ok"
    }
}