package com.boutaina.msreservation.client;

import com.boutaina.msreservation.dto.SalleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msSalles")
public interface SalleRestClient {
    @GetMapping("/api/salles/{id}")
    SalleDTO getSalleById(@PathVariable("id") Long id);
}
