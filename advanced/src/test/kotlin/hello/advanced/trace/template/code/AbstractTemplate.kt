package hello.advanced.trace.template.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractTemplate {
    val log: Logger = LoggerFactory.getLogger(AbstractTemplate::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 수행
        call() // 상속
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    abstract fun call()
}
