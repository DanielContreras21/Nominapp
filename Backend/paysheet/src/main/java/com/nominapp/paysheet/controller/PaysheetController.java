package com.nominapp.paysheet.controller;

import com.nominapp.paysheet.entity.Paysheet;
import com.nominapp.paysheet.service.abstraction.PaysheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paysheets")
public class PaysheetController {

    @Autowired
    private PaysheetService service;

    @PostMapping
    public ResponseEntity<Paysheet> savePaysheet(@RequestBody Paysheet paysheet){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePaysheet(paysheet));
    }


    @GetMapping
    public ResponseEntity<List<Paysheet>> getAllPaysheets(){
        return ResponseEntity.ok(service.getAllPaysheets());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Paysheet>> getPaysheetsByEmployee(@PathVariable Long employeeId){
        return ResponseEntity.ok(service.getPaysheetByEmployee(employeeId));
    }
}
