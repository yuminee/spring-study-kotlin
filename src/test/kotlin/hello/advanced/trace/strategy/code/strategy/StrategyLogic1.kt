package hello.advanced.trace.strategy.code.strategy

import hello.advanced.trace.template.TemplateMethodTest
import org.slf4j.LoggerFactory

class StrategyLogic1 : Strategy {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    override fun call() {
        log.info("비지니스1 로직 수행 ")
    }
}
