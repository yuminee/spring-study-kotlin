package hello.advanced.app.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {

    fun save(itemId: String) {
        if (itemId.equals("ex")) {
            throw IllegalArgumentException("예외 발생!")
        }
        sleep(1000L)
    }

    fun sleep(millis: Long){
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}