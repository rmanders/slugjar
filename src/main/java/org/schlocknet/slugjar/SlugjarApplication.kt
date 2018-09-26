package org.schlocknet.slugjar

import org.schlocknet.slugjar.util.ApplicationEnvironment
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration

@SpringBootApplication(
    exclude = [
      DataSourceAutoConfiguration::class,
      MongoAutoConfiguration::class,
      MongoDataAutoConfiguration::class,
      SecurityAutoConfiguration::class
    ]
)
class SlugjarApplication {

  companion object {

    @JvmStatic
    fun main(args: Array<String>) {
      ApplicationEnvironment.initializeFleet()
      SpringApplication.run(SlugjarApplication::class.java)
    }

  }
}
