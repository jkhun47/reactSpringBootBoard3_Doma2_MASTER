package com.fumidzuki.service;

import com.fumidzuki.dao.DatabaseOneDao;
import com.fumidzuki.dao.DatabaseTwoDao;
import com.fumidzuki.dtos.EmployeesDto;
import com.fumidzuki.entity.EmployeesEntity;
import com.fumidzuki.model.SexType;
import com.fumidzuki.page.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.Result;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
// @Transactional
public class EmployeesService {

  @Autowired
  DatabaseOneDao databaseOneDao;

  @Autowired
  DatabaseTwoDao databaseTwoDao;

  //============================================복수검색조건============================================================
  //게시글 가져오기(컨트롤러에서 분리)
  public EmployeesDto getListResult(Map<String, String> params){
    //전체글
    List<EmployeesEntity> datas = getEmployeesAll();
    //조건검색후 결과
    List<EmployeesEntity> searchDatas = getSearchEmployees(params);
    //전체글 갯수
    int listCnt = datas.size();
    //조건검색결과 갯수
    int searchListCnt = searchDatas.size();
    //첫페이지
    int curPage = Integer.parseInt(params.get("page"));

    //search key, search value세팅
//    EmployeesEntity employeesEntity = searchValueSet(sk,sv);
    //검색조건 Param을 dto에 세팅
    EmployeesDto employeesDto = searchValueSet(params);

    Pagination pagination = null;
//    getPagination(pagination,sk,sv,searchListCnt,listCnt,curPage);
//    if(!sk.isEmpty() && !sv.isEmpty()){
//    if(!params.get("empNo").isEmpty() || !params.get("gender").isEmpty() || !params.get("hireDateFrom").isEmpty() || !params.get("hireDateTo").isEmpty() || !params.get("birthDateFrom").isEmpty() || !params.get("birthDateTo").isEmpty() || !params.get("firstName").isEmpty() || !params.get("lastName").isEmpty()){
    if(!params.get("empNo").isEmpty() || !params.get("gender").isEmpty() || !params.get("hireDateFrom").isEmpty() || !params.get("hireDateTo").isEmpty() || !params.get("birthDateFrom").isEmpty() || !params.get("birthDateTo").isEmpty() || !params.get("firstName").isEmpty() || !params.get("lastName").isEmpty()){
      pagination = new Pagination(searchListCnt,curPage,10,10);
    } else {
      pagination = new Pagination(listCnt,curPage,10,10);
    }

    // 한페이지당 표시 개수 처리 옵션
    SelectOptions options = SelectOptions.get().offset(pagination.getStartIndex()).limit(pagination.getPageSize());

    // listResult와 pagination 값을 리턴하기위해 boardDto에 삽입(set)
//    List<EmployeesEntity> listResult = this.databaseTwoDao.selectSearchLimit(options, employeesEntity.getEmp_no(),employeesEntity.getGender(),employeesEntity.getBirth_date(),employeesEntity.getHire_date());
    List<EmployeesEntity> listResult = this.databaseTwoDao.selectMultiSearchLimit(options, employeesDto.getEmpNo(),employeesDto.getGender(),employeesDto.getHireDateFrom(),employeesDto.getHireDateTo(),employeesDto.getBirthDateFrom(),employeesDto.getBirthDateTo(),employeesDto.getFirstName(),employeesDto.getLastName());
//    EmployeesDto employeesDto = new EmployeesDto();
    employeesDto.setPage(pagination);
    employeesDto.setList(listResult);

    return employeesDto;
//    return this.databaseOneDao.selectSearchLimit(options, boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
  }

  //전체 게시글 목록 가져오기
  public List<EmployeesEntity> getEmployeesAll() {
    return this.databaseTwoDao.selectAllEmployees();
  }

  // 검색값이 입력된 목록 가져오기(검색조건 있을때 없을때 모든 상태)
  public List<EmployeesEntity> getSearchEmployees(Map<String,String> params){
    //params을 dto에 세팅(dto에 세팅하면 null에러가 방지됨)
    EmployeesDto employeesDto = searchValueSet(params);
//    EmployeesEntity employeesEntity = searchValueSet(sk,sv);
//    int emp_no = Integer.parseInt(params.get("empNo"));
//    LocalDate hireDate = Date.valueOf(params.get("hireDate")).toLocalDate();
//    if(hireDate.equals(null)||hireDate.equals("")){
//    }
//    LocalDate birthDate = Date.valueOf(params.get("birthDate")).toLocalDate();
    List<EmployeesEntity> datas = databaseTwoDao.selectMultiSearchEmployees(employeesDto.getEmpNo(),employeesDto.getGender(),employeesDto.getHireDateFrom(),employeesDto.getHireDateTo(),employeesDto.getBirthDateFrom(),employeesDto.getBirthDateTo(),employeesDto.getFirstName(),employeesDto.getLastName());
//    List<EmployeesEntity> datas = databaseTwoDao.selectMultiSearchEmployees(Integer.parseInt(params.get("empNo")),params.get("gender"),params.get("hireDateFrom"),params.get("hireDateTo"),params.get("birthDateFrom"),params.get("birthDateTo"),params.get("firstName"),params.get("lastName"));
    return datas;
  }

  //search key, search value를 검색하기전 boardEnttity에 세팅
  public EmployeesDto searchValueSet(Map<String,String> params){
    EmployeesDto employeesDto = new EmployeesDto();
      if(StringUtils.hasLength(params.get("empNo"))) {
        employeesDto.setEmpNo(Integer.parseInt(params.get("empNo")));
      }
      if(StringUtils.hasLength(params.get("gender"))) {
        employeesDto.setGender(SexType.valueOf(params.get("gender")));
      }
      if(StringUtils.hasLength(params.get("hireDateFrom"))) {
        employeesDto.setHireDateFrom(LocalDate.parse(params.get("hireDateFrom")));
      }
      if(StringUtils.hasLength(params.get("hireDateTo"))) {
        employeesDto.setHireDateTo(LocalDate.parse(params.get("hireDateTo")));
      }
      if(StringUtils.hasLength(params.get("birthDateFrom"))) {
        employeesDto.setBirthDateFrom(LocalDate.parse(params.get("birthDateFrom")));
      }
      if(StringUtils.hasLength(params.get("birthDateTo"))) {
        employeesDto.setBirthDateTo(LocalDate.parse(params.get("birthDateTo")));
      }
      if(StringUtils.hasLength(params.get("firstName"))) {
        employeesDto.setFirstName(params.get("firstName"));
      }
      if(StringUtils.hasLength(params.get("lastName"))) {
        employeesDto.setLastName(params.get("lastName"));
      }
    return employeesDto;
  }

  //게시글 가져오기
  public EmployeesEntity getEmployee(int empNo) {
    return this.databaseTwoDao.selectByEmpNo(empNo);
  }

    //게시글 등록
  public Result<EmployeesEntity> register(EmployeesDto employeesDto){
    EmployeesEntity entity = EmployeesEntity.builder()
            .emp_no(employeesDto.getEmpNo())
            .gender(employeesDto.getGender())
            .first_name(employeesDto.getFirstName())
            .last_name(employeesDto.getLastName())
            .birth_date(employeesDto.getBirthDate())
            .hire_date(employeesDto.getHireDate())
            .build();
    return this.databaseTwoDao.insertEmployee(entity);
  }

    //게시글 수정
  public Result<EmployeesEntity> modify(EmployeesDto employeesDto) {
    EmployeesEntity entity = this.databaseTwoDao.selectByEmpNo(employeesDto.getEmpNo());
    entity = EmployeesEntity.builder()
            .emp_no(employeesDto.getEmpNo())
            .gender(employeesDto.getGender())
            .birth_date(employeesDto.getBirthDate())
            .hire_date(employeesDto.getHireDate())
            .first_name(entity.getFirst_name())
            .last_name(entity.getLast_name())
            .build();
//    return this.databaseOneDao.updateBoard(entity);
    return this.databaseTwoDao.updateEmployee(entity);
  }

  //게시글 삭제
  public Result<EmployeesEntity> delete(int emp_no) {
    EmployeesEntity entity = this.databaseTwoDao.selectByEmpNo(emp_no);
    return this.databaseTwoDao.deleteEmployee(entity);
  }

  //추가시 새번호 취득
  public EmployeesEntity getNewEmpNo(){
    int data = this.databaseTwoDao.selectMaxEmpNo()+1;
    EmployeesEntity entity = new EmployeesEntity();
    entity.setEmp_no(data);
    return entity;
  }

//============================================복수검색조건============================================================

////============================================단일검색조건============================================================
////  public List<AccountOne> getAccountOne() {
////    return this.databaseOneDao.selectAll();
////  }
////----------------------사용안함-----------------------------------------------------------------------------------
////  //게시글 목록 가져오기(검색조건 있는 상태)
////  public List<EmployeesEntity> getBoardLimit(Pagination page, String sk, String sv){
////    //search key, search value세팅
////    EmployeesEntity employeesEntity = searchValueSet(sk,sv);
////    SelectOptions options = SelectOptions.get().offset(page.getStartIndex()).limit(page.getPageSize());
////    return this.databaseTwoDao.selectSearchLimit(options, employeesEntity.getEmp_no(),employeesEntity.getGender(),employeesEntity.getBirth_date(),employeesEntity.getHire_date());
////  }
//// ---------------------사용안함-----------------------------------------------------------------------------------
//
//  //게시글 가져오기(컨트롤러에서 분리)
//  public EmployeesDto getListResult(String page, String sk, String sv){
//    List<EmployeesEntity> datas = getEmployeesAll();
//    List<EmployeesEntity> searchDatas = getSearchEmployees(sk,sv);
//    int listCnt = datas.size();
//    int searchListCnt = searchDatas.size();
//    int curPage = Integer.parseInt(page);
//
//    //search key, search value세팅
//    EmployeesEntity employeesEntity = searchValueSet(sk,sv);
//
//    Pagination pagination = null;
////    getPagination(pagination,sk,sv,searchListCnt,listCnt,curPage);
//    if(!sk.isEmpty() && !sv.isEmpty()){
//      pagination = new Pagination(searchListCnt,curPage,10,10);
//    } else {
//      pagination = new Pagination(listCnt,curPage,10,10);
//    }
//
//    // 한페이지당 표시 개수 처리 옵션
//    SelectOptions options = SelectOptions.get().offset(pagination.getStartIndex()).limit(pagination.getPageSize());
//
//    // listResult와 pagination 2값을 리턴하기위해 boardDto에 삽입(set)
//    List<EmployeesEntity> listResult = this.databaseTwoDao.selectSearchLimit(options, employeesEntity.getEmp_no(),employeesEntity.getGender(),employeesEntity.getBirth_date(),employeesEntity.getHire_date());
//    EmployeesDto employeesDto = new EmployeesDto();
//    employeesDto.setPage(pagination);
//    employeesDto.setList(listResult);
//
//    return employeesDto;
////    return this.databaseOneDao.selectSearchLimit(options, boardEntity.getAuthor(),boardEntity.getTitle(),boardEntity.getContents());
//  }
//
//  //전체 게시글 목록 가져오기
//  public List<EmployeesEntity> getEmployeesAll() {
//    return this.databaseTwoDao.selectAllEmployees();
//    //        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
////    List<BoardDto> dtos = new ArrayList<>();
////    List<BoardEntity> boardEntities = databaseOneDao.selectAllBoard();
////    for(BoardEntity entity : boardEntities){
////      BoardDto dto = BoardDto.builder()
////              .idx(entity.getIdx())
////              .author(entity.getAuthor())
////              .title(entity.getTitle())
////              .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
////              .build();
////
////      dtos.add(dto);
////    }
////    return dtos;
//  }
//
//  // 검색값이 입력된 목록 가져오기(검색조건 있을때 없을때 모든 상태)
//  public List<EmployeesEntity> getSearchEmployees(String sk, String sv){
//    //search key, search value세팅
//    EmployeesEntity employeesEntity = searchValueSet(sk,sv);
//    return this.databaseTwoDao.selectSearchEmployees(employeesEntity.getEmp_no(),employeesEntity.getGender(),employeesEntity.getBirth_date(),employeesEntity.getHire_date());
//  }
//
////search key, search value를 검색하기전 boardEnttity에 세팅
//  public EmployeesEntity searchValueSet(String sk, String sv){
//    EmployeesEntity employeesEntity = new EmployeesEntity();
//    if("emp_no".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        employeesEntity.setEmp_no(Integer.parseInt(sv));
//      }
//    } else if ("gender".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        employeesEntity.setGender(SexType.valueOf(sv));
//      }
//    } else if ("birth_date".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        employeesEntity.setBirth_date(LocalDate.parse(sv));
//      }
//    } else if ("hire_date".equals(sk)) {
//      if(StringUtils.hasLength(sv)) {
//        employeesEntity.setHire_date(LocalDate.parse(sv));
//      }
//    }
//    return employeesEntity;
//  }
////============================================단일검색조건============================================================
//  public Pagination getPagination(Pagination page,String sk,String sv,int searchListCnt, int listCnt, int curPage ){
//
//    if(!sk.isEmpty() && !sv.isEmpty()){
//      page = new Pagination(searchListCnt,curPage,10,10);
//    } else {
//      page = new Pagination(listCnt,curPage,10,10);
//    }
//    return page;
//  }

//  //게시글 등록
////  public Result<BoardEntity> addBoard(BoardDto boardDto){
////    BoardEntity entity = BoardEntity.builder()
////            .title(boardDto.getTitle())
////            .contents(boardDto.getContents())
////            .author(boardDto.getAuthor())
////            .createdAt(LocalDateTime.now())
////            .build();
////    return this.databaseOneDao.addBoard(entity);
////  }
//  public Result<BoardEntity> register(BoardDto boardDto){
//        BoardEntity entity = BoardEntity.builder()
//            .title(boardDto.getTitle())
//            .contents(boardDto.getContents())
//            .author(boardDto.getAuthor())
//            .createdAt(LocalDateTime.now())
//            .build();
//    return this.databaseOneDao.insert(entity);
//  }
//
//  //게시글 수정
////  public Result<BoardEntity> modify(BoardDto boardDto) {
////    BoardEntity entity = BoardEntity.builder()
////            .title(boardDto.getTitle())
////            .contents(boardDto.getContents())
////            .createdAt(LocalDateTime.now())
////            .build();
////    return this.databaseOneDao.update(entity);
////  }
//
//  public Result<BoardEntity> modify(BoardDto boardDto) {
//    BoardEntity entity = this.databaseOneDao.selectById(boardDto.getIdx());
//    entity = BoardEntity.builder()
//            .idx(boardDto.getIdx())
//            .title(boardDto.getTitle())
//            .contents(boardDto.getContents())
//            .author(entity.getAuthor())
//            .createdAt(entity.getCreatedAt())
//            .build();
////    return this.databaseOneDao.updateBoard(entity);
//    return this.databaseOneDao.update(entity);
//  }
//
//
//  // public void getAccount() {
//  //
//  // List<AccountOne> accountOnes = this.databaseOneDao.selectAll();
//  // log.info("1：取得件数：{}", accountOnes.size());
//  // AccountOne one = new AccountOne();
//  // one.setUserId(new Random().nextInt());
//  // one.setUsername("username_" + one.getUserId());
//  // this.databaseOneDao.insert(one);
//  //
//  // List<AccountTwo> accountTwos = this.databaseTwoDao.selectAll();
//  // log.info("2：取得件数：{}", accountTwos.size());
//  //
//  // // TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//  //
//  // }
//
//  //게시글 목록 가져오기(페이징처리 추가)
////    public Header<List<BoardDto>> getBoardList(Pageable pageable, SearchCondition searchCondition){
////        List<BoardDto> dtos = new ArrayList<>();
//////        List<BoardEntity> boardEntities = boardRepository.findAll();
//////        Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
////        Page<BoardEntity> boardEntities = boardRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
////        for(BoardEntity entity : boardEntities){
////            BoardDto dto = BoardDto.builder()
////                    .idx(entity.getIdx())
////                    .author(entity.getAuthor())
////                    .title(entity.getTitle())
////                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
////                    .build();
////
////            dtos.add(dto);
////        }
////        Pagination pagination = new Pagination(
////                (int) boardEntities.getTotalElements()
////                , pageable.getPageNumber() + 1
////                , pageable.getPageSize()
////                , 10
////        );
////        return Header.OK(dtos, pagination);
////    }

}
