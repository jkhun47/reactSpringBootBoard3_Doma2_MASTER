package com.fumidzuki.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seasar.doma.*;
import com.fumidzuki.model.SexType;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "EMPLOYEES")
@Entity(immutable = true)
public class EmployeesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "emp_no")
    private int emp_no;
//    @Column(name = "birth_date")
    private LocalDate birth_date;
//    @Column(name = "first_name")
    private String first_name;
//    @Column(name = "last_name")
    private String last_name;
//    @Column(name = "author")
    private SexType gender;
//    @Column(name = "hire_date")
    private LocalDate hire_date;
}
