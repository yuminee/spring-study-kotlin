package hello.advanced.trace.template.code

class SubClassLocal2 : AbstractTemplate() {
    override fun call() {
        log.info("비지니스 로직2 실행")
    }
}
