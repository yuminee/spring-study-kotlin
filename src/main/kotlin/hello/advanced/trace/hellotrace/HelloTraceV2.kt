package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class HelloTraceV2() {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val START_PREFIX = "-->"
    private val COMPLETE_PREFIX = "<--"
    private val EX_PREFIX = "<X-"

    fun begin(message: String): TraceStatus {
        val traceId = TraceId.create()
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}$message")
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(
        beforeTraceId: TraceId,
        message: String,
    ): TraceStatus {
        val nextId = beforeTraceId.createNextId()
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${nextId.id}] ${addSpace(START_PREFIX, nextId.level)}$message")
        return TraceStatus(nextId, startTimeMs, message)
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(
        status: TraceStatus,
        e: Exception,
    ) {
        complete(status, e)
    }

    private fun complete(
        status: TraceStatus,
        e: Exception?,
    ) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - status.startTimeMs
        val traceId = status.traceId

        if (e == null) {
            logger.info("[${traceId.id}] ${addSpace(COMPLETE_PREFIX, traceId.level)}${status.message} time=${resultTimeMs}ms")
        } else {
            logger.info("[${traceId.id}] ${addSpace(EX_PREFIX, traceId.level)}${status.message} time=${resultTimeMs}ms ex=$e")
        }
    }

    private fun addSpace(
        prefix: String,
        level: Int,
    ): String {
        val sb = StringBuilder()
        for (i: Int in 0..level) {
            if (i == level - 1) {
                sb.append("|$prefix")
            } else {
                sb.append("|   ")
            }
        }
        return sb.toString()
    }
}
