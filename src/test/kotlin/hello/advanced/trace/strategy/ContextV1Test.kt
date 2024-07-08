package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV1
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import hello.advanced.trace.template.TemplateMethodTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ContextV1Test {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun contextMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 수행
        log.info("비지니스 로직1 실행")
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        // 비지니스 로직 수행
        log.info("비지니스 로직2 실행")
        // 비지니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV2() {
        // 익명 내부 클래스 사용
        val strategyLogic1 =
            object : Strategy {
                override fun call() {
                    log.info("비지니스 로직1 수행")
                }
            }
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 =
            object : Strategy {
                override fun call() {
                    log.info("비지니스 로직2 수행")
                }
            }
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

    @Test
    fun strategyV3() {
        val context1 =
            ContextV1(
                object : Strategy {
                    override fun call() {
                        log.info("비지니스 로직1 수행")
                    }
                },
            )
        context1.execute()

        val context2 =
            ContextV1(
                object : Strategy {
                    override fun call() {
                        log.info("비지니스 로직2 수행")
                    }
                },
            )
        context2.execute()
    }
}
