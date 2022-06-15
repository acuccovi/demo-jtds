package com.example.demojtds.service;

import com.example.demojtds.entity.Tbl;
import com.example.demojtds.repository.TblRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TblService {

    private final TblRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public TblService(TblRepository repository, JdbcTemplate jdbcTemplate) {

        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Tbl> fetchData(List<String> data) {

        try {
            jdbcTemplate.execute("CREATE TABLE #tbl_tmp(u varchar(1000), d varchar(255), INDEX IDX_U NONCLUSTERED (u))");
        } catch (Exception ignore) {
        }

        var uuid = UUID.randomUUID().toString();
        var values = data.stream().map(d -> new Object[]{uuid, d}).toList();
        jdbcTemplate.batchUpdate("INSERT INTO #tbl_tmp (u, d) VALUES (?, ?)", values);

        var result = repository.findByNameIn(uuid);

        jdbcTemplate.update("DELETE FROM #tbl_tmp WHERE u = ?", uuid);

        return result;
    }
}
