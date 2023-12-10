package com.clprunner.runnertalk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunnerTalkApplication

fun main(args: Array<String>) {
	runApplication<RunnerTalkApplication>(*args)
}
