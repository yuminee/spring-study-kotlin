package hello.advanced.trace

class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
) {
}