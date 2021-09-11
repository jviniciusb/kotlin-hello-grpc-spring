package com.jviniciusb.kotlinhellogrpcspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinHelloGrpcSpringApplication

fun main(args: Array<String>) {
    runApplication<KotlinHelloGrpcSpringApplication>(*args)
}
