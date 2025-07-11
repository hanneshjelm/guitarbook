package com.hannes.guitarbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GuitarBookApplication

fun main(args: Array<String>) {
    runApplication<GuitarBookApplication>(*args)
}
