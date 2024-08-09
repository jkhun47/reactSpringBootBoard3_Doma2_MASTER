package com.fumidzuki.dao;

import com.fumidzuki.entity.EmployeesEntity;
import com.fumidzuki.model.SexType;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;

@Dao
@AnnotateWith(annotations = {@Annotation(target = AnnotationTarget.CLASS, type = Repository.class),
    @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class),
    @Annotation(target = AnnotationTarget.CONSTRUCTOR_PARAMETER, type = Qualifier.class,
        elements = "\"databaseOneConfig\"")})
public interface DatabaseTwoDao {

//  @Select
//  public List<AccountOne> selectAll();
//
//  @Select
//  public List<BoardEntity> selectAllLimit(SelectOptions options);
//
//  @Select
//  public List<EmployeesEntity> selectSearchLimit(SelectOptions options,String author,String title,String contents);

//  @Select
//  public List<BoardEntity> selectSearchBoard(String author,String title,String contents);
//
//  @Select
//  public List<EmployeesEntity> selectMultiSearchEmployees(int emp_no, SexType gender, LocalDate birth_date, LocalDate hire_date);

//  @Select
//  public List<EmployeesEntity> selectSearchEmployees(int emp_no, SexType gender, LocalDate birth_date, LocalDate hire_date);

  @Select
  public List<EmployeesEntity> selectAllEmployees();

  @Select
  List<EmployeesEntity> selectSearchLimit(SelectOptions options, int emp_no, SexType gender, LocalDate birth_date, LocalDate hire_date);

  @Select
  List<EmployeesEntity> selectMultiSearchEmployees(int empNo, SexType gender, LocalDate hireDateFrom, LocalDate hireDateTo, LocalDate birthDateFrom, LocalDate birthDateTo, String firstName, String lastName);

  @Select
  List<EmployeesEntity> selectSearchEmployees(int emp_no, String gender, LocalDate hireDate, LocalDate birthDate);

  @Select
  List<EmployeesEntity> selectMultiSearchLimit(SelectOptions options, int empNo, SexType gender, LocalDate hireDateFrom, LocalDate hireDateTo, LocalDate birthDateFrom, LocalDate birthDateTo, String firstName, String lastName);

  @Select
  EmployeesEntity selectByEmpNo(int empNo);

  @Insert
  public Result<EmployeesEntity> insert(EmployeesEntity employeesEntity);

  @Update
  public Result<EmployeesEntity> update(EmployeesEntity entity);

  @Update(sqlFile = true)
  public Result<EmployeesEntity> updateEmployee(EmployeesEntity entity);

  @Delete
  Result<EmployeesEntity> delete(EmployeesEntity entity);

  @Delete(sqlFile = true)
  Result<EmployeesEntity> deleteEmployee(EmployeesEntity entity);

  @Select
  int selectMaxEmpNo();

  @Insert(sqlFile = true)
  Result<EmployeesEntity> insertEmployee(EmployeesEntity entity);

//  @Select
//  public BoardEntity selectById(Long idx);
//
//  @Insert
////  public Result<BoardEntity> addBoard(BoardEntity boardEntity);
//  public Result<BoardEntity> insert(BoardEntity boardEntity);
//
////  @Update
////  public Result<BoardEntity> updateBoard(BoardEntity boardEntity);
////  }
//
//  @Update
//  public Result<BoardEntity> update(BoardEntity boardEntity);
  }
