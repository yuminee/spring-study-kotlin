package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.template.Callback
import hello.advanced.trace.strategy.code.template.TimeLogTemplate
import hello.advanced.trace.template.TemplateMethodTest
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class TemplateCallbackTest {
    private val log = LoggerFactory.getLogger(TemplateMethodTest::class.java)

    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        template.execute(
            object : Callback {
                override fun call() {
                    log.info("비지니스 로직 수행")
                }
            },
        )

        template.execute(
            object : Callback {
                override fun call() {
                    log.info("비지니스 로직2 수행")
                }
            },
        )
    }
}
