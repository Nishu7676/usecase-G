package com.example.EStockMarketApplication.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="EmployeeService", value="EmployeeService", url="http://localhost:8081")
public interface MyfeignClient {
	
    @PostMapping("/authorize")
    public boolean authorize(@RequestHeader("Authorization")String token);

    @PostMapping("/getrole")
    public String getrole(@RequestHeader("Authorization")String token);

    @GetMapping("/getmails/{role}")
    public List<String> getmails(@PathVariable String role);


}
