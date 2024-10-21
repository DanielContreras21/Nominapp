package com.nominapp.paysheet.service.abstraction;

import com.nominapp.paysheet.entity.Paysheet;

import java.util.List;

public interface PaysheetService {
    Paysheet savePaysheet(Paysheet user);
    List<Paysheet> getAllPaysheets();
    List<Paysheet> getPaysheetByEmployee(Long employeeId);
}
