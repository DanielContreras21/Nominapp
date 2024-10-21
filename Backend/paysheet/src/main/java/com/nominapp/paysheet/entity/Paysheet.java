package com.nominapp.paysheet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("paysheets")
public class Paysheet {

    @Id
    private String id;
    private String eps;
    private Long employeeId;
}
