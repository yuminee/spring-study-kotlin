package hello.advanced.trace.template.code

class SubClassLocal1 : AbstractTemplate() {
    override fun call() {
        log.info("비지니스 로직1 실행")
    }
}
