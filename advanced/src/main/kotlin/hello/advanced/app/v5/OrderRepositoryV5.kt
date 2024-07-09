package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5(
    private val template: TraceTemplate,
) {
    fun save(itemId: String) =
        template.execute(
            "OrderRepository.save()",
            object : TraceCallback<Unit> {
                override fun call() {
                    if (itemId.equals("ex")) {
                        throw IllegalArgumentException("예외 발생!")
                    }
                    sleep(1000L)
                }
            },
        )

    fun sleep(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
