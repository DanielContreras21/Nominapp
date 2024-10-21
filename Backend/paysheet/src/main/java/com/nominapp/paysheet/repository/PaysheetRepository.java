package com.nominapp.paysheet.repository;

import com.nominapp.paysheet.entity.Paysheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaysheetRepository extends MongoRepository<Paysheet, String> {

    List<Paysheet> findByEmployeeId(Long id);
}
