package hello.advanced.trace.strategy.code.template

import hello.advanced.trace.template.TemplateMethodTest
import org.slf4j.LoggerFactory

class TimeLogTemplate {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        callback.call()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }
}
