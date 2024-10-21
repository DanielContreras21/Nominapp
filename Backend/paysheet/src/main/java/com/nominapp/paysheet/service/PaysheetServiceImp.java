package com.nominapp.paysheet.service;

import com.nominapp.paysheet.entity.Paysheet;
import com.nominapp.paysheet.exception.NotFoundException;
import com.nominapp.paysheet.repository.PaysheetRepository;
import com.nominapp.paysheet.service.abstraction.PaysheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysheetServiceImp implements PaysheetService {

    @Autowired
    private PaysheetRepository repository;

    @Override
    public Paysheet savePaysheet(Paysheet paysheet) {
        return repository.save(paysheet);
    }

    @Override
    public List<Paysheet> getAllPaysheets() {
        return repository.findAll();
    }

    @Override
    public List<Paysheet> getPaysheetByEmployee(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}
