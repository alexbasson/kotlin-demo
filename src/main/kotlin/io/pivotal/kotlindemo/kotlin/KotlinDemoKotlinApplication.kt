package io.pivotal.kotlindemo.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinDemoKotlinApplication

fun main(args: Array<String>) {
    runApplication<KotlinDemoKotlinApplication>(*args)
}
