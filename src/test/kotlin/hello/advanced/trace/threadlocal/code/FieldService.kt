package hello.advanced.trace.threadlocal.code

import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

class FieldService() {
    private var nameStore: String? = null
    private val logger = LoggerFactory.getLogger(javaClass)

    fun logic(name: String): String {
        logger.info("저장 name={} -> nameStore={}", name, nameStore)
        nameStore = name
        sleep(1000L)
        logger.info("조회 nameStore={}", nameStore)
        return nameStore!!
    }
}
