package com.clprunner.runnertalkuser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunnerTalkUserApplication

fun main(args: Array<String>) {
    runApplication<RunnerTalkUserApplication>(*args)
}
