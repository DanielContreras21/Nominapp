package com.nominapp.employee.external.services;


import com.nominapp.employee.entity.Paysheet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Paysheet")
public interface PaysheetService {

    @GetMapping("/paysheet/{paysheetId}")
    public Paysheet getPaysheet(@PathVariable String paysheetId);
}
