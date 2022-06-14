package com.simplify.ktlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtlinApplication

fun main(args: Array<String>) {
	runApplication<KtlinApplication>(*args)
}
