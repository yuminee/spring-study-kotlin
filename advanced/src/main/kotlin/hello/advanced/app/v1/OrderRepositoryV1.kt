package hello.advanced.app.v1

import hello.advanced.trace.hellotrace.HelloTraceV1
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1(
    private val trace: HelloTraceV1
) {

    fun save(itemId: String) {
        val status = trace.begin("OrderRepository.save()")
        try {
            if (itemId.equals("ex")) {
                throw IllegalArgumentException("예외 발생!")
            }
            sleep(1000L)
            trace.end(status)
        } catch (e: Exception){
            trace.exception(status, e)
            throw e
        }
    }

    fun sleep(millis: Long){
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}