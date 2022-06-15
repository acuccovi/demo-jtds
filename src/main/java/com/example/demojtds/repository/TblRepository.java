package com.example.demojtds.repository;

import com.example.demojtds.entity.Tbl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TblRepository extends CrudRepository<Tbl, Long> {

    @Query(value = "SELECT * from tbl WHERE name IN (SELECT d FROM #tbl_tmp WHERE u = :u)", nativeQuery = true)
    List<Tbl> findByNameIn(String u);

    List<Tbl> findByNameIn(List<String> names);
}
