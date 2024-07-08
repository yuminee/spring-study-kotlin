package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV2
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import hello.advanced.trace.template.TemplateMethodTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV2Test {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun strategyV1() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }

    // 전략패턴 익명내부클래스사용.
    @Test
    fun strategyV2() {
        val context = ContextV2()
        context.execute(
            object : Strategy {
                override fun call() {
                    log.info("비지니스 로직1 수행")
                }
            },
        )
        context.execute(
            object : Strategy {
                override fun call() {
                    log.info("비지니스 로직2 수행")
                }
            },
        )
    }
}
