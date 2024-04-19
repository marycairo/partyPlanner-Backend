package com.example.demo.Boostrap


import com.example.demo.DemoApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServeLetInitializer : SpringBootServletInitializer(){

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(DemoApplication::class.java)
    }
}