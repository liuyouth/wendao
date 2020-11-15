package pub.moi.wendao

import org.hibernate.engine.jdbc.LobCreationContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@SpringBootApplication
class WendaoApplication  : WebMvcConfigurerAdapter() {
//	override fun addCorsMappings(registry: CorsRegistry) {
//		//跨域
//		registry!!.addMapping("/**")
//				.allowCredentials(true)
//				.allowedHeaders("*")
//				.allowedOrigins("*")
//				.allowedMethods("*")
//	}


}

fun main(args: Array<String>) {
	runApplication<WendaoApplication>(*args)
}

