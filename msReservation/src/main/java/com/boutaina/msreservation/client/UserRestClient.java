package com.boutaina.msreservation.client;

import com.boutaina.msreservation.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msUsers")
public interface UserRestClient {
    @GetMapping("/api/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
