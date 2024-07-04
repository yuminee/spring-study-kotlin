package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractTemplate<T>(
    private val trace: LogTrace,
) {
    val log: Logger = LoggerFactory.getLogger(AbstractTemplate::class.java)

    fun execute(message: String): T? {
        var status: TraceStatus? = null
        return try {
            status = trace.begin(message)
            // 로직 호출
            val result = call()
            trace.end(status)
            result
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

    abstract fun call(): T
}
