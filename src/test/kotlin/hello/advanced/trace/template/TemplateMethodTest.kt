package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLocal1
import hello.advanced.trace.template.code.SubClassLocal2
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateMethodTest {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun templateMethodV0() {
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
    fun templateMethodV1() {
        val template1: AbstractTemplate = SubClassLocal1()
        template1.execute()
        val template2: AbstractTemplate = SubClassLocal2()
        template2.execute()
    }

    @Test
    fun templateMethodV2() {
        val template1 =
            object : AbstractTemplate() {
                override fun call() {
                    log.info("비지니스 로직1 수행")
                }
            }
        template1.execute()

        val template2 =
            object : AbstractTemplate() {
                override fun call() {
                    log.info("비지니스 로직2 수행")
                }
            }
        template2.execute()
    }
}
