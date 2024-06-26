package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class FieldLogTrace : LogTrace {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val START_PREFIX = "-->"
    private val COMPLETE_PREFIX = "<--"
    private val EX_PREFIX = "<X-"

    private var traceIdHolder: TraceId? = null

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        logger.info("[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}$message")
        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = TraceId.create()
        } else {
            traceIdHolder = traceIdHolder!!.createNextId()
        }
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(
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

        releaseTraceId()
    }

    private fun releaseTraceId() {
        traceIdHolder =
            if (traceIdHolder!!.isFirstLevel()) {
                null
            } else {
                traceIdHolder!!.createPreviousId()
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
