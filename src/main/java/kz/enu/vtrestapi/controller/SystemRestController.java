package kz.enu.vtrestapi.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class SystemRestController {

    private final JdbcTemplate jdbcTemplate;

    public SystemRestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/db-status")
    public Map<String, Object> dbStatus() {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            String database = jdbcTemplate.queryForObject("select current_database()", String.class);
            String user = jdbcTemplate.queryForObject("select current_user", String.class);
            response.put("status", "UP");
            response.put("database", database);
            response.put("user", user);
            return response;
        } catch (Exception ex) {
            response.put("status", "DOWN");
            response.put("error", ex.getMessage());
            return response;
        }
    }
}
