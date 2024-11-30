package com.example.kurs.DataBase;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class CreateDB {
    private final JdbcTemplate jdbcTemplate;

    public CreateDB(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @PostConstruct
    public void init(){
        createTables();
    }

    private void createTables(){
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users("
                + "user_id BIGSERIAL PRIMARY KEY, "
                + "name VARCHAR(255) NOT NULL, "
                + "email VARCHAR(255) NOT NULL"
                + ");";

        jdbcTemplate.execute(createUsersTable);
    }
}
