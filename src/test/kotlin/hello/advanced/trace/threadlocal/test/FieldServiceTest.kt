package hello.advanced.trace.threadlocal.test

import hello.advanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.lang.Thread.sleep

class FieldServiceTest() {
    private val fieldService = FieldService()
    private val logger = LoggerFactory.getLogger(javaClass)

    @Test
    fun field() {
        logger.info("main start")
        val userA =
            Runnable {
                fieldService.logic("userA")
            }
        val userB =
            Runnable {
                fieldService.logic("userB")
            }

        val threadA = Thread(userA)
        threadA.name = "thread-A"

        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
        sleep(100)
        threadB.start()
        sleep(3000)
        logger.info("main exit")
    }
}
