package hello.advanced.trace.strategy.code.strategy

import hello.advanced.trace.template.TemplateMethodTest
import org.slf4j.LoggerFactory

// context는 변하지 않는 로직을 가지고 있는 템플릿 역활을 하는 코드.
class ContextV1(
    private val strategy: Strategy,
) {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 수행
        strategy.call()
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }
}
