package kz.enu.vtrestapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.enu.vtrestapi.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MyController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/")
    public ResponseEntity<Void> root() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/login"))
                .build();
    }

    @GetMapping("/index")
    public String printMessage() {
        return "Hello World";
    }

    @GetMapping(value = "/index/specific", produces = "application/json")
    public String getDefaultStudent() {
        Student student = new Student(1, "Sagadi Daryn", 20);
        return toJson(student);
    }

    @PostMapping(value = "/index/specific", produces = "application/json")
    public String getSpecialStudent(@RequestParam("name") String name) {
        Student student = new Student(1, name, 20);
        return toJson(student);
    }

    private String toJson(Student student) {
        try {
            return objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
