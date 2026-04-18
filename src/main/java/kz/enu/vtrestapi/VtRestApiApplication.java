package kz.enu.vtrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = {"kz.enu.vtrestapi", "kz.example.lms"})
@ServletComponentScan("kz.example.lms.servlet")
public class VtRestApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(VtRestApiApplication.class, args);
    }
}
