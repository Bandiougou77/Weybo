package org.ldv.weybo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeyboApplication

fun main(args: Array<String>) {
	runApplication<WeyboApplication>(*args)
}
