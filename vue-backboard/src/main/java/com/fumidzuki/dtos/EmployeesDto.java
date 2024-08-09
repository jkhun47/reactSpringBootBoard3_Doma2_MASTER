package com.fumidzuki.dtos;

import com.fumidzuki.entity.BoardEntity;
import com.fumidzuki.entity.EmployeesEntity;
import com.fumidzuki.model.SexType;
import com.fumidzuki.page.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.seasar.doma.Column;

import java.io.Serializable;
import java.time.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesDto implements Serializable {

    private int empNo;
    private String firstName;
    private String lastName;
    private SexType gender;
    private LocalDate hireDateFrom;
    private LocalDate hireDateTo;
    private LocalDate birthDateFrom;
    private LocalDate birthDateTo;
//    private String empNo;
//    private String gender;
//    private String firstName;
//    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;



    //service에서 controller로 복수의 값을 리턴하기 위해
    private List<EmployeesEntity> list;
    private Pagination page;
}
