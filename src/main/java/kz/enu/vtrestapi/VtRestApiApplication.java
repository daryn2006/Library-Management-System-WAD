package kz.enu.vtrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan(basePackages = "kz.example")
public class VtRestApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VtRestApiApplication.class, args);
    }
}
