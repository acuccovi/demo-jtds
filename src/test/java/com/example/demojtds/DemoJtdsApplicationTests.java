package com.example.demojtds;

import com.example.demojtds.service.TblService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class DemoJtdsApplicationTests {

    @Autowired
    private TblService tblService;

    @Test
    void contextLoads() {

        var strings = IntStream.range(0, 10000).mapToObj(Integer::toString).toList();
        var byNameIn = tblService.fetchData(strings);
        assertFalse(byNameIn.isEmpty());
    }

}
