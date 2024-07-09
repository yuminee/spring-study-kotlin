package hello.advanced.trace.threadlocal.test

import hello.advanced.trace.threadlocal.code.ThreadLocalService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ThreadLocalServiceTest {
    private val service = ThreadLocalService()
    private val logger = LoggerFactory.getLogger(javaClass)

    @Test
    fun field() {
        logger.info("main start")
        val userA =
            Runnable {
                service.logic("userA")
            }
        val userB =
            Runnable {
                service.logic("userB")
            }

        val threadA = Thread(userA)
        threadA.name = "thread-A"

        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
        Thread.sleep(100)
        threadB.start()
        Thread.sleep(3000)
        logger.info("main exit")
    }
}
