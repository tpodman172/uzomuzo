package com.tpodman172.uzomuzo.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HelloRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findOne(int id){
        final String query = "SELECT * FROM tasks WHERE id = ?";
        Map<String, Object> tasks = jdbcTemplate.queryForMap(query, id);
        return tasks;
    }
}