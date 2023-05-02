package com.VacationProject.VacationProjectFrontEnd.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @NonNull
    private Integer id;

    @NonNull
    private String customerName;

    @NonNull
    private String password;

    @NonNull
    private String role;

}
