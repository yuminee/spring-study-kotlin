package hello.advanced.trace

import java.util.*

class TraceId(
    val id: String,
    val level: Int
) {
    companion object {
        fun create(): TraceId {
            return TraceId(
                id = creatId(),
                level = 0
            )
        }

        private fun creatId(): String {
            return UUID.randomUUID().toString().substring(0, 8)
        }
    }

    fun createNextId(): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPreviousId(): TraceId {
        return TraceId(id, level - 1)
    }

    fun isFirstLevel(): Boolean {
        return level == 0
    }
}